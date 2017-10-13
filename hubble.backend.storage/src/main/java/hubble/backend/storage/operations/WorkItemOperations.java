package hubble.backend.storage.operations;

import hubble.backend.storage.models.WorkItemStorage;
import java.util.Date;
import java.util.List;

public interface WorkItemOperations {

    boolean exist(WorkItemStorage workItem);
    List<WorkItemStorage> findWorkItemsByApplicationIdBetweenDates(String applicationId, Date startDate, Date endDate);

}
