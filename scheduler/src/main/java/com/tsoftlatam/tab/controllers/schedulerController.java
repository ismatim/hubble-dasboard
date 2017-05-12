package com.tsoftlatam.tab.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by david.malagueno on 12/5/2017.
 */
@Component
public class schedulerController {
    //private static final Logger log = LoggerFactory.getLogger(schedulerController.class);

    @Value("${url}")
    private String url;

    @Scheduled(fixedRateString = "${time}")
    public void reportCurrentTime() {
        System.out.println(url);
    }
}
