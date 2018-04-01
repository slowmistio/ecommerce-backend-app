package com.luanoliveira.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.luanoliveira.cursomc.domain.enuns.StatusPayment;

@Entity
@JsonTypeName("paymentTicket")
public class PaymentTicket extends Payment {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date paymentDate;

	public PaymentTicket(){
		
	}

	public PaymentTicket(Integer id, StatusPayment status, Order order, Date dueDate, Date paymentDate) {
		super(id, status, order);
		this.paymentDate = paymentDate;
		this.dueDate = dueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	
	
}
