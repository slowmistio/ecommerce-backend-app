package com.luanoliveira.cursomc.config.environment;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.luanoliveira.cursomc.services.DBService;
import com.luanoliveira.cursomc.services.EmailService;
import com.luanoliveira.cursomc.services.MockEmailService;

@Configuration
@Profile("tst")
public class TstConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailservice() {
		return new MockEmailService();
	}
}
