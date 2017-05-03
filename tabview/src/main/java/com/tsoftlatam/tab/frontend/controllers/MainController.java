package com.tsoftlatam.tab.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class MainController {

    @GetMapping("/helloJsp")
    public String init(){
        return "index";
    }

    @GetMapping("/hello")
    public String home(Model model){
        model.addAttribute("fecha", LocalDate.now());
        return "home";
    }

}
