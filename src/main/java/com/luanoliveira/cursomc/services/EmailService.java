package com.luanoliveira.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.luanoliveira.cursomc.domain.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order order);
	
	void sendEmail(SimpleMailMessage msg);
	
}
