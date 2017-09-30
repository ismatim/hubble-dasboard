package hubble.frontend.business.interfaces;

import hubble.backend.business.services.models.AvailabilityDto;
import hubble.frontend.business.models.BusinessApplication;
import hubble.frontend.business.models.Uptime;
import hubble.frontend.business.views.application.BusinessApplicationView;
import java.util.List;

public interface BusinessApplicationManager {

    public BusinessApplicationView getBusinessApplicationView(String id);

    public List<BusinessApplication> getAllApplications();

    public List<Uptime> getUptimeLastMonth(String applicationId);
    
    public List<AvailabilityDto> getAvailabilityLast10Minutes(String applicationId);
    
    public List<AvailabilityDto> getAvailabilityLastHour(String applicationId);

}
