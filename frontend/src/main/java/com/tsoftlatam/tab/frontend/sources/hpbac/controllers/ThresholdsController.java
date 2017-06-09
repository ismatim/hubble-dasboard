package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Threshold;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.LocationsService;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ProfilesService;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ThresholdsService;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ThresholdsController {

    @Autowired
    private ThresholdsService thresholdsService;
    @Autowired
    private LocationsService locationsService;
    @Autowired
    private ProfilesService profilesService;
    @Autowired
    private TransactionsService transactionsService;

    //Devuelve la lista de registros
    @GetMapping("/sources/hpbac/thresholds")
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        List<Threshold> thresholds = (List<Threshold>) thresholdsService.findAllThresholds();
        List<String> profileName = new ArrayList<>();
        List<String> transactionName = new ArrayList<>();
        List<String> locationName = new ArrayList<>();

        for (Threshold threshold : thresholds) {
            profileName.add(profilesService.findProfileById(threshold.getProfileId()).getProfileName());
            transactionName.add(transactionsService.findTransactionById(threshold.getTransactionId()).getTransactionName());
            locationName.add(locationsService.findLocationById(threshold.getLocationId()).getLocationName());
        }

        request.setAttribute("profileName", profileName);
        request.setAttribute("transactionName", transactionName);
        request.setAttribute("locationName", locationName);
        request.setAttribute("thresholds", thresholds);
        modelAndView.setViewName("/sources/hpbac/thresholds/list");
        return modelAndView;
    }

    //Devuelve un registro específico
    @GetMapping("/sources/hpbac/thresholds/{id}")
    public ModelAndView edit(HttpServletRequest request, @PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        Threshold threshold = thresholdsService.findThresholdById(id);
        String profileName = profilesService.findProfileById(threshold.getProfileId()).getProfileName();
        String transactionName = transactionsService.findTransactionById(threshold.getTransactionId()).getTransactionName();
        String locationName = locationsService.findLocationById(threshold.getLocationId()).getLocationName();
        request.setAttribute("profileName", profileName);
        request.setAttribute("transactionName", transactionName);
        request.setAttribute("locationName", locationName);
        request.setAttribute("threshold", threshold);
        modelAndView.setViewName("/sources/hpbac/thresholds/edit");
        return modelAndView;
    }

    //Actualización de un registro
    @PostMapping("/sources/hpbac/thresholds/update/{id}")
    public void update(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){
        String profileName;
        String transactionName;
        String locationName;
        float okThreshold = Float.parseFloat(request.getParameter("okThreshold"));
        float criticalThreshold = Float.parseFloat(request.getParameter("criticalThreshold"));
        float outlierThreshold = Float.parseFloat(request.getParameter("outlierThreshold"));
        Threshold threshold = thresholdsService.updateThreshold(id ,okThreshold, criticalThreshold, outlierThreshold);


        try {
            profileName = profilesService.findProfileById(threshold.getProfileId()).getProfileName();
            transactionName = transactionsService.findTransactionById(threshold.getTransactionId()).getTransactionName();
            locationName = locationsService.findLocationById(threshold.getLocationId()).getLocationName();

            request.setAttribute("profileName", profileName);
            request.setAttribute("transactionName", transactionName);
            request.setAttribute("locationName", locationName);
            request.setAttribute("threshold", threshold);
            response.sendRedirect("/sources/hpbac/thresholds/"+id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
