/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hubble.frontend.web.controllers;

import hubble.backend.business.domain.AvailabilityBusiness;
import hubble.frontend.managers.models.BusinessApplication;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import java.beans.Transient;
import org.springframework.context.annotation.Bean;
import hubble.frontend.managers.interfaces.ApplicationManager;
import hubble.frontend.managers.interfaces.AvailabilityManager;

/**
 *
 * @author alexander.jimenez
 */
@Controller
public class AvailabilityMainController {
    @Autowired
    private AvailabilityManager appPulseAvailabilityManager; 
    
    @Autowired
    private ApplicationManager appPulseApplicationManager;
    
    @GetMapping("/Availability/Applications")
    public ModelAndView getAvailabilityApplications(HttpServletRequest req){
        ModelAndView mav = new ModelAndView();
        List<BusinessApplication> applications = new ArrayList();
        
        applications = appPulseApplicationManager.findAllApplications();
        
        req.setAttribute("regs", applications);
        mav.setViewName("/Availability/ApplicationAvailabilityCards");
        return mav;
    }
    
    @GetMapping("/Availability/BusinessProcesses/{applicationId}")
    public ModelAndView getAvailabilityBusinessProcesses(HttpServletRequest req,@PathVariable int applicationId){
        ModelAndView mav = new ModelAndView();
        List<BusinessApplication> applications = new ArrayList();
        
        //applications = appPulseApplicationManager.findAllApplications();

        req.setAttribute("regs", applications);
        mav.setViewName("/Availability/ApplicationAvailabilityCards");
        return mav;
    }
    
     
    @GetMapping("/Availability/Applications/Details/LastHour/{applicationId}")
    public ModelAndView getApplicationLastHourDetails(HttpServletRequest req, @PathVariable int applicationId){
         ModelAndView mav = new ModelAndView();
         List<AvailabilityBusiness> samples;
         BusinessApplication application;
         
         samples = appPulseAvailabilityManager.findLastHourSamplesByApplicationId(applicationId);
         application = appPulseApplicationManager.findBusinessApplicationById(applicationId);
                  
         req.setAttribute("regs", samples);
         req.setAttribute("entity", application);
                  
         mav.setViewName("/Availability/ApplicationDetailsLastHour");
         
         return mav;
    }
    
    @GetMapping("/Availability/Applications/Details/Last10Minutes/{applicationId}")
    public ModelAndView getApplicationLast10MinutesDetails(HttpServletRequest req, @PathVariable int applicationId){
         ModelAndView mav = new ModelAndView();
         List<AvailabilityBusiness> samples;
         BusinessApplication application;
         
         samples = appPulseAvailabilityManager.findLast10MinutesSamplesByApplicationId(applicationId);
         application = appPulseApplicationManager.findBusinessApplicationById(applicationId);
                  
         req.setAttribute("regs", samples);
         req.setAttribute("entity", application);
                  
         mav.setViewName("/Availability/ApplicationDetailsLast10Minutes");
         
         return mav;
    }
    
}

