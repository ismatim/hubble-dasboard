package hubble.frontend.web.controllers;

import hubble.frontend.managers.implementations.AvailabilityManagerImpl;
import hubble.frontend.managers.interfaces.PerformanceManager;
import hubble.frontend.managers.models.aggregations.BusinessApplicationAvg;
import hubble.frontend.managers.models.entities.BusinessApplication;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardMainController {

    @Autowired
    private AvailabilityManagerImpl availabilityManager;
    @Autowired
    private PerformanceManager performanceManager;

    @GetMapping("/")
    public ModelAndView getMainDashboard(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        List<BusinessApplication> applications = new ArrayList();
        applications = availabilityManager.findAllApplications();
        req.setAttribute("regs", applications);
        mav.setViewName("main");
        return mav;
    }

    @GetMapping("/applications")
    public ModelAndView getApplications(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        List<BusinessApplication> applications = availabilityManager.findAllApplications();
        List<BusinessApplicationAvg> availabilityApplicationsLastHourAvgs = new ArrayList();
        List<BusinessApplicationAvg> availabilityApplicationsLast10MinutesAvgs = new ArrayList();
        List<BusinessApplicationAvg> performanceApplicationsLastHourAvgs = new ArrayList();
        List<BusinessApplicationAvg> performanceApplicationsLast10MinutesAvgs = new ArrayList();

        for(BusinessApplication application : applications){
            availabilityApplicationsLastHourAvgs.add(availabilityManager.findLastHourAverageByApplication(application.getId()));
            availabilityApplicationsLast10MinutesAvgs.add(availabilityManager.findLast10MinutesAverageByApplication(application.getId()));
            performanceApplicationsLast10MinutesAvgs.add(performanceManager.findLast10MinutesAverageByApplication(application.getId()));
            performanceApplicationsLastHourAvgs.add(performanceManager.findLastHourAverageByApplication(application.getId()));
        }

        req.setAttribute("applications", applications);
        req.setAttribute("availabilityLastHourAvgs", availabilityApplicationsLastHourAvgs);
        req.setAttribute("availabilityLast10MinutesAvgs", availabilityApplicationsLast10MinutesAvgs);
        req.setAttribute("performanceLastHourAvgs", performanceApplicationsLastHourAvgs);
        req.setAttribute("performanceLast10MinutesAvgs", performanceApplicationsLast10MinutesAvgs);

        mav.setViewName("applications");
        return mav;
    }

}
