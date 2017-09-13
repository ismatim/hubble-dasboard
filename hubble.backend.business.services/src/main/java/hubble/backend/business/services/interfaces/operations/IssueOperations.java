package hubble.backend.business.services.interfaces.operations;

import hubble.backend.business.services.models.measures.IssuesQuantity;

public interface IssueOperations {

    public IssuesQuantity calculateIssuesQuantityLastMonth(String applicationId);

    public IssuesQuantity calculateIssuesQuantityLastDay(String applicationId);
}
