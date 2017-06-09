package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Profile;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ProfilesController {

    @Autowired
    private ProfilesService profilesService;


    //Devuelve la lista de aplicaciones
    @GetMapping("/sources/hpbac/profiles")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        request.setAttribute("profiles", profilesService.findAllProfiles());
        modelAndView.setViewName("/sources/hpbac/profiles/list");
        return modelAndView;
    }


    //Devuelve una aplicación específica
    @GetMapping("/sources/hpbac/profiles/{id}")
    public ModelAndView edit(HttpServletRequest request, @PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        Profile profile = profilesService.findProfileById(id);
        request.setAttribute("profile", profile);
        modelAndView.setViewName("/sources/hpbac/profiles/edit");
        return modelAndView;
    }

    //Actualización de un registro
    @PostMapping("/sources/hpbac/profiles/update/{id}")
    public void update(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){

        Profile profile = profilesService.updateProfile(id ,request.getParameter("profileName"),
                                                        request.getParameter("displayName"));


        request.setAttribute("profile", profile);

        try {
            response.sendRedirect("/sources/hpbac/profiles/"+id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Nota, la eleiminación no se va a implementar, no debería darse la posibilidad al usuario de eliminar
    //Eliminación de un registro a partir de su ID
    @GetMapping("/sources/hpbac/profiles/delete/{id}")
    public void delete(HttpServletResponse response, @PathVariable int id) throws IOException {

        //TODO: Esta linea verifica que se encuentre la aplicación, sumar validación
        Profile profile = profilesService.findProfileById(id);
        profilesService.deleteProfile(id);

        try {
            response.sendRedirect("/sources/hpbac/profiles");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }









}
