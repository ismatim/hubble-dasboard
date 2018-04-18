package hubble.backend.business.services.interfaces.operations.rules;

import hubble.backend.core.enums.MonitoringFields;
import java.util.List;

public interface GroupRuleOperations<G, M> {

    public Double calculateGroupRule(List<G> storageModel);

    public MonitoringFields.STATUS calculateGroupRuleStatus(M model, Double measure);

}
