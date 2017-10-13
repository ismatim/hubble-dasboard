package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.business.services.models.TransactionAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import java.util.List;

public interface TransactionService {

    public TransactionDto findTransactionById(String transactionId);

    public List<TransactionDto> findTransactionsByApplicationId(String applicationId);

    public List<AvailabilityDto> findAvailabilitiesByTransactionId(String transactionId);

    public List<AvailabilityDto> findLast10MinutesAvailabilitiesByTransactionId(String transactionId);

    public List<AvailabilityDto> findLastHourAvailabilitiesByTransactionId(String transactionId);

    public List<AvailabilityDto> findLastDayAvailabilitiesByTransactionId(String transactionId);

    public List<AvailabilityDto> findLastMonthAvailabilitiesByTransactionId(String transactionId);

    public TransactionAvgDto calculateLast10MinutesAverageTransactionAvailability(String transactionId);

    public TransactionAvgDto calculateLastHourAverageTransactionAvailability(String transactionId);

    public TransactionAvgDto calculateLastDayAverageTransactionAvailability(String transactionId);

    public TransactionAvgDto calculateLastMonthAverageTransactionAvailability(String transactionId);

    public List<PerformanceDto> findPerformanceByTransactionId(String transactionId);

    public List<PerformanceDto> findLast10MinutesPerformanceByTransactionId(String transactionId);

    public List<PerformanceDto> findLastHourPerformanceByTransactionId(String transactionId);

    public List<PerformanceDto> findLastDayPerformanceByTransactionId(String transactionId);

    public List<PerformanceDto> findLastMonthPerformanceByTransactionId(String transactionId);

    public TransactionAvgDto calculateLastDayAverageTransactionPerformance(String transactionId);

    public TransactionAvgDto calculateLastMonthAverageTransactionPerformance(String transactionId);

    public TransactionAvgDto calculateLast10MinutesAverageTransactionPerformance(String transactionId);

    public TransactionAvgDto calculateLastHourAverageTransactionPerformance(String transactionId);
}
