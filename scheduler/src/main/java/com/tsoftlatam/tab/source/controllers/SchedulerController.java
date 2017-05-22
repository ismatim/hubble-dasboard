package com.tsoftlatam.tab.source.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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

    //@Value("${urlBACservice}")
    //private String urlBACservice;


    //@Scheduled(fixedRateString = "${BACtime}")
    @Scheduled(fixedRateString = "20000")
    public void executeBAC() throws IOException {

        try {
            //Obtengo la URLa ejecutar
            //URL iurl = new URL(urlBACservice);
            URL iurl = new URL("http://localhost:8765/res");

            //Abro un Stream de la URL, siendo esto similar a ejecutarla en un navegador
            iurl.openStream();


            Calendar calendar = Calendar.getInstance();

            System.out.println("Se ejecutó una extracción en: " + calendar.getTime());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
