package com.luanoliveira.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanoliveira.cursomc.domain.Product;
import com.luanoliveira.cursomc.dto.ProductDTO;
import com.luanoliveira.cursomc.resources.utils.URL;
import com.luanoliveira.cursomc.services.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/api/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@ApiOperation("Find all product")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list) ;
	}

	@ApiOperation("Find product by ID")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Product.class)})
	@RequestMapping(value="/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> findById(@PathVariable Integer productId) {
		
		Product obj = service.findById(productId);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation("Find product by categories")
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findByCategories(
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="categories", defaultValue="") String categories,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="order", defaultValue="name") String orderBy, 
			@RequestParam(value="dir", defaultValue="ASC") String direction) {
	
		String nameDecoded = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categories);

		Page<Product> list = service.findByCategories(nameDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDto) ;
	}	
	
	
}