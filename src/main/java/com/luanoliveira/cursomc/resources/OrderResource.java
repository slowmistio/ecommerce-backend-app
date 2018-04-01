package com.luanoliveira.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.luanoliveira.cursomc.domain.Order;
import com.luanoliveira.cursomc.services.OrderService;

@RestController
@RequestMapping(value="/api/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Order> obj = service.findAll();
		
		return ResponseEntity.ok().body(obj) ;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Order> find(@PathVariable Integer id) {
		
		Order obj = service.find(id);
		
		return ResponseEntity.ok().body(obj) ;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> insert() {
		return ResponseEntity.ok().body("");
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		service.delete(id);
		
		return ResponseEntity.ok().body("");
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable Integer id) {
		return ResponseEntity.ok().body("");
	}
	
	
	
	
}