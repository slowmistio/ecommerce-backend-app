package com.luanoliveira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.Categoria;
import com.luanoliveira.cursomc.domain.Cliente;
import com.luanoliveira.cursomc.repositories.ClienteRepository;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}

	public List<Cliente> findAll() {
		List<Cliente> obj = repo.findAll();
		return obj;
	}
	
}
