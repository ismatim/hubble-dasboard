package hubble.backend.business.services.implementations;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import hubble.backend.business.services.interfaces.AvailabilityService;

@Component
public class AvailabilityServiceImpl implements AvailabilityService{

    @Autowired
    AvailabilityRepository repository;
    @Autowired
    MapperConfiguration mapper;
    List<AvailabilityStorage> availabilityStorageList;

    @Override
    public List<AvailabilityDto> findAllAvailabilities() {

        availabilityStorageList = repository.findAll();

        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public AvailabilityDto findAvailabilityById(String id) {

        return mapper.mapToAvailabilityDto(repository.findOne(id));

    }

    @Override
    public List<AvailabilityDto> findAvailabilitiesByApplicationId(String applicationId) {

       availabilityStorageList = repository.findAvailabilitiesByApplicationId(applicationId);

       return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLast10MinutesAvailabilities() {

        availabilityStorageList = repository.findAvailabilitiesByDurationMinutes(10);

        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastHourAvailabilities() {

        availabilityStorageList = repository.findAvailabilitiesByDurationMinutes(60);

        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLast10MinutesAvailabilitiesByApplicationId(String applicationId) {

        availabilityStorageList = repository.findAvailabilitiesByApplicationIdAndDurationMinutes(10, applicationId);

        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastHourAvailabilitiesByApplicationId(String applicationId) {

        availabilityStorageList = repository.findAvailabilitiesByApplicationIdAndDurationMinutes(60, applicationId);

        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }
}
