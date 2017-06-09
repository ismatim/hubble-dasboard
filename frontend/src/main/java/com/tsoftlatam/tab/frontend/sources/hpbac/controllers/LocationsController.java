package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Location;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.LocationsService;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ProfilesService;
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
public class LocationsController {

    @Autowired
    private LocationsService locationsService;
    @Autowired
    private ProfilesService profilesService;
    @Autowired
    private TransactionsService transactionsService;

    //Devuelve la lista de locaciones
    @GetMapping("/sources/hpbac/locations")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        List<Location> locations = (List<Location>) locationsService.findAllLocations();
        List<String> profileName = new ArrayList<>();
        List<String> transactionName = new ArrayList<>();

        for (Location location: locations) {
            profileName.add(profilesService.findProfileById(location.getProfileId()).getProfileName());
            transactionName.add(transactionsService.findTransactionById(location.getTransactionId()).getTransactionName());
        }

        request.setAttribute("profileName", profileName);
        request.setAttribute("transactionName", transactionName);
        request.setAttribute("locations", locations);
        modelAndView.setViewName("/sources/hpbac/locations/list");
        return modelAndView;
    }

    //Devuelve una locación específica
    @GetMapping("/sources/hpbac/locations/{id}")
    public ModelAndView edit(HttpServletRequest request, @PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        Location location = locationsService.findLocationById(id);
        String profileName = profilesService.findProfileById(location.getProfileId()).getProfileName();
        String transactionName = transactionsService.findTransactionById(location.getTransactionId()).getTransactionName();
        request.setAttribute("profileName", profileName);
        request.setAttribute("transactionName", transactionName);
        request.setAttribute("location", location);
        modelAndView.setViewName("/sources/hpbac/locations/edit");
        return modelAndView;
    }

    //Actualización de un registro
    @PostMapping("/sources/hpbac/locations/update/{id}")
    public void update(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){
        String profileName;
        String transactionName;
        String locationName = request.getParameter("locationName");
        String displayName = request.getParameter("displayName");
        Boolean isInternal = Boolean.parseBoolean(request.getParameter("isInternal"));
        Location location = locationsService.updateLocation(id ,locationName, displayName, isInternal);


        try {
            profileName = profilesService.findProfileById(location.getProfileId()).getProfileName();
            transactionName = transactionsService.findTransactionById(location.getTransactionId()).getTransactionName();

            request.setAttribute("profileName", profileName);
            request.setAttribute("transactionName", transactionName);
            request.setAttribute("location", location);
            response.sendRedirect("/sources/hpbac/locations/"+id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
