package hubble.frontend.managers.interfaces;

import hubble.frontend.managers.models.aggregations.PerformanceApplicationAvg;
import hubble.frontend.managers.models.aggregations.PerformanceTransactionAvg;

public interface PerformanceManager {

    public PerformanceApplicationAvg findLastHourAverageByApplication(String applicationId);
    public PerformanceTransactionAvg findLastHourAverageByTransaction(String transactionId);

}
