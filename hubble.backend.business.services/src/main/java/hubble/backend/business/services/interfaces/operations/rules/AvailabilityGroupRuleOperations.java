package hubble.backend.business.services.interfaces.operations.rules;

import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.rules.AvailabilityGroupRule;
import hubble.backend.storage.models.AvailabilityStorage;

public interface AvailabilityGroupRuleOperations extends
        GroupRuleOperations<AvailabilityStorage, ApplicationIndicators> {

    public AvailabilityGroupRule calculateLast10MinutesGroupRuleByApplication(String applicationId);

    public AvailabilityGroupRule calculateLastHourGroupRuleByApplication(String applicationId);

    public AvailabilityGroupRule calculateLastDayGroupRuleByApplication(String applicationId);

    public AvailabilityGroupRule calculateLastMonthGroupRuleByApplication(String applicationId);
}
