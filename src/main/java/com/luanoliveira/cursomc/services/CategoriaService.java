package com.luanoliveira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.Category;
import com.luanoliveira.cursomc.dto.CategoriaDTO;
import com.luanoliveira.cursomc.repositories.CategoriaRepository;
import com.luanoliveira.cursomc.services.exceptions.DataIntegrityException;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	public List<Category> findAll() {
		List<Category> obj = repo.findAll();
		return obj;
	}

	public Category find(Integer id) {
		Category obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Category.class.getName());
		}
		return obj;
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoriaDTO objDTO) {
		return new Category(objDTO.getId(), objDTO.getNome());
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		Category newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir a categoria pois produtos associados");
		}
	}
	
	private void updateData(Category newObj, Category obj) {
		newObj.setNome(obj.getNome());
	}

	
}
