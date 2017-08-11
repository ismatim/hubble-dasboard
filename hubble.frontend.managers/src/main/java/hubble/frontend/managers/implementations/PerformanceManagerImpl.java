package hubble.frontend.managers.implementations;

import hubble.backend.business.services.interfaces.PerformanceService;
import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.frontend.managers.interfaces.PerformanceManager;
import hubble.frontend.managers.models.entities.BusinessApplication;
import hubble.frontend.managers.models.collections.Performance;
import hubble.frontend.managers.models.aggregations.PerformanceApplicationAvg;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.business.services.models.AvailabilityTransactionAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.frontend.managers.configurations.mappers.ManagerApplicationMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.ManagerPerformanceMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.ManagerTransactionMapperConfiguration;
import hubble.frontend.managers.models.aggregations.PerformanceTransactionAvg;
import hubble.frontend.managers.models.entities.Transaction;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerformanceManagerImpl implements PerformanceManager{
    
    @Autowired
    ManagerPerformanceMapperConfiguration performanceMapper;
    @Autowired
    ManagerApplicationMapperConfiguration applicationMapper;
    @Autowired
    ManagerTransactionMapperConfiguration transactionMapper;

    @Autowired
    PerformanceService performanceService;

    
    @Override
    public Performance findPerformanceById(String id) {
        PerformanceDto performanceDto = performanceService.findPerformanceById(id);
        Performance a = performanceMapper.mapToPerformance(performanceDto);
        return a;
    }

    @Override
    public List<Performance> findAllPerformances() {
        List<PerformanceDto> performanceDtoList = performanceService.findAllPerformances();
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findPerformanceByApplicationId(String applicationId) {
        List<PerformanceDto> performanceDtoList = performanceService.findPerformanceByApplicationId(applicationId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findPerformanceByTransactionId(String transactionId) {
        List<PerformanceDto> performanceDtoList = performanceService.findPerformanceByTransactionId(transactionId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findLast10MinutesPerformance() {
        List<PerformanceDto> performanceDtoList = performanceService.findLast10MinutesPerformances();
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findLastHourPerformance() {
        List<PerformanceDto> performanceDtoList = performanceService.findLastHourPerformances();
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findLast10MinutesPerformanceByApplicationId(String applicationId) {
        List<PerformanceDto> performanceDtoList = performanceService.findLast10MinutesPerformanceByApplicationId(applicationId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findLast10MinutesPerformanceByTransactionId(String transactionId) {
        List<PerformanceDto> performanceDtoList = performanceService.findLast10MinutesPerformanceByTransactionId(transactionId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findLastHourPerformanceByApplicationId(String applicationId) {
        List<PerformanceDto> performanceDtoList = performanceService.findLastHourPerformanceByApplicationId(applicationId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findLastHourPerformanceByTransactionId(String transactionId) {
        List<PerformanceDto> performanceDtoList = performanceService.findLastHourPerformanceByTransactionId(transactionId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public PerformanceApplicationAvg findLast10MinutesAverageByApplication(String applicationId) {
        AvailabilityApplicationAvgDto performanceAvgDto = performanceService.calculateLast10MinutesAverageApplicationPerformance(applicationId);
        return performanceMapper.mapToPerformanceApplicationAvg(performanceAvgDto);
    }

    @Override
    public PerformanceApplicationAvg findLastHourAverageByApplication(String applicationId) {
        AvailabilityApplicationAvgDto performanceAvgDto = performanceService.calculateLastHourAverageApplicationPerformance(applicationId);
        return performanceMapper.mapToPerformanceApplicationAvg(performanceAvgDto);
    }

    @Override
    public PerformanceTransactionAvg findLast10MinutesAverageByTransaction(String transactionId) {
        AvailabilityTransactionAvgDto performanceAvgDto = performanceService.calculateLast10MinutesAverageTransactionPerformance(transactionId);
        return performanceMapper.mapToPerformanceTransactionAvg(performanceAvgDto);
    }

    @Override
    public PerformanceTransactionAvg findLastHourAverageByTransaction(String transactionId) {
        AvailabilityTransactionAvgDto performanceAvgDto = performanceService.calculateLastHourAverageTransactionPerformance(transactionId);
        return performanceMapper.mapToPerformanceTransactionAvg(performanceAvgDto);
    }

    @Override
    public BusinessApplication findBusinessApplicationById(String applicationId) {
        ApplicationDto applicationDto = performanceService.findApplicationById(applicationId);
        return applicationMapper.mapToBusinessApplication(applicationDto);
    }

    @Override
    public List<BusinessApplication> findAllApplications() {
        List<ApplicationDto> applicationDtoList = performanceService.findAllApplications();
        return applicationMapper.mapToBusinessApplicationList(applicationDtoList);
    }

    @Override
    public Transaction findBusinessTransactionById(String transactionId) {
        TransactionDto transactionDto = performanceService.findTransactionById(transactionId);
        return transactionMapper.mapToBusinessTransaction(transactionDto);
    }

    @Override
    public List<Transaction> findTransactionsByApplication(String applicationId) {
        List<TransactionDto> transactionDtoList = performanceService.findTransactionsByApplicationId(applicationId);
        return transactionMapper.mapToBusinessTransactionList(transactionDtoList);
    }

    @Override
    public List<Performance> findLastDayPerformances() {
        List<PerformanceDto> performanceDtoList = performanceService.findLastDayPerformances();
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findLastMonthPerformances() {
        List<PerformanceDto> performanceDtoList = performanceService.findLastMonthPerformances();
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findLastDayPerformanceByApplicationId(String applicationId) {
        List<PerformanceDto> performanceDtoList = performanceService.findLastDayPerformanceByApplicationId(applicationId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findLastDayPerformanceByTransactionId(String transactionId) {
        List<PerformanceDto> performanceDtoList = performanceService.findLastDayPerformanceByTransactionId(transactionId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findLastMonthPerformanceByApplicationId(String applicationId) {
        List<PerformanceDto> performanceDtoList = performanceService.findLastMonthPerformanceByApplicationId(applicationId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> findLastMonthPerformanceByTransactionId(String transactionId) {
        List<PerformanceDto> performanceDtoList = performanceService.findLastMonthPerformanceByTransactionId(transactionId) ;
        return performanceMapper.mapToPerformanceList(performanceDtoList);    
    }

    @Override
    public PerformanceApplicationAvg findLastDayAverageByApplication(String applicationId) {
        AvailabilityApplicationAvgDto performanceAvgDto = performanceService.calculateLastDayAverageApplicationPerformance(applicationId);
        return performanceMapper.mapToPerformanceApplicationAvg(performanceAvgDto);   
    }

    @Override
    public PerformanceTransactionAvg findLastDayAverageByTransaction(String transactionId) {
        AvailabilityTransactionAvgDto performanceAvgDto = performanceService.calculateLastDayAverageTransactionPerformance(transactionId);
        return performanceMapper.mapToPerformanceTransactionAvg(performanceAvgDto);
    }

    @Override
    public PerformanceApplicationAvg findLastMonthAverageByApplication(String applicationId) {
        AvailabilityApplicationAvgDto performanceAvgDto = performanceService.calculateLastMonthAverageApplicationPerformance(applicationId);
        return performanceMapper.mapToPerformanceApplicationAvg(performanceAvgDto);   
    }

    @Override
    public PerformanceTransactionAvg findLastMonthAverageByTransaction(String transactionId) {
        AvailabilityTransactionAvgDto performanceAvgDto = performanceService.calculateLastMonthAverageTransactionPerformance(transactionId);
        return performanceMapper.mapToPerformanceTransactionAvg(performanceAvgDto);
    }
    
}
