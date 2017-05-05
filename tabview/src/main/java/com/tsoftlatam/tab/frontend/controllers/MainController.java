package com.tsoftlatam.tab.frontend.controllers;

import com.tsoftlatam.tab.frontend.models.BacSample;
import com.tsoftlatam.tab.frontend.models.Book;
import com.tsoftlatam.tab.frontend.models.restClients.BacClient;
import com.tsoftlatam.tab.frontend.models.restClients.BookClient;
import com.tsoftlatam.tab.frontend.services.LmsService;
import com.tsoftlatam.tab.readers.models.BacData;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private LmsService lmsService;

    private BookClient bookClient;
    private BacClient bacClient;
    private String bookClientUrl = "http://localhost:8080/findAllBooks";
    private String bacClientUrl = "http://localhost:9080/getBacData";


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

    //Cliente de servicio REST con feign
    @GetMapping("/booksTable")
    public String booksTable(HttpServletRequest req){
            bookClient = Feign.builder()
                    .decoder(new GsonDecoder())
                    .target(BookClient.class, bookClientUrl);
            List<Book> books = bookClient.findAll();
            req.setAttribute("books",books);
            return "booksTable";
    }

    //Cliente de servicio REST con feign
    @GetMapping("/bacTable")
    public String bacTable(HttpServletRequest req){
        bacClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(BacClient.class, bacClientUrl);
        List<BacSample> bacSamples = bacClient.findAll();
        req.setAttribute("bacSamples",bacSamples);
        return "bacTable";
    }





}
