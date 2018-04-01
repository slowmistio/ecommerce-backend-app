package com.luanoliveira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.Order;
import com.luanoliveira.cursomc.repositories.OrderRepository;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;

	public Order find(Integer id) {
		Order obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Order.class.getName());
		}
		return obj;
	}

	public List<Order> findAll() {
		List<Order> obj = repo.findAll();
		return obj;
	}

	public void delete(Integer id) {
		repo.delete(id);
	}
	
}
