package hubble.backend.business.services.interfaces.operations.rules;

import hubble.backend.core.enums.MonitoringFields;
import java.util.List;

public interface GroupRuleOperations<T, G, M> {

    public Double calculateGroupRule(List<G> storageModel);

    public T calculateLast10MinutesGroupRuleByApplication(String applicationId);

    public T calculateLastHourGroupRuleByApplication(String applicationId);

    public T calculateLastDayGroupRuleByApplication(String applicationId);

    public T calculateLastMonthGroupRuleByApplication(String applicationId);

    public MonitoringFields.STATUS calculateGroupRuleStatus(M model, Double measure);

}
