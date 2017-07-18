package hubble.frontend.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransferController {

    @GetMapping("/Transfer/Applications")
    public ModelAndView getMainDashboard() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/Transfer/ApplicationTransferCards");
        return mav;
    }
}

