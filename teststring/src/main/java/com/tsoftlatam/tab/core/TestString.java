package com.tsoftlatam.tab.core;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestString {

    @RequestMapping("/")
    public String home(){
        return "Esto es un texto de prueba";
    }
}
