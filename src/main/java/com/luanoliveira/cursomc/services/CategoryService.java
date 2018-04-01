package com.luanoliveira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.Category;
import com.luanoliveira.cursomc.dto.CategoryDTO;
import com.luanoliveira.cursomc.repositories.CategoryRepository;
import com.luanoliveira.cursomc.services.exceptions.DataIntegrityException;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;

	public List<Category> findAll() {
		List<Category> obj = repo.findAll();
		return obj;
	}

	public Category findById(Integer categoryId) {
		Category obj = repo.findOne(categoryId);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + categoryId
					+ ", Tipo: " + Category.class.getName());
		}
		return obj;
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO objDTO) {
		return new Category(objDTO.getId(), objDTO.getName());
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		Category newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer categoryId) {
		findById(categoryId);
		try {
			repo.delete(categoryId);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir a categoria pois produtos associados");
		}
	}
	
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}

	
}
