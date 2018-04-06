package hubble.backend.business.services.interfaces.operations.averages;

import hubble.backend.business.services.models.measures.IssuesQuantity;
import hubble.backend.business.services.interfaces.operations.kpis.KpiThresholdSetup;

public interface IssueOperations extends KpiThresholdSetup {

    public IssuesQuantity calculateIssuesQuantityLastMonth(String applicationId);

    public IssuesQuantity calculateIssuesQuantityLastDay(String applicationId);
}
