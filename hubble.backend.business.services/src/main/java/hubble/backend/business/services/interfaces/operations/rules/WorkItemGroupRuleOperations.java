package hubble.backend.business.services.interfaces.operations.rules;

import hubble.backend.business.services.interfaces.operations.kpis.KpiThresholdSetup;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.rules.WorkItemsGroupRule;
import hubble.backend.storage.models.WorkItemStorage;

public interface WorkItemGroupRuleOperations extends
        GroupRuleOperations< WorkItemStorage, ApplicationIndicators>,
        KpiThresholdSetup {

    public WorkItemsGroupRule calculateLastDayGroupRuleByApplication(String applicationId);

    public WorkItemsGroupRule calculateLastWeekGroupRuleByApplication(String applicationId);

    public WorkItemsGroupRule calculateLastMonthGroupRuleByApplication(String applicationId);
}
