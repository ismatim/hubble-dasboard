package hubble.backend.business.services.interfaces.operations.kpis;

import hubble.backend.business.services.models.measures.kpis.IssuesKpi;
import hubble.backend.business.services.models.measures.rules.IssuesGroupRule;

public interface IssuesKpiOperations extends
        KeyPerformanceIndicatorOperationsBase<IssuesGroupRule>, KpiThresholdSetup {

    public IssuesKpi calculateLast10MinutesKeyPerformanceIndicatorByApplication(String applicationId);

    public IssuesKpi calculateLastHourKeyPerformanceIndicatorByApplication(String applicationId);

    public IssuesKpi calculateLastDayKeyPerformanceIndicatorByApplication(String applicationId);

    public IssuesKpi calculateLastMonthKeyPerformanceIndicatorByApplication(String applicationId);

}
