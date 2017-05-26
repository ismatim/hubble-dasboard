package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Application;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ApplicationsController {

    @Autowired
    private ApplicationsService applicationsService;
    //Atención, sin el autowired del servicio devuelve nullpointerexception

    //Devuelve la lista de aplicaciones
    @GetMapping("/sources/hpbac/applications")
    public String list(HttpServletRequest req){
        req.setAttribute("applications", applicationsService.findAllApplications());
        return "/sources/hpbac/applications/list";
    }

    //Devuelve una aplicación específica
    @GetMapping("/sources/hpbac/applications/{id}")
    public String edit(HttpServletRequest req,@PathVariable int id){
        Application application = applicationsService.findApplicationById(id);
        System.out.println(application.toString());
        req.setAttribute("appDetails", application);
        return "/sources/hpbac/applications/edit";
    }

    //Actuaización de un registro
    @PostMapping("/sources/hpbac/applications/update/{id}")
    public void updateApplication(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){

        Application application = applicationsService.updateApplication(id
                                                        ,request.getParameter("applicationName")
                                                        ,request.getParameter("displayName"));

        try {
            response.sendRedirect("/sources/hpbac/applications/"+id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Eliminación de un registro a partir de su ID
    @GetMapping("/sources/hpbac/applications/delete/{id}")
    public void deleteApplication(HttpServletResponse response, @PathVariable int id) throws IOException {

        //TODO: Esta linea verifica que se encuentre la aplicación, sumar validación
        Application application = applicationsService.findApplicationById(id);
        applicationsService.deleteApplication(id);

        try {
            response.sendRedirect("/sources/hpbac/applications");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }









}
