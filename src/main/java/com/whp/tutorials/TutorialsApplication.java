package com.whp.tutorials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TutorialsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialsApplication.class, args);
	}

}
