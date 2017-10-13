package hubble.backend.business.services.implementations.services;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.services.AvailabilityService;
import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.AvailabilityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import hubble.backend.business.services.interfaces.operations.AvailabilityOperations;

@Component
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    AvailabilityOperations availavilityOperation;
    @Autowired
    MapperConfiguration mapper;

    @Override
    public List<AvailabilityDto> getAll() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAll();
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public AvailabilityDto get(String id) {
        return mapper.mapToAvailabilityDto(availabilityRepository.findOne(id));
    }

    @Override
    public List<AvailabilityDto> getLast10Minutes() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.TEN_MINUTES);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> getLastHour() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.ONE_HOUR);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> getLastDay() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.ONE_DAY);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> getLastMonth() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMonths(CalendarHelper.ONE_MONTH);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> getLast10Minutes(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> getLastHour(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> getLastDay(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> getLastMonth(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(CalendarHelper.ONE_MONTH, applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> getAll(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationId(applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public ApplicationDto getApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        return mapper.mapToApplicationDto(applicationStorage);
    }

    @Override
    public ApplicationAvgDto calculateLast10MinutesAverageByApplication(String applicationId) {
        return availavilityOperation.calculateLast10MinutesAverageByApplication(applicationId);
    }

    @Override
    public ApplicationAvgDto calculateLastHourAverageByApplication(String applicationId) {
        return availavilityOperation.calculateLastHourAverageByApplication(applicationId);
    }

    @Override
    public ApplicationAvgDto calculateLastDayAverageByApplication(String applicationId) {
        return availavilityOperation.calculateLastDayAverageByApplication(applicationId);
    }

    @Override
    public ApplicationAvgDto calculateLastMonthAverageByApplication(String applicationId) {
        return availavilityOperation.calculateLastMonthAverageByApplication(applicationId);
    }

    @Override
    public List<ApplicationDto> getAllApplications() {
        List<ApplicationStorage> applicationsStorage = applicationRepository.findAllApplications();
        return mapper.mapToApplicationDtoList(applicationsStorage);
    }

}
