package com.tsoftlatam.tab.frontend.controllers;

import com.tsoftlatam.tab.frontend.services.LmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
public class MainController {

    @Autowired
    private LmsService lmsService;

    @GetMapping("/helloJsp")
    public String init(){
        return "index";
    }

    @GetMapping("/hello")
    public String home(Model model){
        model.addAttribute("fecha", LocalDate.now());
        return "main";
    }

    @GetMapping("/test")
    public String test(){
        return "mainTemplates/test";
    }

    @GetMapping("/modelAndView")
    public String modelAndView(HttpServletRequest req){
        req.setAttribute("books",lmsService.findAllBooks());
        return "main";
    }



}
