package hubble.backend.business.services.interfaces.operations.rules;

import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.rules.PerformanceGroupRule;
import hubble.backend.storage.models.AvailabilityStorage;

public interface PerformanceGroupRuleOperations extends
        GroupRuleOperations<PerformanceGroupRule, AvailabilityStorage, ApplicationIndicators> {
}
