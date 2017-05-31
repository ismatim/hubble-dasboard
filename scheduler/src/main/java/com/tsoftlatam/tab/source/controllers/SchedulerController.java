package com.tsoftlatam.tab.source.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by david.malagueno on 22/5/2017.
 */
@Component
public class SchedulerController {
    @Value("${urlBACservice}")
    private String urlBACservice;


    @Scheduled(fixedRateString = "${BACtime}")
    public void executeBAC() throws IOException {

        try {
            //Obtengo la URLa ejecutar
            URL iurl = new URL(urlBACservice);

            //Abro un Stream de la URL, siendo esto similar a ejecutarla en un navegador
            iurl.openStream();


            Calendar calendar = Calendar.getInstance();
            System.out.println("Ejecuto el extractor:" + calendar.getTime());
            System.out.println(iurl.getContent());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
