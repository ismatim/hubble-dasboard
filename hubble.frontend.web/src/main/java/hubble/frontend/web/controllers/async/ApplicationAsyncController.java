package hubble.frontend.web.controllers.async;

import hubble.frontend.business.interfaces.BusinessApplicationManager;
import hubble.frontend.business.models.Uptime;
import hubble.frontend.business.views.application.BusinessApplicationView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationAsyncController {

    @Autowired
    private BusinessApplicationManager businessAppMgr;

    @GetMapping("/applicationasync/{applicationId}")
    public @ResponseBody
    BusinessApplicationView getApplications(HttpServletRequest req, @PathVariable String applicationId) {

        BusinessApplicationView applicationView = businessAppMgr.getBusinessApplicationView(applicationId);

        return applicationView;
    }

    @GetMapping("/applicationasync/uptime/{applicationId}")
    public @ResponseBody
    List<Uptime> getUptimeLastMonth(HttpServletRequest req, @PathVariable String applicationId) {

        List<Uptime> uptimes = businessAppMgr.getUptimeLastMonth(applicationId);

        return uptimes;
    }
}
