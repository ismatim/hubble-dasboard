package hubble.backend.business.services.interfaces.operations.kpis;

import hubble.backend.core.enums.MonitoringFields;

public interface KeyPerformanceIndicatorOperationsBase<T, G> {

    public double calculateKeyPerformanceIndicator(G groupRuleMeasureModel);

    public T calculateLast10MinutesKeyPerformanceIndicatorByApplication(String applicationId);

    public T calculateLastHourKeyPerformanceIndicatorByApplication(String applicationId);

    public T calculateLastDayKeyPerformanceIndicatorByApplication(String applicationId);

    public T calculateLastMonthKeyPerformanceIndicatorByApplication(String applicationId);

    public MonitoringFields.STATUS calculateKpiStatus(Double measure);
}
