package hubble.frontend.managers.interfaces;

import hubble.frontend.managers.models.collections.Availability;
import hubble.frontend.managers.models.aggregations.BusinessApplicationAvg;
import hubble.frontend.managers.models.aggregations.TransactionAvg;
import hubble.frontend.managers.models.entities.BusinessApplication;
import hubble.frontend.managers.models.entities.Transaction;
import java.util.List;

public interface AvailabilityManager {

    public Availability findAvailabilityById(String id);
    public List<Availability> findAllAvailabilities();
    public List<Availability> findAvailabilitiesByApplicationId(String applicationId);
    public List<Availability> findAvailabilitiesByTransactionId(String transactionId);
    public List<Availability> findLast10MinutesAvailabilities();
    public List<Availability> findLastHourAvailabilities();
    public List<Availability> findLastDayAvailabilities();
    public List<Availability> findLastMonthAvailabilities();
    public List<Availability> findLast10MinutesAvailabilitiesByApplicationId(String applicationId);
    public List<Availability> findLast10MinutesAvailabilitiesByTransactionId(String transactionId);
    public List<Availability> findLastHourAvailabilitiesByApplicationId(String applicationId);
    public List<Availability> findLastHourAvailabilitiesByTransactionId(String transactionId);
    public List<Availability> findLastDayAvailabilitiesByApplicationId(String applicationId);
    public List<Availability> findLastDayAvailabilitiesByTransactionId(String transactionId);
    public List<Availability> findLastMonthAvailabilitiesByApplicationId(String applicationId);
    public List<Availability> findLastMonthAvailabilitiesByTransactionId(String transactionId);
    public BusinessApplicationAvg findLast10MinutesAverageByApplication(String applicationId);
    public BusinessApplicationAvg findLastHourAverageByApplication(String applicationId);
    public BusinessApplicationAvg findLastDayAverageByApplication(String applicationId);
    public BusinessApplicationAvg findLastMonthAverageByApplication(String applicationId);
    public TransactionAvg findLast10MinutesAverageByTransaction(String transactionId);
    public TransactionAvg findLastHourAverageByTransaction(String transactionId);
    public TransactionAvg findLastDayAverageByTransaction(String transactionId);
    public TransactionAvg findLastMonthAverageByTransaction(String transactionId);
    public BusinessApplication findBusinessApplicationById(String applicationId);
    public List<BusinessApplication> findAllApplications();
    public Transaction findBusinessTransactionById(String applicationId);
    public List<Transaction> findTransactionsByApplication(String applicationId);
}
