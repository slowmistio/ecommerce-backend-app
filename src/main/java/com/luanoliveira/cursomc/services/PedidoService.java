package com.luanoliveira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.Pedido;
import com.luanoliveira.cursomc.repositories.PedidoRepository;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}

	public void excluir(Integer id) {
		repo.delete(id);
	}

	public List<Pedido> buscarTodos() {
		List<Pedido> obj = repo.findAll();
		return obj;
	}
	
}
