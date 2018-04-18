package hubble.backend.business.services.interfaces.operations;

import hubble.backend.business.services.interfaces.operations.kpis.KpiThresholdSetup;
import hubble.backend.business.services.models.measures.quantities.WorkItemQuantity;

public interface WorkItemOperations extends KpiThresholdSetup {

    public WorkItemQuantity calculateWorkItemQuantityLastMonth(String applicationId);

    public WorkItemQuantity calculateWorkItemQuantityLastWeek(String applicationId);
}
