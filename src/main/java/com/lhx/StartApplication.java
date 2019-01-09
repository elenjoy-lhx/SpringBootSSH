package com.lhx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableJpaRepositories
public class StartApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
	
	
	
}

