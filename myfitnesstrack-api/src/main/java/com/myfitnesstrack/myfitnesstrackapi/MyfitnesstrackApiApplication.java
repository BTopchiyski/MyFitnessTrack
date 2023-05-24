package com.myfitnesstrack.myfitnesstrackapi;

import com.myfitnesstrack.myfitnesstrackapi.auth.AuthenticationService;
import com.myfitnesstrack.myfitnesstrackapi.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.myfitnesstrack.myfitnesstrackapi.user.Role.ADMIN;
import static com.myfitnesstrack.myfitnesstrackapi.user.Role.MANAGER;

@SpringBootApplication
public class MyfitnesstrackApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyfitnesstrackApiApplication.class, args);
	}

}
