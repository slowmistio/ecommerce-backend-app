package com.luanoliveira.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanoliveira.cursomc.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
