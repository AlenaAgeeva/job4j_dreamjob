package dreamjob.repository;

import dreamjob.model.City;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@ThreadSafe
public class MemoryCityRepository implements CityRepository {
    private final Map<Integer, City> cities = new ConcurrentHashMap<>() {
        {
            put(1, new City(1, "Москва"));
            put(2, new City(2, "Санкт-Петербург"));
            put(3, new City(3, "Екатеринбург"));
            put(4, new City(4, "Новосибирск"));
            put(5, new City(5, "Казань"));
            put(6, new City(6, "Челябинск"));
            put(7, new City(7, "Омск"));
            put(8, new City(8, "Самара"));
            put(9, new City(9, "Ростов-на-Дону"));
            put(10, new City(10, "Уфа"));
            put(11, new City(11, "Волгоград"));
            put(12, new City(12, "Красноярск"));
            put(13, new City(13, "Пермь"));
            put(14, new City(14, "Воронеж"));
            put(15, new City(15, "Саратов"));
            put(16, new City(16, "Тольятти"));
            put(17, new City(17, "Ижевск"));
            put(18, new City(18, "Барнаул"));
            put(19, new City(19, "Ульяновск"));
            put(20, new City(20, "Хабаровск"));
        }
    };

    @Override
    public Collection<City> findAll() {
        return cities.values();
    }
}