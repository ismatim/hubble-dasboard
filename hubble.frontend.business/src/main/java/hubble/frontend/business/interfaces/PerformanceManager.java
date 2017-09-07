package hubble.frontend.business.interfaces;

import hubble.frontend.business.models.BusinessApplication;
import hubble.frontend.business.models.Performance;
import hubble.frontend.business.models.Transaction;
import java.util.List;

public interface PerformanceManager {

    public Performance getPerformanceById(String id);

    public List<Performance> getAllPerformances();

    public List<Performance> getPerformanceByApplicationId(String applicationId);

    public List<Performance> getPerformanceByTransactionId(String transactionId);

    public List<Performance> getLast10MinutesPerformance();

    public List<Performance> getLastHourPerformance();

    public List<Performance> getLast10MinutesPerformanceByApplicationId(String applicationId);

    public List<Performance> getLast10MinutesPerformanceByTransactionId(String transactionId);

    public List<Performance> getLastHourPerformanceByApplicationId(String applicationId);

    public List<Performance> getLastHourPerformanceByTransactionId(String transactionId);

    public List<Performance> getLastDayPerformances();

    public List<Performance> getLastMonthPerformances();

    public List<Performance> getLastDayPerformanceByApplicationId(String applicationId);

    public List<Performance> getLastDayPerformanceByTransactionId(String transactionId);

    public List<Performance> getLastMonthPerformanceByApplicationId(String applicationId);

    public List<Performance> getLastMonthPerformanceByTransactionId(String transactionId);

    public BusinessApplication getBusinessApplicationById(String applicationId);

    public List<BusinessApplication> getAllApplications();

    public Transaction getBusinessTransactionById(String transactionId);

    public List<Transaction> getTransactionsByApplication(String applicationId);
}
