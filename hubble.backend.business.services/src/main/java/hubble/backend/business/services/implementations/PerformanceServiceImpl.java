package hubble.backend.business.services.implementations;

import hubble.backend.business.services.configurations.mappers.DtoMapperConfiguration;
import hubble.backend.business.services.interfaces.PerformanceService;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.backend.business.services.models.AvailabilityTransactionAvgDto;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.HubbleConstants;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.models.TransactionStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.AvailabilityRepository;
import hubble.backend.storage.repositories.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerformanceServiceImpl implements PerformanceService{
    
    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    DtoMapperConfiguration mapper;
    @Override
    public List<PerformanceDto> findAllPerformances() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAll();
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public PerformanceDto findPerformanceById(String id) {
        return mapper.mapToPerformanceDto(availabilityRepository.findOne(id));
    }

    @Override
    public List<PerformanceDto> findPerformanceByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationId(applicationId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionId(transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLast10MinutesPerformances() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(HubbleConstants.TEN_MINUTES);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLastHourPerformances() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(HubbleConstants.ONE_HOUR);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLast10MinutesPerformanceByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, applicationId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLast10MinutesPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLastHourPerformanceByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_HOUR, applicationId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLastHourPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_HOUR, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public AvailabilityApplicationAvgDto calculateLast10MinutesAverageApplicationPerformance(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, applicationId);
        AvailabilityApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        if (availabilityStorageList.isEmpty()) {//No data for last 10 minutes
            applicationAvailabilityAvg.setAverage(null);
            applicationAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        } else {
            applicationAvailabilityAvg.setAverage(calculateAveragePerformance(availabilityStorageList));
            applicationAvailabilityAvg.setStatus(calculatePerformanceStatus(applicationAvailabilityAvg.getAvailabilityThreshold(), applicationAvailabilityAvg.getAverage()));
        }
        return applicationAvailabilityAvg;
    }

    @Override
    public AvailabilityTransactionAvgDto calculateLast10MinutesAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        AvailabilityTransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (availabilityStorageList.isEmpty()) {//No data for last 10 minutes
            transactionAvailabilityAvg.setAverage(null);
            transactionAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        } else {
            transactionAvailabilityAvg.setAverage(calculateAveragePerformance(availabilityStorageList));
            transactionAvailabilityAvg.setStatus(calculatePerformanceStatus(parentApplicationDto.getAvailabilityThreshold(), transactionAvailabilityAvg.getAverage()));
        }
        return transactionAvailabilityAvg;
    }

    @Override
    public AvailabilityApplicationAvgDto calculateLastHourAverageApplicationPerformance(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_HOUR, applicationId);
        AvailabilityApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        if (availabilityStorageList.isEmpty()) {//No data for last hour
            applicationAvailabilityAvg.setAverage(null);
            applicationAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        } else {
            applicationAvailabilityAvg.setAverage(calculateAveragePerformance(availabilityStorageList));
            applicationAvailabilityAvg.setStatus(calculatePerformanceStatus(applicationAvailabilityAvg.getAvailabilityThreshold(), applicationAvailabilityAvg.getAverage()));
        }
        return applicationAvailabilityAvg;
    }

    @Override
    public AvailabilityTransactionAvgDto calculateLastHourAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        AvailabilityTransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_HOUR, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (availabilityStorageList.isEmpty()) {//No data for last hour
            transactionAvailabilityAvg.setAverage(null);
            transactionAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        } else {
            transactionAvailabilityAvg.setAverage(calculateAveragePerformance(availabilityStorageList));
            transactionAvailabilityAvg.setStatus(calculatePerformanceStatus(parentApplicationDto.getAvailabilityThreshold(), transactionAvailabilityAvg.getAverage()));
        }
        return transactionAvailabilityAvg;
    }

    private Integer calculateAveragePerformance(List<AvailabilityStorage> availabilityStorageList) {
        int totalAvailabilities = availabilityStorageList.size();
        if (totalAvailabilities == 0) {
            return null;
        }
        int averagePerformance = 0;
        for (AvailabilityStorage availability : availabilityStorageList) {
            averagePerformance += availability.getResponseTime();

        }

        return averagePerformance / totalAvailabilities;
    }

    private MonitoringFields.STATUS calculatePerformanceStatus(int availabilityThreshold, int avgAvailability) {
        if (avgAvailability >= availabilityThreshold) {
            return MonitoringFields.STATUS.SUCCESS;
        } else if (avgAvailability >= 0) {
            return MonitoringFields.STATUS.CRITICAL;
        } else {
            return MonitoringFields.STATUS.NO_DATA;
        }
    }

    @Override
    public List<PerformanceDto> findLastDayPerformances() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(HubbleConstants.ONE_DAY);
        return mapper.mapToPerformanceDtoList(availabilityStorageList); 
    }

    @Override
    public List<PerformanceDto> findLastMonthPerformances() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMonths(HubbleConstants.ONE_MONTH);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLastDayPerformanceByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_DAY, applicationId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLastDayPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_DAY, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLastMonthPerformanceByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(HubbleConstants.ONE_MONTH, applicationId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLastMonthPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(HubbleConstants.ONE_MONTH, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public AvailabilityApplicationAvgDto calculateLastDayAverageApplicationPerformance(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_DAY, applicationId);
        AvailabilityApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        if (availabilityStorageList.isEmpty()) {//No data for last day
            applicationAvailabilityAvg.setAverage(null);
            applicationAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        } else {
            applicationAvailabilityAvg.setAverage(calculateAveragePerformance(availabilityStorageList));
            applicationAvailabilityAvg.setStatus(calculatePerformanceStatus(applicationAvailabilityAvg.getAvailabilityThreshold(), applicationAvailabilityAvg.getAverage()));
        }
        return applicationAvailabilityAvg;
    }

    @Override
    public AvailabilityTransactionAvgDto calculateLastDayAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        AvailabilityTransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_DAY, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (availabilityStorageList.isEmpty()) {//No data for last day
            transactionAvailabilityAvg.setAverage(null);
            transactionAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        } else {
            transactionAvailabilityAvg.setAverage(calculateAveragePerformance(availabilityStorageList));
            transactionAvailabilityAvg.setStatus(calculatePerformanceStatus(parentApplicationDto.getAvailabilityThreshold(), transactionAvailabilityAvg.getAverage()));
        }
        return transactionAvailabilityAvg;
    }

    @Override
    public AvailabilityApplicationAvgDto calculateLastMonthAverageApplicationPerformance(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(HubbleConstants.ONE_MONTH, applicationId);
        AvailabilityApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        if (availabilityStorageList.isEmpty()) {//No data for last month
            applicationAvailabilityAvg.setAverage(null);
            applicationAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        } else {
            applicationAvailabilityAvg.setAverage(calculateAveragePerformance(availabilityStorageList));
            applicationAvailabilityAvg.setStatus(calculatePerformanceStatus(applicationAvailabilityAvg.getAvailabilityThreshold(), applicationAvailabilityAvg.getAverage()));
        }
        return applicationAvailabilityAvg;   
    }

    @Override
    public AvailabilityTransactionAvgDto calculateLastMonthAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        AvailabilityTransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(HubbleConstants.ONE_MONTH, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (availabilityStorageList.isEmpty()) {//No data for last month
            transactionAvailabilityAvg.setAverage(null);
            transactionAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        } else {
            transactionAvailabilityAvg.setAverage(calculateAveragePerformance(availabilityStorageList));
            transactionAvailabilityAvg.setStatus(calculatePerformanceStatus(parentApplicationDto.getAvailabilityThreshold(), transactionAvailabilityAvg.getAverage()));
        }
        return transactionAvailabilityAvg;
    }

    @Override
    public ApplicationDto findApplicationById(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        return mapper.mapToApplicationDto(applicationStorage);       
    }

    @Override
    public TransactionDto findTransactionById(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId); 
        return mapper.mapToTransactionDto(transactionStorage);
    }

    @Override
    public List<TransactionDto> findTransactionsByApplicationId(String applicationId) {
        return findApplicationById(applicationId).getTransactions();
    }

    @Override
    public List<ApplicationDto> findAllApplications() {
        List<ApplicationStorage> applicationStorageList = applicationRepository.findAllApplications();
        return mapper.mapToApplicationDtoList(applicationStorageList);  
    }
    
}
