package hubble.backend.business.services.interfaces.operations.kpis;

import hubble.backend.business.services.models.measures.kpis.IssuesKpi;
import hubble.backend.business.services.models.measures.rules.IssuesGroupRule;

public interface IssuesKpiOperations extends
        KeyPerformanceIndicatorOperationsBase<IssuesKpi, IssuesGroupRule>, KpiThresholdSetup {

}
