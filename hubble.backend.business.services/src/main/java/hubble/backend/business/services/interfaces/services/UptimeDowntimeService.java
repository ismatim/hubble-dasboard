package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.models.measures.Downtime;
import hubble.backend.business.services.models.measures.Uptime;
import hubble.backend.core.enums.MonitoringFields;
import java.util.Date;

public interface UptimeDowntimeService {

    public Uptime getUptime(String transactionId, MonitoringFields.FRECUENCY period, Date startDate, Date endDate);

    public Downtime getDowntime(String transactionId, MonitoringFields.FRECUENCY period, Date startDate, Date endDate);

    public Uptime getUptime(MonitoringFields.FRECUENCY period, Date startDate, Date endDate);

    public Uptime getUptimeByApplication(String applicationId, MonitoringFields.FRECUENCY period, Date startDate, Date endDate);

    public Uptime calculateLast10MinutesUptime(String applicationId);

    public Uptime calculateLastHourUptime(String applicationId);

    public Uptime calculateLastDayUptime(String applicationId);

}
