package com.luanoliveira.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InitialApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InitialApp.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}
	
}
