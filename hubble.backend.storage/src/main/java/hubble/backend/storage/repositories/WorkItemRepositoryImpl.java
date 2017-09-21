package hubble.backend.storage.repositories;

import hubble.backend.storage.models.WorkItemStorage;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import hubble.backend.storage.operations.WorkItemOperations;

public class WorkItemRepositoryImpl implements WorkItemOperations {

    @Autowired
    MongoOperations mongo;

    @Override
    public boolean exist(WorkItemStorage workItem) {
        Criteria isSameIssueExternalId = Criteria.where("externalId").is(workItem.getExternalId());
        Criteria isSameProviderName = Criteria.where("providerName").is(workItem.getProviderName());

        List<WorkItemStorage> workItems = mongo
                .find(Query
                        .query(isSameIssueExternalId.andOperator(isSameProviderName)),
                        WorkItemStorage.class);

        return !workItems.isEmpty();
    }

    @Override
    public List<WorkItemStorage> findWorkItemsByApplicationIdBetweenDates(String applicationId, Date startDate, Date endDate) {
        Criteria applicationIdCriteria = Criteria.where("businessApplicationId").is(applicationId);
        Criteria startDateCriteria = Criteria.where("registeredDate").gte(startDate);
        Criteria endDateCriteria = Criteria.where("registeredDate").lte(endDate);

        List<WorkItemStorage> workItems = mongo
                .find(Query
                        .query(applicationIdCriteria.andOperator(
                                startDateCriteria,endDateCriteria)),
                        WorkItemStorage.class);
        return workItems;
    }

}
