package com.tsoftlatam.tab.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;


@Component
public class SchedulerController {
    @Value("${url}")
    private String url;


    @Scheduled(fixedRateString = "${time}")
    public void reportCurrentTime() throws IOException {

        try {
            //Obtengo la URLa ejecutar
            URL iurl = new URL(url);

            //Abro un Stream de la URL, siendo esto similar a ejecutarla en un navegador
            iurl.openStream();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
