package com.ayushsingh.testing_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestingDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TestingDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application is running...");
	}

}
