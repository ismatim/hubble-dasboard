package hubble.backend.business.services.interfaces.operations.rules;

import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.rules.PerformanceGroupRule;
import hubble.backend.storage.models.AvailabilityStorage;

public interface PerformanceGroupRuleOperations extends
        GroupRuleOperations<AvailabilityStorage, ApplicationIndicators> {

    public PerformanceGroupRule calculateLast10MinutesGroupRuleByApplication(String applicationId);

    public PerformanceGroupRule calculateLastHourGroupRuleByApplication(String applicationId);

    public PerformanceGroupRule calculateLastDayGroupRuleByApplication(String applicationId);

    public PerformanceGroupRule calculateLastMonthGroupRuleByApplication(String applicationId);
}
