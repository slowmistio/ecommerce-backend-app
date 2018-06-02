package com.luanoliveira.cursomc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luanoliveira.cursomc.domain.Order;
import com.luanoliveira.cursomc.services.OrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/api/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Find all orders")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list) ;
	}

	@ApiOperation("Find order by ID")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Order.class)})
	@RequestMapping(value="/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<Order> findById(@PathVariable Integer orderId) {
		
		Order obj = service.findById(orderId);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Find orders by pagination")
	@RequestMapping(value="/pagination", method=RequestMethod.GET)
	public ResponseEntity<Page<Order>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="order", defaultValue="name") String orderBy, 
			@RequestParam(value="dir", defaultValue="ASC") String direction) {
		
		Page<Order> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list) ;
	}	
	
	@ApiOperation("Create a new order")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Order obj) {
		
		Order order = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{orderId}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}