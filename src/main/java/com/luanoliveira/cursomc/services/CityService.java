package com.luanoliveira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.City;
import com.luanoliveira.cursomc.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repo;

	public List<City> findCities(Integer id) {
		List<City> obj = repo.findCities(id);
		return obj;
	}

	
}
