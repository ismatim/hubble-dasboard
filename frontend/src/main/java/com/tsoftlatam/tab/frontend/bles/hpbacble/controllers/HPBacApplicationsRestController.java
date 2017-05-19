package com.tsoftlatam.tab.frontend.bles.hpbacble.controllers;

import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacApplication;
import com.tsoftlatam.tab.frontend.bles.hpbacble.services.HPBacApplicationsService;
import com.tsoftlatam.tab.frontend.readers.HPBacRestClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestController
public class HPBacApplicationsRestController {

    @Autowired
    private HPBacApplicationsService hpBacApplicationsService;

    //FindAlls (Solo está hecho transactions, hay que hacer applications y locations)

    @GetMapping(value = "/hpbac/applications/findAll")
    public Collection<HPBacApplication> getAllApplications(){
        return hpBacApplicationsService.findAllApplications();
    }

    //Delete
    private String bacClientUrl = "http://localhost:9080/getBacData";
    private HPBacRestClient bacClient;

    //Eliminación de un registro a partir de su ID
    @GetMapping(value = "hpbac/applications/delete")
    public void deleteApplication(@RequestParam int id){
        hpBacApplicationsService.deleteApplication(id);
    }


    //creación de un registro
    @GetMapping(value = "/hpbac/applications/create")
    public void createApplication(){
        hpBacApplicationsService.createApplication(new HPBacApplication("testN","TestN2"));
    }

    //Llena la base de datos con los nombres de las aplicaciones
    @GetMapping("/hpbac/applications/fill")
    public void getApplications(){
        bacClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(HPBacRestClient.class, bacClientUrl);
        List<HPBacApplication> bacSamples = bacClient.findAllApplications();
        Iterator<HPBacApplication> it = bacSamples.iterator();
        String app = it.next().getApplicationName();
        String nextApp="";
        System.out.println("Nombre de app "+app);
        hpBacApplicationsService.createApplication(new HPBacApplication(app,app));
        while (it.hasNext()){
            nextApp = it.next().getApplicationName();


            System.out.println(app+ " : "+nextApp);

            if (!app.equals(nextApp))
                hpBacApplicationsService.createApplication(new HPBacApplication(nextApp,nextApp));
            app=nextApp;
        }
    }
}
