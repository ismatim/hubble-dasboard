package hubble.frontend.web.controllers.async;

import hubble.backend.business.services.models.AvailabilityDto;
import hubble.frontend.business.interfaces.BusinessApplicationManager;
import hubble.frontend.business.models.Uptime;
import hubble.frontend.business.views.application.BusinessApplicationView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationAsyncController {

    @Autowired
    private BusinessApplicationManager businessAppMgr;

    @GetMapping(value = "/v2/application/{applicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BusinessApplicationView getApplications(HttpServletRequest req, @PathVariable String applicationId) {

        BusinessApplicationView applicationView = businessAppMgr.getBusinessApplicationView(applicationId);

        return applicationView;
    }

    @GetMapping(value = "/v2/application/uptime/{applicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Uptime> getUptimeLastMonth(HttpServletRequest req, @PathVariable String applicationId) {

        List<Uptime> uptimes = businessAppMgr.getUptimeLastMonth(applicationId);

        return uptimes;
    }

    @GetMapping(value = "/v2/application/availability/{applicationId}/10Minutes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AvailabilityDto> getTransactionLast10Minutes(HttpServletRequest req, @PathVariable String applicationId) {
        List<AvailabilityDto> availbility = businessAppMgr.getAvailabilityLast10Minutes(applicationId);

        return availbility;
    }

}
