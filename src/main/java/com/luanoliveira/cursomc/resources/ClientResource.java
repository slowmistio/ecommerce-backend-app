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

import com.luanoliveira.cursomc.domain.Client;
import com.luanoliveira.cursomc.dto.ClientDTO;
import com.luanoliveira.cursomc.dto.ClientNewDTO;
import com.luanoliveira.cursomc.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Client> list = service.findAll();
		return ResponseEntity.ok().body(list) ;
	}

	@RequestMapping(value="/{clientId}", method = RequestMethod.GET)
	public ResponseEntity<Client> findById(@PathVariable Integer clientId) {
		
		Client obj = service.findById(clientId);
		return ResponseEntity.ok().body(obj) ;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/pagination", method=RequestMethod.GET)
	public ResponseEntity<Page<Client>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="order", defaultValue="name") String orderBy, 
			@RequestParam(value="dir", defaultValue="ASC") String direction) {
		
		Page<Client> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list) ;
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO client) {
		
		Client obj = service.fromDTO(client);	
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{clientId}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{clientId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO client, @PathVariable Integer clientId) {
		
		Client obj = service.fromDTO(client);
		obj.setId(clientId);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{clientId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer clientId) {
		
		service.delete(clientId);
		return ResponseEntity.noContent().build();
	}
	
}