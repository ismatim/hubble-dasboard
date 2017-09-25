package hubble.frontend.web.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardMainController {

    @GetMapping("/")
    public ModelAndView getMainDashboard(HttpServletRequest req) {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("home");
        return mav;
    }
}
