package hubble.frontend.web.controllers;

import hubble.frontend.business.interfaces.BusinessApplicationManager;
import hubble.frontend.business.views.application.BusinessApplicationView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {

    @Autowired
    private BusinessApplicationManager businessAppMgr;

    @GetMapping(value = "/application/{applicationId}")
    public ModelAndView getApplications(HttpServletRequest req, @PathVariable String applicationId) {
        ModelAndView mav = new ModelAndView();

        BusinessApplicationView applicationView = businessAppMgr.getBusinessApplicationView(applicationId);
        mav.addObject("applicationView", applicationView);
        mav.setViewName("Application/profile");
        return mav;
    }
}
