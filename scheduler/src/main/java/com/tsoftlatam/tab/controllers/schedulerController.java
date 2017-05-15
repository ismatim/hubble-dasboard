package com.tsoftlatam.tab.controllers;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by david.malagueno on 12/5/2017.
 */
@Component
@RestController
public class schedulerController {
    //private static final Logger log = LoggerFactory.getLogger(schedulerController.class);

    @Value("${url}")
    private String url;
    private String bacClientUrl = url;


    @Scheduled(fixedRateString = "${time}")
    public void reportCurrentTime() {

        System.out.println(url);
    }

    //Cliente REST con feign que devuelve un string
    @GetMapping("/test")
            public String  test(){
                Feign.builder()
                .decoder(new GsonDecoder())
                .target(BacClient.class, bacClientUrl);
            return "Ejecutado";
    }


}
