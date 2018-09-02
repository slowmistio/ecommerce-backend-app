package com.luanoliveira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.State;
import com.luanoliveira.cursomc.repositories.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository repo;

	public List<State> findAll() {
		List<State> obj = repo.findAllByOrderByName();
		return obj;
	}

	
}
