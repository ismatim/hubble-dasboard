package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Sample;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ApplicationsService;
import com.tsoftlatam.tab.frontend.readers.HPBacRestReader;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    private String bacClientUrl = "http://localhost:9080/getBacData";
    private HPBacRestReader bacClient;
    private ApplicationsService appCrud;

    //Cliente de servicio REST con feign
    @GetMapping("/main")
    public String bacTable(HttpServletRequest req){
        bacClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(HPBacRestReader.class, bacClientUrl);
        List<Sample> bacSamples = bacClient.findAll();
        req.setAttribute("bacSamples",bacSamples);
        return "main";
    }


}
