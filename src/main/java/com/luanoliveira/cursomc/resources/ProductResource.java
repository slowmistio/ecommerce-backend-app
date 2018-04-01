package com.luanoliveira.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.luanoliveira.cursomc.domain.Product;
import com.luanoliveira.cursomc.services.ProductService;

@RestController
@RequestMapping(value="/api/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Product> obj = service.findAll();
		
		return ResponseEntity.ok().body(obj) ;
	}
	
}