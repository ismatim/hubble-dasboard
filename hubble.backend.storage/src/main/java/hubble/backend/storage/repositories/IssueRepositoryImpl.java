package hubble.backend.storage.repositories;

import hubble.backend.storage.models.IssueStorage;
import hubble.backend.storage.operations.IssueOperations;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class IssueRepositoryImpl implements IssueOperations {

    @Autowired
    MongoOperations mongo;
    
    @Override
    public boolean exist(IssueStorage issue) {
        Criteria isSameIssueExternalId = Criteria.where("externalId").is(issue.getExternalId());
        Criteria isSameProviderName = Criteria.where("providerName").is(issue.getProviderName());
        
        List<IssueStorage> issues = mongo
                .find(Query
                        .query(isSameIssueExternalId.andOperator(isSameProviderName)),
                        IssueStorage.class);

        return !issues.isEmpty();
    }

    @Override
    public List<IssueStorage> findIssuesByApplicationIdBetweenDates(String applicationId, Date startDate, Date endDate) {        
        Criteria applicationIdCriteria = Criteria.where("businessApplicationId").is(applicationId);
        Criteria startDateCriteria = Criteria.where("registeredDate").gte(startDate);
        Criteria endDateCriteria = Criteria.where("registeredDate").lte(endDate);
        
        List<IssueStorage> issues = mongo
                .find(Query
                        .query(applicationIdCriteria.andOperator(
                                startDateCriteria,endDateCriteria)),
                        IssueStorage.class);
        return issues;
    }    
}
