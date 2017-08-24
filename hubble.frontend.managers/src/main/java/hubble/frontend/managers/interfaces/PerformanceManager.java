package hubble.frontend.managers.interfaces;

import hubble.frontend.managers.models.aggregations.BusinessApplicationAvg;
import hubble.frontend.managers.models.aggregations.TransactionAvg;
import hubble.frontend.managers.models.entities.BusinessApplication;
import hubble.frontend.managers.models.collections.Performance;
import hubble.frontend.managers.models.entities.Transaction;
import java.util.List;

public interface PerformanceManager {
    public Performance findPerformanceById(String id);
    public List<Performance> findAllPerformances();
    public List<Performance> findPerformanceByApplicationId(String applicationId);
    public List<Performance> findPerformanceByTransactionId(String transactionId);
    public List<Performance> findLast10MinutesPerformance();
    public List<Performance> findLastHourPerformance();
    public List<Performance> findLast10MinutesPerformanceByApplicationId(String applicationId);
    public List<Performance> findLast10MinutesPerformanceByTransactionId(String transactionId);
    public List<Performance> findLastHourPerformanceByApplicationId(String applicationId);    
    public List<Performance> findLastHourPerformanceByTransactionId(String transactionId);    
    public List<Performance> findLastDayPerformances();
    public List<Performance> findLastMonthPerformances();
    public List<Performance> findLastDayPerformanceByApplicationId(String applicationId);
    public List<Performance> findLastDayPerformanceByTransactionId(String transactionId);
    public List<Performance> findLastMonthPerformanceByApplicationId(String applicationId);
    public List<Performance> findLastMonthPerformanceByTransactionId(String transactionId);
    public BusinessApplicationAvg findLastDayAverageByApplication(String applicationId);
    public TransactionAvg findLastDayAverageByTransaction(String transactionId);
    public BusinessApplicationAvg findLastMonthAverageByApplication(String applicationId);
    public TransactionAvg findLastMonthAverageByTransaction(String transactionId);
    public BusinessApplicationAvg findLast10MinutesAverageByApplication(String applicationId);
    public BusinessApplicationAvg findLastHourAverageByApplication(String applicationId);
    public TransactionAvg findLast10MinutesAverageByTransaction(String transactionId);
    public TransactionAvg findLastHourAverageByTransaction(String transactionId);
    public BusinessApplication findBusinessApplicationById(String applicationId);
    public List<BusinessApplication> findAllApplications();  
    public Transaction findBusinessTransactionById(String transactionId);
    public List<Transaction> findTransactionsByApplication(String applicationId);
}
