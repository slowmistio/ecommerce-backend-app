package com.luanoliveira.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luanoliveira.cursomc.services.S3Service;

@SpringBootApplication
public class InitialApp implements CommandLineRunner {

	@Autowired
	private S3Service s3service;
	
	public static void main(String[] args) {
		SpringApplication.run(InitialApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3service.uploadFile("/home/hunter/Imagens/teste.jpeg");
		
	}
	
	
}
