package com.luanoliveira.cursomc.dto;

import com.luanoliveira.cursomc.domain.City;

public class CityDTO {

	private Integer id;
	private String name;
	
	public CityDTO(City city){
		this.id = city.getId();
		this.name = city.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
