package com.luanoliveira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.Categoria;
import com.luanoliveira.cursomc.repositories.CategoriaRepository;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	public List<Categoria> findAll() {
		List<Categoria> obj = repo.findAll();
		return obj;
	}

	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
		}
		
	}
	
}
