package hubble.backend.business.services.interfaces;

import hubble.backend.business.services.models.ApplicationAvailabilityAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.TransactionAvailabilityAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import java.util.List;

public interface AvailabilityService {
    public ApplicationDto findApplicationById(String applicationId);
    public TransactionDto findTransactionById(String transactionId);
    public List<TransactionDto> findTransactionsByApplicationId(String applicationId);
    public List<ApplicationDto> findAllApplications();
    public List<TransactionDto> findAllTransactions();
    public List<AvailabilityDto> findAllAvailabilities();
    public AvailabilityDto findAvailabilityById(String id);
    public List<AvailabilityDto> findAvailabilitiesByApplicationId(String applicationId);
    public List<AvailabilityDto> findAvailabilitiesByTransactionId(String transactionId);
    public List<AvailabilityDto> findLast10MinutesAvailabilities();
    public List<AvailabilityDto> findLastHourAvailabilities();
    public List<AvailabilityDto> findLast10MinutesAvailabilitiesByApplicationId(String applicationId);
    public List<AvailabilityDto> findLast10MinutesAvailabilitiesByTransactionId(String transactionId);
    public List<AvailabilityDto> findLastHourAvailabilitiesByApplicationId(String applicationId);
    public List<AvailabilityDto> findLastHourAvailabilitiesByTransactionId(String transactionId);
    public ApplicationAvailabilityAvgDto calculateLast10MinutesAverageApplicationAvailability(String applicationId);
    public TransactionAvailabilityAvgDto calculateLast10MinutesAverageTransactionAvailability(String transactionId);
    public ApplicationAvailabilityAvgDto calculateLastHourAverageApplicationAvailability(String applicationId);    
    public TransactionAvailabilityAvgDto calculateLastHourAverageTransactionAvailability(String transactionId);    
}
