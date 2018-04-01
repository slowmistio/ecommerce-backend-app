package com.luanoliveira.cursomc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.luanoliveira.cursomc.resources.exceptions.ValidaionError;
import com.luanoliveira.cursomc.services.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/api/clients")
@Api(value = "Clients Resource REST Endpoint.", 
	produces = MediaType.APPLICATION_JSON_VALUE,
	description = "Show the client info.")
public class ClientResource {
	
	@Autowired
	private ClientService service;

	@ApiOperation("Get All Clients")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Client> list = service.findAll();
		return ResponseEntity.ok().body(list) ;
	}

	@ApiOperation("Get Categories for ID ")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Client.class)})
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		
		Client obj = service.find(id);
		return ResponseEntity.ok().body(obj) ;
	}
	
	@ApiOperation("Get Clients for Pagination ")
	@RequestMapping(value="/pagination", method=RequestMethod.GET)
	public ResponseEntity<Page<Client>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="order", defaultValue="name") String orderBy, 
			@RequestParam(value="dir", defaultValue="ASC") String direction) {
		
		Page<Client> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list) ;
	}	
	
	@ApiOperation("Insert a Client")
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "Created"),
					@ApiResponse(code = 400, message = "Invalidation", response = ValidaionError.class)
				})
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO objDTO) {
		Client obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Update data a Client")
	@ApiResponses(
			value = {
					@ApiResponse(code = 204, message = "Updated"),
					@ApiResponse(code = 400, message = "Invalidation", response = ValidaionError.class)
				})
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO client, @PathVariable Integer id) {
		Client obj = service.fromDTO(client);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Delete a Client")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}