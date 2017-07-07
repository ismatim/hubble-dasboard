/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hubble.frontend.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author alexander.jimenez
 */
@Controller
public class DashboardMainController {
    
    
    @GetMapping("/")
    public ModelAndView getMainDashboard(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }
    
}

