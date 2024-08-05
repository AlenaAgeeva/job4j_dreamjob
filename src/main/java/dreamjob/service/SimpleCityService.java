package dreamjob.service;

import dreamjob.model.City;
import dreamjob.repository.CityRepository;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@ThreadSafe
public class SimpleCityService implements CityService {
    private final CityRepository cityRepository;

    public SimpleCityService(CityRepository sql2oCityRepository) {
        this.cityRepository = sql2oCityRepository;
    }

    @Override
    public Collection<City> findAll() {
        return cityRepository.findAll();
    }
}
