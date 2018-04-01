package com.luanoliveira.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luanoliveira.cursomc.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
	
	@Transactional(readOnly = true)
	Client findByEmail(String email);
	
}
