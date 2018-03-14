package hubble.backend.api.interfaces;

import hubble.backend.api.models.BusinessApplication;
import hubble.backend.api.models.Uptime;
import hubble.backend.api.models.BusinessApplicationProfile;
import hubble.backend.business.services.models.AvailabilityDto;
import java.util.List;

public interface BusinessApplicationManager {

    public BusinessApplicationProfile getBusinessApplicationView(String id);

    public List<BusinessApplication> getAllApplications();

    public List<Uptime> getUptimeLastMonth(String applicationId);

    public List<AvailabilityDto> getAvailabilityLast10Minutes(String applicationId);

    public List<AvailabilityDto> getAvailabilityLastHour(String applicationId);

}
