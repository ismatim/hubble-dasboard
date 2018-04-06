package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.models.Issue;
import hubble.backend.business.services.models.measures.IssuesQuantity;
import hubble.backend.business.services.models.measures.kpis.IssuesKpi;

public interface IssueService extends ServiceBase<Issue>,
        OperationsKeyPerformanceIndicatorServiceBase<IssuesKpi> {

    public IssuesQuantity calculateIssuesQuantityLastDay(String applicationId);
}
