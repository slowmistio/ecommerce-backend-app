package com.luanoliveira.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.PaymentTicket;


@Service
public class TicketService {

	public void putPaymentoTicket(PaymentTicket payment, Date requestDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(requestDate);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setDueDate(cal.getTime());
	}
	
}
