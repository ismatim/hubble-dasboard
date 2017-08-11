package hubble.backend.business.services.interfaces;

import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.backend.business.services.models.AvailabilityTransactionAvgDto;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.business.services.models.TransactionDto;
import java.util.List;

public interface PerformanceService {

    public ApplicationDto findApplicationById(String applicationId);

    public TransactionDto findTransactionById(String transactionId);

    public List<TransactionDto> findTransactionsByApplicationId(String applicationId);

    public List<ApplicationDto> findAllApplications();

    public List<PerformanceDto> findAllPerformances();

    public PerformanceDto findPerformanceById(String id);

    public List<PerformanceDto> findPerformanceByApplicationId(String applicationId);

    public List<PerformanceDto> findPerformanceByTransactionId(String transactionId);

    public List<PerformanceDto> findLast10MinutesPerformances();

    public List<PerformanceDto> findLastHourPerformances();

    public List<PerformanceDto> findLast10MinutesPerformanceByApplicationId(String applicationId);

    public List<PerformanceDto> findLast10MinutesPerformanceByTransactionId(String transactionId);

    public List<PerformanceDto> findLastHourPerformanceByApplicationId(String applicationId);

    public List<PerformanceDto> findLastHourPerformanceByTransactionId(String transactionId);

    public List<PerformanceDto> findLastDayPerformances();

    public List<PerformanceDto> findLastMonthPerformances();

    public List<PerformanceDto> findLastDayPerformanceByApplicationId(String applicationId);

    public List<PerformanceDto> findLastDayPerformanceByTransactionId(String transactionId);

    public List<PerformanceDto> findLastMonthPerformanceByApplicationId(String applicationId);

    public List<PerformanceDto> findLastMonthPerformanceByTransactionId(String transactionId);

    public AvailabilityApplicationAvgDto calculateLastDayAverageApplicationPerformance(String applicationId);

    public AvailabilityTransactionAvgDto calculateLastDayAverageTransactionPerformance(String transactionId);

    public AvailabilityApplicationAvgDto calculateLastMonthAverageApplicationPerformance(String applicationId);

    public AvailabilityTransactionAvgDto calculateLastMonthAverageTransactionPerformance(String transactionId);

    public AvailabilityApplicationAvgDto calculateLast10MinutesAverageApplicationPerformance(String applicationId);

    public AvailabilityTransactionAvgDto calculateLast10MinutesAverageTransactionPerformance(String transactionId);

    public AvailabilityApplicationAvgDto calculateLastHourAverageApplicationPerformance(String applicationId);

    public AvailabilityTransactionAvgDto calculateLastHourAverageTransactionPerformance(String transactionId);

}
