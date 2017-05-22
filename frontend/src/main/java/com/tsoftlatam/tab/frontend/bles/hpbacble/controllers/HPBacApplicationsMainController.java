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
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HPBacApplicationsMainController {

    @Autowired
    private HPBacApplicationsService hpBacApplicationsService;
    //Atención, sin el autowired del servicio devuelve nullpointerexception


    @GetMapping("/hpbac/applications/list")
    public String list(HttpServletRequest req){
        req.setAttribute("hpBacApplications",hpBacApplicationsService.findAllApplications());
        return "/dao/hpbac/applications/list";
    }

    @GetMapping("/hpbac/applications/view/{id}")
    public String view(HttpServletRequest req,@PathVariable int id){
        HPBacApplication hpBacApplication = hpBacApplicationsService.findApplicationById(id);
        System.out.println(hpBacApplication.toString());
        req.setAttribute("hpBacApplication",hpBacApplication);
        return "/dao/hpbac/applications/view";
    }

    @GetMapping("/hpbac/applications/update/{id}")
    public String update(HttpServletRequest req,@PathVariable int id){
        HPBacApplication hpBacApplication = hpBacApplicationsService.findApplicationById(id);
        req.setAttribute("hpBacApplication",hpBacApplication);
        return "/dao/hpbac/applications/update";
    }


}
