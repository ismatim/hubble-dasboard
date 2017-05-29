package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Application;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class ApplicationsController {

    @Autowired
    private ApplicationsService applicationsService;
    //Atención, sin el autowired del servicio devuelve nullpointerexception

    //Devuelve la lista de aplicaciones
    @GetMapping("/sources/hpbac/applications")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        request.setAttribute("applications", applicationsService.findAllApplications());
        modelAndView.setViewName("/sources/hpbac/applications/list");
        return modelAndView;
    }


    //Devuelve una aplicación específica
    @GetMapping("/sources/hpbac/applications/{id}")
    public ModelAndView edit(HttpServletRequest request, @PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        Application application = applicationsService.findApplicationById(id);
        request.setAttribute("appDetails", application);
        modelAndView.setViewName("/sources/hpbac/applications/edit");
        return modelAndView;
    }

    //Actuaización de un registro
    @PostMapping("/sources/hpbac/applications/update")
    public ModelAndView updateApplication(@Valid Application application,
                                          BindingResult result
                                          //HttpServletRequest request,
                                          //HttpServletResponse response,
                                          //@PathVariable int id
                                            ){
        ModelAndView modelAndView = new ModelAndView();
        /*Application application = applicationsService.updateApplication(id
                                                        ,request.getParameter("applicationName")
                                                        ,request.getParameter("displayName"));
        */
        modelAndView.addObject("appDetails", application);
        modelAndView.setViewName(result.hasErrors() ? "/sources/hpbac/applications/edit" : "userReady");
        //request.setAttribute("appDetails", app);
        modelAndView.setViewName("/sources/hpbac/applications/edit");
        return modelAndView;

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
