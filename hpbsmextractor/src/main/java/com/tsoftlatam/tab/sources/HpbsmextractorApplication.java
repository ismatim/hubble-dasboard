package com.tsoftlatam.tab.sources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

//Para que reconozca al ExtractorController que está en un package diferente
@ComponentScan(basePackages = {"com.tsoftlatam.tab.controllers"})
@EnableDiscoveryClient
@SpringBootApplication
public class HpbsmextractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HpbsmextractorApplication.class, args);
	}
}
