package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Application;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ApplicationsService;
import com.tsoftlatam.tab.frontend.readers.HPBacRestReader;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class ApplicationsRestController {

    @Autowired
    private ApplicationsService applicationsService;

    @GetMapping(value = "/sources/hpbac/applications/findAll")
    public Collection<Application> getAllApplications(){
        return applicationsService.findAllApplications();
    }


    private String bacClientUrl = "http://localhost:9080/getBacData";
    private HPBacRestReader bacClient;

    //creación de un registro
    @GetMapping(value = "/sources/hpbac/applications/create")
    public void createApplication(){
        applicationsService.createApplication(new Application("testN","TestN2"));
    }

    //Llena la base de datos con los nombres de las aplicaciones
    @GetMapping("/sources/hpbac/applications/fill")
    public void fill(){
        bacClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(HPBacRestReader.class, bacClientUrl);

        List<Application> bacSamples = bacClient.findAllApplications();

        //Se usan Streams introducidos en Java 8. parallelStream a diferencia de stream, paraleliza las acciones para
        //que se ejecuten en los múltiples cores del equipo
        List<String> applications = bacSamples.parallelStream()
                .map(Application::getApplicationName)      //Primero se selecciona el campo
                .distinct()                                     //Se filtran por distinct
                .collect(toList());                             //Se envían a la lista

        //Se cargan las aplicaciones en la base de datos
        for (int x=0;x<applications.size();x++) {
            applicationsService.createApplication(new Application(applications.get(x),applications.get(x)));
        }
    }
}
