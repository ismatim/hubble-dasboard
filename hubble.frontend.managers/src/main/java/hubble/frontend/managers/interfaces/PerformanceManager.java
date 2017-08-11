package hubble.frontend.managers.interfaces;

import hubble.frontend.managers.models.entities.BusinessApplication;
import hubble.frontend.managers.models.collections.Performance;
import hubble.frontend.managers.models.aggregations.PerformanceApplicationAvg;
import hubble.frontend.managers.models.aggregations.PerformanceTransactionAvg;
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
    public PerformanceApplicationAvg findLastDayAverageByApplication(String applicationId);
    public PerformanceTransactionAvg findLastDayAverageByTransaction(String transactionId);
    public PerformanceApplicationAvg findLastMonthAverageByApplication(String applicationId);
    public PerformanceTransactionAvg findLastMonthAverageByTransaction(String transactionId);
    public PerformanceApplicationAvg findLast10MinutesAverageByApplication(String applicationId);
    public PerformanceApplicationAvg findLastHourAverageByApplication(String applicationId);
    public PerformanceTransactionAvg findLast10MinutesAverageByTransaction(String transactionId);
    public PerformanceTransactionAvg findLastHourAverageByTransaction(String transactionId);
    public BusinessApplication findBusinessApplicationById(String applicationId);
    public List<BusinessApplication> findAllApplications();  
    public Transaction findBusinessTransactionById(String transactionId);
    public List<Transaction> findTransactionsByApplication(String applicationId);
}
