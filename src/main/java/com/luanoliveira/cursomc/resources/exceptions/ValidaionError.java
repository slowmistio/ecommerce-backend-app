package com.luanoliveira.cursomc.resources.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidaionError extends StandardError implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidaionError(Long timestamp, Integer status, String error, String messsage, String path) {
		super(timestamp, status, error, messsage, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}


}
