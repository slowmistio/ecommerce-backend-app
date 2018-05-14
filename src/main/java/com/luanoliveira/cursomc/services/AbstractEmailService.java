package com.luanoliveira.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.luanoliveira.cursomc.domain.Order;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;	
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Order order) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(order);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order order) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(order.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado! Código: " + order.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(order.toString());
		return sm;
	}
	
	protected String htmlFromTemplatePedido(Order order) {
		Context context = new  Context();
		context.setVariable("order", order);
		return templateEngine.process("email/confirmationOrder", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Order order) {
		try {
			MimeMessage mm = prepareMimeMessageFromOrder(order);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(order);
		}
	}

	protected MimeMessage prepareMimeMessageFromOrder(Order order) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(order.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido Confirmado! Código: " + order.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(order), true);
		return mimeMessage;
	}
}
