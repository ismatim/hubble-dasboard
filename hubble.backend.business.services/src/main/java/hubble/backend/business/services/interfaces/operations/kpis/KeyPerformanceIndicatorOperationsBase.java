package hubble.backend.business.services.interfaces.operations.kpis;

import hubble.backend.core.enums.MonitoringFields;

public interface KeyPerformanceIndicatorOperationsBase<G> {

    public double calculateKeyPerformanceIndicator(G groupRuleMeasureModel);

    public MonitoringFields.STATUS calculateKpiStatus(Double measure);
}
