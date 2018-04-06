package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.models.Availability;
import hubble.backend.business.services.models.Performance;
import hubble.backend.business.services.models.TransactionAvg;
import hubble.backend.business.services.models.Transaction;
import java.util.List;

public interface TransactionService {

    public Transaction findTransactionById(String transactionId);

    public List<Transaction> findTransactionsByApplicationId(String applicationId);

    public List<Availability> findAvailabilitiesByTransactionId(String transactionId);

    public List<Availability> findLast10MinutesAvailabilitiesByTransactionId(String transactionId);

    public List<Availability> findLastHourAvailabilitiesByTransactionId(String transactionId);

    public List<Availability> findLastDayAvailabilitiesByTransactionId(String transactionId);

    public List<Availability> findLastMonthAvailabilitiesByTransactionId(String transactionId);

    public TransactionAvg calculateLast10MinutesAverageTransactionAvailability(String transactionId);

    public TransactionAvg calculateLastHourAverageTransactionAvailability(String transactionId);

    public TransactionAvg calculateLastDayAverageTransactionAvailability(String transactionId);

    public TransactionAvg calculateLastMonthAverageTransactionAvailability(String transactionId);

    public List<Performance> findPerformanceByTransactionId(String transactionId);

    public List<Performance> findLast10MinutesPerformanceByTransactionId(String transactionId);

    public List<Performance> findLastHourPerformanceByTransactionId(String transactionId);

    public List<Performance> findLastDayPerformanceByTransactionId(String transactionId);

    public List<Performance> findLastMonthPerformanceByTransactionId(String transactionId);

    public TransactionAvg calculateLastDayAverageTransactionPerformance(String transactionId);

    public TransactionAvg calculateLastMonthAverageTransactionPerformance(String transactionId);

    public TransactionAvg calculateLast10MinutesAverageTransactionPerformance(String transactionId);

    public TransactionAvg calculateLastHourAverageTransactionPerformance(String transactionId);
}
