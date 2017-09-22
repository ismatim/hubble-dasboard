package hubble.backend.business.services.interfaces.operations;

import hubble.backend.business.services.models.measures.WorkItemQuantity;

public interface WorkItemOperations {

    public WorkItemQuantity calculateWorkItemQuantityLastMonth(String applicationId);

    public WorkItemQuantity calculateWorkItemQuantityLastDay(String applicationId);
}
