package com.luanoliveira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.Category;
import com.luanoliveira.cursomc.domain.Product;
import com.luanoliveira.cursomc.repositories.CategoryRepository;
import com.luanoliveira.cursomc.repositories.ProductRepository;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public Product findById(Integer productId) {
		Product obj = productRepository.findOne(productId);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + productId
					+ ", Tipo: " + Product.class.getName());
		}
		return obj;
	}
	
	public List<Product> findAll() {
		List<Product> obj = productRepository.findAll();
		return obj;
	}
	
	public Page<Product> findByCategories(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAll(ids);		
		return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
	
}
