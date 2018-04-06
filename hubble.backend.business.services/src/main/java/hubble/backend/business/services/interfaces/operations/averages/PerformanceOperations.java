package hubble.backend.business.services.interfaces.operations.averages;

import hubble.backend.business.services.models.Application;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.core.enums.MonitoringFields;

public interface PerformanceOperations extends
        AverageOperationsBase<ApplicationIndicators> {

    public MonitoringFields.STATUS calculateStatus(Application appAvg, Double avgPerformance);
}
