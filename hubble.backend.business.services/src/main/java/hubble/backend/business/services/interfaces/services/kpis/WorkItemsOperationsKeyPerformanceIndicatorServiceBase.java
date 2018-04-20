package hubble.backend.business.services.interfaces.services.kpis;

import hubble.backend.business.services.models.measures.kpis.WorkItemsKpi;

public interface WorkItemsOperationsKeyPerformanceIndicatorServiceBase extends OperationsKeyPerformanceIndicatorServiceBase<WorkItemsKpi> {

    public WorkItemsKpi calculateLastWeekKpiByApplication(String applicationId);

}
