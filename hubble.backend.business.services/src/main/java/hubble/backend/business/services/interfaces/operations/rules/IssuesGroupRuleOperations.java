package hubble.backend.business.services.interfaces.operations.rules;

import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.rules.IssuesGroupRule;
import hubble.backend.storage.models.IssueStorage;
import hubble.backend.business.services.interfaces.operations.kpis.KpiThresholdSetup;

public interface IssuesGroupRuleOperations extends
        GroupRuleOperations<IssuesGroupRule, IssueStorage, ApplicationIndicators>,
        KpiThresholdSetup {
}
