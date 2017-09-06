package hubble.backend.business.services.implementations.services;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.services.PerformanceService;
import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.AvailabilityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import hubble.backend.business.services.interfaces.operations.PerformanceOperations;

@Component
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    PerformanceOperations performanceOperation;
    @Autowired
    MapperConfiguration mapper;

    @Override
    public List<PerformanceDto> getAll() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAll();
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public PerformanceDto getById(String id) {
        return mapper.mapToPerformanceDto(availabilityRepository.findOne(id));
    }

    @Override
    public List<PerformanceDto> getLast10Minutes() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.TEN_MINUTES);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> getLastHour() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.ONE_HOUR);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> getLastDay() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.ONE_DAY);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> getLastMonth() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMonths(1);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public ApplicationAvgDto calculateLast10MinutesAverageByApplication(String applicationId) {
        return performanceOperation.calculateLast10MinutesAverageByApplication(applicationId);
    }

    @Override
    public ApplicationAvgDto calculateLastHourAverageByApplication(String applicationId) {
        return performanceOperation.calculateLastHourAverageByApplication(applicationId);
    }

    @Override
    public ApplicationAvgDto calculateLastDayAverageByApplication(String applicationId) {
        return performanceOperation.calculateLastDayAverageByApplication(applicationId);
    }

    @Override
    public ApplicationAvgDto calculateLastMonthAverageByApplication(String applicationId) {
        return performanceOperation.calculateLastMonthAverageByApplication(applicationId);
    }

    @Override
    public ApplicationDto getApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        return mapper.mapToApplicationDto(applicationStorage);
    }

    @Override
    public List<PerformanceDto> getAll(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationId(applicationId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> getLast10Minutes(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, applicationId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> getLastHour(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, applicationId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> getLastDay(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, applicationId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> getLastMonth(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(CalendarHelper.ONE_MONTH, applicationId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<ApplicationDto> getAllApplications() {
        List<ApplicationStorage> applicationsStorage = applicationRepository.findAll();
        return mapper.mapToApplicationDtoList(applicationsStorage);
    }
}
