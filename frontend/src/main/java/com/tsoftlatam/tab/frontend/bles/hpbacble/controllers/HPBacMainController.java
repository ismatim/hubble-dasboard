package com.tsoftlatam.tab.frontend.bles.hpbacble.controllers;

import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacApplication;
import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacSample;
import com.tsoftlatam.tab.frontend.bles.hpbacble.services.HPBacApplicationsService;
import com.tsoftlatam.tab.frontend.readers.HPBacRestClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

@Controller
public class HPBacMainController {

    private String bacClientUrl = "http://localhost:9080/getBacData";
    private HPBacRestClient bacClient;
    private HPBacApplicationsService appCrud;

    //Cliente de servicio REST con feign
    @GetMapping("/main")
    public String bacTable(HttpServletRequest req){
        bacClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(HPBacRestClient.class, bacClientUrl);
        List<HPBacSample> bacSamples = bacClient.findAll();
        req.setAttribute("bacSamples",bacSamples);
        return "main";
    }


}
