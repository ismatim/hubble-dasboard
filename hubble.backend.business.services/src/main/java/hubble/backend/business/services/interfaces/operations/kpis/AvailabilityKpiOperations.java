package hubble.backend.business.services.interfaces.operations.kpis;

import hubble.backend.business.services.models.measures.kpis.AvailabilityKpi;
import hubble.backend.business.services.models.measures.rules.AvailabilityGroupRule;

public interface AvailabilityKpiOperations extends
        KeyPerformanceIndicatorOperationsBase<AvailabilityGroupRule> {

    public AvailabilityKpi calculateLast10MinutesKeyPerformanceIndicatorByApplication(String applicationId);

    public AvailabilityKpi calculateLastHourKeyPerformanceIndicatorByApplication(String applicationId);

    public AvailabilityKpi calculateLastDayKeyPerformanceIndicatorByApplication(String applicationId);

    public AvailabilityKpi calculateLastMonthKeyPerformanceIndicatorByApplication(String applicationId);

}
