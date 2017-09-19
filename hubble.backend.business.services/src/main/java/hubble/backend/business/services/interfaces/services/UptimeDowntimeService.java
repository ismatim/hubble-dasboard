package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.models.measures.DowntimeDto;
import hubble.backend.business.services.models.measures.UptimeDto;
import hubble.backend.core.enums.MonitoringFields;
import java.util.Date;

public interface UptimeDowntimeService {

    public UptimeDto getUptime(String transactionId, MonitoringFields.FRECUENCY period, Date startDate, Date endDate);

    public DowntimeDto getDowntime(String transactionId, MonitoringFields.FRECUENCY period, Date startDate, Date endDate);

    public UptimeDto getUptime(MonitoringFields.FRECUENCY period, Date startDate, Date endDate);

    public UptimeDto getUptimeByApplication(String applicationId, MonitoringFields.FRECUENCY period, Date startDate, Date endDate);

    public UptimeDto calculateLast10MinutesUptime(String applicationId);

    public UptimeDto calculateLastHourUptime(String applicationId);

    public UptimeDto calculateLastDayUptime(String applicationId);

}
