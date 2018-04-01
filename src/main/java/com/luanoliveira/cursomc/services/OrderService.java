package com.luanoliveira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.Order;
import com.luanoliveira.cursomc.repositories.OrderRepository;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;

	public Order findById(Integer orderId) {
		
		Order obj = repo.findOne(orderId);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + orderId
					+ ", Tipo: " + Order.class.getName());
		}
		return obj;
	}

	public List<Order> findAll() {
		
		List<Order> obj = repo.findAll();
		return obj;
	}
	
	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Order insert(Order obj) {
		
		return repo.save(obj);
	}
	
}
