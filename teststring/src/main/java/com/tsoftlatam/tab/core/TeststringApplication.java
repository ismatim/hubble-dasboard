package com.tsoftlatam.tab.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TeststringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeststringApplication.class, args);
	}
}
