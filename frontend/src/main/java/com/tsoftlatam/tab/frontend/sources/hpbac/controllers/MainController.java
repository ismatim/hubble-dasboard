package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Sample;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.LocationsService;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ProfilesService;
import com.tsoftlatam.tab.frontend.readers.hpbac.HPBacRestReader;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.TransactionsService;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private String bacClientUrl = "http://localhost:9080/readers/hpbac/getRawData";
    private HPBacRestReader bacClient;
    @Autowired
    private ProfilesService profilesService;
    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private LocationsService locationsService;

    //Cliente de servicio REST con feign
    @GetMapping("/main")
    public String bacTable(HttpServletRequest req) {
        bacClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(HPBacRestReader.class, bacClientUrl);
        List<Sample> bacSamples = bacClient.findAll();
        List<String> profileDisplayName = new ArrayList<>();
        List<String> transactionDisplayName = new ArrayList<>();
        List<String> locationDisplayName = new ArrayList<>();


        for (Sample sample: bacSamples) {
            profileDisplayName.add(profilesService.findProfileDisplayName(sample.getProfileName()));
            transactionDisplayName.add(transactionsService.findTransactionDisplayName(sample.getTransactionName()));
            locationDisplayName.add(locationsService.findLocationDisplayName(sample.getLocationName()));
        }

        req.setAttribute("profileDisplayName",profileDisplayName);
        req.setAttribute("transactionDisplayName",transactionDisplayName);
        req.setAttribute("locationDisplayName",locationDisplayName);
        req.setAttribute("bacSamples",bacSamples);
        return "main";
    }
}
