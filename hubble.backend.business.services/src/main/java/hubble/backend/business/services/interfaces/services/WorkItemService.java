package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.interfaces.services.kpis.OperationsKeyPerformanceIndicatorServiceBase;
import hubble.backend.business.services.models.WorkItem;
import hubble.backend.business.services.models.measures.quantities.WorkItemQuantity;
import hubble.backend.business.services.models.measures.kpis.WorkItemsKpi;

public interface WorkItemService extends ServiceBase<WorkItem>,
        OperationsKeyPerformanceIndicatorServiceBase<WorkItemsKpi> {

    public WorkItemQuantity calculateWorkItemQuantityLastMonth(String applicationId);

    public WorkItemQuantity calculateWorkItemQuantityLastWeek(String applicationId);
}
