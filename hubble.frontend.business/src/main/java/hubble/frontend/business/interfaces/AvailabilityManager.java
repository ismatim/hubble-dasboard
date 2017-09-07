package hubble.frontend.business.interfaces;

import hubble.frontend.business.models.Availability;
import hubble.frontend.business.models.BusinessApplication;
import hubble.frontend.business.models.Transaction;
import java.util.List;

public interface AvailabilityManager {

    public Availability getAvailabilityById(String id);

    public List<Availability> getAllAvailabilities();

    public List<Availability> getAvailabilitiesByApplicationId(String applicationId);

    public List<Availability> getAvailabilitiesByTransactionId(String transactionId);

    public List<Availability> getLast10MinutesAvailabilities();

    public List<Availability> getLastHourAvailabilities();

    public List<Availability> getLast10MinutesAvailabilitiesByApplicationId(String applicationId);

    public List<Availability> getLast10MinutesAvailabilitiesByTransactionId(String transactionId);

    public List<Availability> getLastHourAvailabilitiesByApplicationId(String applicationId);

    public List<Availability> getLastHourAvailabilitiesByTransactionId(String transactionId);

    public BusinessApplication getBusinessApplicationById(String applicationId);

    public List<BusinessApplication> getAllApplications();

    public Transaction getBusinessTransactionById(String applicationId);

    public List<Transaction> getTransactionsByApplication(String applicationId);
}
