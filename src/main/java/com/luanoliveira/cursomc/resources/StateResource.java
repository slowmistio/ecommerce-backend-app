package com.luanoliveira.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.luanoliveira.cursomc.domain.City;
import com.luanoliveira.cursomc.domain.State;
import com.luanoliveira.cursomc.dto.CityDTO;
import com.luanoliveira.cursomc.dto.StateDTO;
import com.luanoliveira.cursomc.services.CityService;
import com.luanoliveira.cursomc.services.StateService;

@RestController
@RequestMapping(value="/states")
public class StateResource {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll() {
		
		List<State> list = stateService.findAll();
		List<StateDTO> listDTO = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO) ;
	}	

	@RequestMapping(method=RequestMethod.GET, path="/{id}/cities")
	public ResponseEntity<List<CityDTO>> findCitiesByState(@PathVariable Integer id) {
		
		List<City> list = cityService.findCities(id);
		List<CityDTO> listDTO = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO) ;
	}	
	
}