package com.tsoftlatam.tab.frontend.bles.hpbacble.controllers;

import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacApplication;
import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacSample;
import com.tsoftlatam.tab.frontend.bles.hpbacble.services.HPBacApplicationsService;
import com.tsoftlatam.tab.frontend.readers.HPBacRestClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HPBacApplicationsMainController {

    @Autowired
    private HPBacApplicationsService hpBacApplicationsService;


    //Atención, sin el autowired del servicio devuelve nullpointerexception
    @GetMapping("/hpbac/applications/admin")
    public String admin(HttpServletRequest req){
        req.setAttribute("hpBacApplications",hpBacApplicationsService.findAllApplications());
        return "/dao/hpbac/applications/admin";
    }


}
