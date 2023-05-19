package com.myfitnesstrack.myfitnesstrackapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.myfitnesstrack.myfitnesstrackapi.config")
public class MyfitnesstrackApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyfitnesstrackApiApplication.class, args);
	}

}
