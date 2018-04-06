package hubble.backend.business.services.interfaces.operations.rules;

import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.rules.AvailabilityGroupRule;
import hubble.backend.storage.models.AvailabilityStorage;

public interface AvailabilityGroupRuleOperations extends
        GroupRuleOperations<AvailabilityGroupRule, AvailabilityStorage, ApplicationIndicators> {
}
