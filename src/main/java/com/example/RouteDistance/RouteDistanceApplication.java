package com.example.RouteDistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RouteDistanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouteDistanceApplication.class, args);
	}

}
