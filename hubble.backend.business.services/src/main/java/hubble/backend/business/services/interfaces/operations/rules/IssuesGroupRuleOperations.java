package hubble.backend.business.services.interfaces.operations.rules;

import hubble.backend.business.services.interfaces.operations.kpis.KpiThresholdSetup;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.rules.IssuesGroupRule;
import hubble.backend.storage.models.IssueStorage;

public interface IssuesGroupRuleOperations extends
        GroupRuleOperations<IssueStorage, ApplicationIndicators>,
        KpiThresholdSetup {

    public IssuesGroupRule calculateLast10MinutesGroupRuleByApplication(String applicationId);

    public IssuesGroupRule calculateLastHourGroupRuleByApplication(String applicationId);

    public IssuesGroupRule calculateLastDayGroupRuleByApplication(String applicationId);

    public IssuesGroupRule calculateLastMonthGroupRuleByApplication(String applicationId);
}
