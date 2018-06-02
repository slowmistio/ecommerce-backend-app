package com.luanoliveira.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.luanoliveira.cursomc.domain.Category;
import com.luanoliveira.cursomc.dto.CategoryDTO;
import com.luanoliveira.cursomc.services.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "categories")
@RestController
@RequestMapping(value="/api/categories")
public class CategoriesResource {
	
	@Autowired
	private CategoryService service;

	@ApiOperation("Find all categories")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll() {
		
		List<Category> list = service.findAll();
		List<CategoryDTO> listDTO = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO) ;
	}
	
	@ApiOperation("Find category by ID ")
	@RequestMapping(value="/{categoryId}", method=RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer categoryId) {
		
		Category obj = service.findById(categoryId);
		return ResponseEntity.ok().body(obj) ;
	}
	
	@ApiOperation("Find categories by pagination")
	@RequestMapping(value="/pagination", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="order", defaultValue="name") String orderBy, 
			@RequestParam(value="dir", defaultValue="ASC") String direction) {
		
		Page<Category> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> listDTO = list.map(obj -> new CategoryDTO(obj));
		return ResponseEntity.ok().body(listDTO) ;
	}	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Create a new category")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO category) {
		
		Category obj = service.fromDTO(category);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Update an existing category")
	@RequestMapping(value="/{categoryId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO category, @PathVariable Integer categoryId) {
		
		Category obj = service.fromDTO(category);
		obj.setId(categoryId);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Delete category by ID")
	@RequestMapping(value="/{categoryId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer categoryId) {
		
		service.delete(categoryId);
		return ResponseEntity.noContent().build();
	}
	
	
	
}