package com.luanoliveira.cursomc.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luanoliveira.cursomc.domain.Categoria;
import com.luanoliveira.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Categoria> obj = service.findAll();
		
		return ResponseEntity.ok().body(obj) ;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Categoria obj = service.find(id);
		
		return ResponseEntity.ok().body(obj) ;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(Categoria obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
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