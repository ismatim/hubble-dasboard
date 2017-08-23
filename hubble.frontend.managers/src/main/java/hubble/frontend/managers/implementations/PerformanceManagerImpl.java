package hubble.frontend.managers.implementations;

import hubble.backend.business.services.interfaces.services.PerformanceService;
import hubble.backend.business.services.interfaces.services.TransactionService;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.frontend.managers.configurations.mappers.ApplicationMapper;
import hubble.frontend.managers.configurations.mappers.PerformanceMapper;
import hubble.frontend.managers.configurations.mappers.TransactionMapperConfiguration;
import hubble.frontend.managers.interfaces.PerformanceManager;
import hubble.frontend.managers.models.BusinessApplication;
import hubble.frontend.managers.models.Performance;
import hubble.frontend.managers.models.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerformanceManagerImpl implements PerformanceManager {

    @Autowired
    PerformanceMapper performanceMapper;
    @Autowired
    ApplicationMapper applicationMapper;
    @Autowired
    TransactionMapperConfiguration transactionMapper;
    @Autowired
    TransactionService transactionService;

    @Autowired
    PerformanceService performanceService;

    @Override
    public Performance getPerformanceById(String id) {
        PerformanceDto performanceDto = performanceService.getById(id);
        Performance a = performanceMapper.mapToPerformance(performanceDto);
        return a;
    }

    @Override
    public List<Performance> getAllPerformances() {
        List<PerformanceDto> performanceDtoList = performanceService.getAll();
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getPerformanceByApplicationId(String applicationId) {
        List<PerformanceDto> performanceDtoList = performanceService.getAll(applicationId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getPerformanceByTransactionId(String transactionId) {
        List<PerformanceDto> performanceDtoList = transactionService.findPerformanceByTransactionId(transactionId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getLast10MinutesPerformance() {
        List<PerformanceDto> performanceDtoList = performanceService.getLast10Minutes();
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getLastHourPerformance() {
        List<PerformanceDto> performanceDtoList = performanceService.getLastHour();
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getLast10MinutesPerformanceByApplicationId(String applicationId) {
        List<PerformanceDto> performanceDtoList = performanceService.getLast10Minutes(applicationId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getLast10MinutesPerformanceByTransactionId(String transactionId) {
        List<PerformanceDto> performanceDtoList = transactionService.findLast10MinutesPerformanceByTransactionId(transactionId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getLastHourPerformanceByApplicationId(String applicationId) {
        List<PerformanceDto> performanceDtoList = performanceService.getLastHour(applicationId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getLastHourPerformanceByTransactionId(String transactionId) {
        List<PerformanceDto> performanceDtoList = transactionService.findLastHourPerformanceByTransactionId(transactionId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public BusinessApplication getBusinessApplicationById(String applicationId) {
        ApplicationDto applicationDto = performanceService.getApplication(applicationId);
        return applicationMapper.mapToBusinessApplication(applicationDto);
    }

    @Override
    public List<BusinessApplication> getAllApplications() {
        List<ApplicationDto> applicationDtoList = performanceService.getAllApplications();
        return applicationMapper.mapToBusinessApplicationList(applicationDtoList);
    }

    @Override
    public Transaction getBusinessTransactionById(String transactionId) {
        TransactionDto transactionDto = transactionService.findTransactionById(transactionId);
        return transactionMapper.mapToTransaction(transactionDto);
    }

    @Override
    public List<Transaction> getTransactionsByApplication(String applicationId) {
        List<TransactionDto> transactionDtoList = transactionService.findTransactionsByApplicationId(applicationId);
        return transactionMapper.mapToTransactionList(transactionDtoList);
    }

    @Override
    public List<Performance> getLastDayPerformances() {
        List<PerformanceDto> performanceDtoList = performanceService.getLastDay();
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getLastMonthPerformances() {
        List<PerformanceDto> performanceDtoList = performanceService.getLastMonth();
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getLastDayPerformanceByApplicationId(String applicationId) {
        List<PerformanceDto> performanceDtoList = performanceService.getLastDay(applicationId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getLastDayPerformanceByTransactionId(String transactionId) {
        List<PerformanceDto> performanceDtoList = transactionService.findLastDayPerformanceByTransactionId(transactionId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getLastMonthPerformanceByApplicationId(String applicationId) {
        List<PerformanceDto> performanceDtoList = performanceService.getLastMonth(applicationId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

    @Override
    public List<Performance> getLastMonthPerformanceByTransactionId(String transactionId) {
        List<PerformanceDto> performanceDtoList = transactionService.findLastMonthPerformanceByTransactionId(transactionId);
        return performanceMapper.mapToPerformanceList(performanceDtoList);
    }

}
