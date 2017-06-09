package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.readers.hpbac.ProfileReaderModel;
import com.tsoftlatam.tab.frontend.sources.hpbac.models.Profile;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ProfilesService;
import com.tsoftlatam.tab.frontend.readers.hpbac.HPBacRestReader;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

//Clase utilizada para realizar llamadas asincrónicas
@RestController
public class ProfilesRestController {

    @Autowired
    private ProfilesService profilesService;
    private String bacClientProfilesUrl = "http://localhost:9080/readers/hpbac/getProfiles";
    private HPBacRestReader bacClient;

    //Carga la lista de aplicaciones en la base de datos
    @GetMapping("/sources/hpbac/profiles/initialize")
    public void initialize(){
        bacClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(HPBacRestReader.class, bacClientProfilesUrl);

        //Lista que contiene información tal cual viene del origen
        List<ProfileReaderModel> profilesFromReader = bacClient.findAllProfiles();

        for (int x=0;x<profilesFromReader.size();x++) {
            profilesService.createProfile(new Profile(profilesFromReader.get(x).getProfileName(),
                    profilesFromReader.get(x).getProfileName()));
        }
    }
}
