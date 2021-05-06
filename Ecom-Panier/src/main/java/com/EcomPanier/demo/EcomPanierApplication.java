package com.EcomPanier.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EcomPanierApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomPanierApplication.class, args);
	}

}
