package hubble.frontend.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardMainController {

    @GetMapping("/")
    public ModelAndView getMainDashboard() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }

}
