package com.luanoliveira.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luanoliveira.cursomc.domain.Order;
import com.luanoliveira.cursomc.services.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;

	@RequestMapping(value="/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<Order> findById(@PathVariable Integer orderId) {
		
		Order obj = service.findById(orderId);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/pagination", method=RequestMethod.GET)
	public ResponseEntity<Page<Order>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="order", defaultValue="requestDate") String orderBy, 
			@RequestParam(value="dir", defaultValue="DESC") String direction) {
		
		Page<Order> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list) ;
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Order obj) {
		
		Order order = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{orderId}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}