package dreamjob.service;

import dreamjob.dto.FileDto;
import dreamjob.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

public interface VacancyService {
    Vacancy save(Vacancy vacancy, FileDto image);

    boolean deleteById(int id);

    boolean update(Vacancy vacancy, FileDto image);

    Optional<Vacancy> findById(int id);

    Collection<Vacancy> findAll();
}
