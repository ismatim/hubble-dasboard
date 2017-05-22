package com.tsoftlatam.tab.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by david.malagueno on 22/5/2017.
 */
@EnableScheduling
@SpringBootApplication
public class SchedulerApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SchedulerApplication.class);
    }
}
