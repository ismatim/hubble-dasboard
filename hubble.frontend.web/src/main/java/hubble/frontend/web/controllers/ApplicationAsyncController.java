package hubble.frontend.web.controllers;

import hubble.frontend.business.interfaces.BusinessApplicationManager;
import hubble.frontend.business.views.application.BusinessApplicationView;
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
}
