package hubble.frontend.managers.interfaces;

import hubble.frontend.managers.models.collections.Availability;
import hubble.frontend.managers.models.aggregations.AvailabilityBusinessApplicationAvg;
import hubble.frontend.managers.models.aggregations.AvailabilityTransactionAvg;
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
    public List<Availability> findLast10MinutesAvailabilitiesByApplicationId(String applicationId);
    public List<Availability> findLast10MinutesAvailabilitiesByTransactionId(String transactionId);
    public List<Availability> findLastHourAvailabilitiesByApplicationId(String applicationId);
    public List<Availability> findLastHourAvailabilitiesByTransactionId(String transactionId);
    public AvailabilityBusinessApplicationAvg findLast10MinutesAverageByApplication(String applicationId);
    public AvailabilityBusinessApplicationAvg findLastHourAverageByApplication(String applicationId);
    public AvailabilityTransactionAvg findLast10MinutesAverageByTransaction(String transactionId);
    public AvailabilityTransactionAvg findLastHourAverageByTransaction(String transactionId);
    public BusinessApplication findBusinessApplicationById(String applicationId);
    public List<BusinessApplication> findAllApplications();
    public Transaction findBusinessTransactionById(String applicationId);
    public List<Transaction> findTransactionsByApplication(String applicationId);
}
