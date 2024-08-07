package dreamjob.repository;

import dreamjob.configuration.DatasourceConfiguration;
import dreamjob.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2oException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Properties;

class Sql2UserRepositoryTest {
    private static Sql2UserRepository sql2UserRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2UserRepository.class
                .getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");
        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);
        sql2UserRepository = new Sql2UserRepository(sql2o);
    }

    @AfterEach
    public void clearUsers() {
        var users = sql2UserRepository.findAll();
        for (var user : users) {
            sql2UserRepository.deleteById(user.getId());
        }
    }

    @Test
    public void whenSaveThenGetSame() {
        var user = sql2UserRepository
                .save(new User(0, "test@example.com", "Test User", "securePassword")).get();
        var savedUser = sql2UserRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).get();
        assertThat(savedUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void whenDuplicateThenException() {
        var user = sql2UserRepository
                .save(new User(0, "test@example.com", "Test User", "securePassword")).get();
        assertThatThrownBy(() -> {
            sql2UserRepository
                    .save(new User(0, "test@example.com", "Test User", "securePassword"));
        }).isInstanceOf(Sql2oException.class);
    }
}