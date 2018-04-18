package hubble.backend.business.services.interfaces.operations.kpis;

import hubble.backend.business.services.models.measures.kpis.PerformanceKpi;
import hubble.backend.business.services.models.measures.rules.PerformanceGroupRule;

public interface PerformanceKpiOperations extends
        KeyPerformanceIndicatorOperationsBase<PerformanceGroupRule> {

    public PerformanceKpi calculateLast10MinutesKeyPerformanceIndicatorByApplication(String applicationId);

    public PerformanceKpi calculateLastHourKeyPerformanceIndicatorByApplication(String applicationId);

    public PerformanceKpi calculateLastDayKeyPerformanceIndicatorByApplication(String applicationId);

    public PerformanceKpi calculateLastMonthKeyPerformanceIndicatorByApplication(String applicationId);

}
