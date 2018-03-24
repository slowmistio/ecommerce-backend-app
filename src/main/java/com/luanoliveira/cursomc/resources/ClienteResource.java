package com.luanoliveira.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.luanoliveira.cursomc.domain.Cliente;
import com.luanoliveira.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Cliente obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj) ;
	}

	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Cliente> obj = service.buscarTodos();
		
		return ResponseEntity.ok().body(obj) ;
	}


	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		service.excluir(id);
		
		return ResponseEntity.ok().body("");
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable Integer id) {
		return ResponseEntity.ok().body("");
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> insert() {
		return ResponseEntity.ok().body("");
	}
	
	
	
}