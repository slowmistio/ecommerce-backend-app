package com.luanoliveira.cursomc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.luanoliveira.cursomc.domain.enuns.StatusPayment;

@Entity
@JsonTypeName("paymentCard")
public class PaymentCard extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer numberPayments;
	
	public PaymentCard() {
		
	}

	public PaymentCard(Integer id, StatusPayment status, Order order, Integer numberPayments) {
		super(id, status, order);
		this.numberPayments = numberPayments;
	}

	public Integer getNumberPayments() {
		return numberPayments;
	}

	public void setNumberPayments(Integer numberPayments) {
		this.numberPayments = numberPayments;
	}
	
	

	
}
