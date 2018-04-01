package com.luanoliveira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luanoliveira.cursomc.domain.Address;
import com.luanoliveira.cursomc.domain.City;
import com.luanoliveira.cursomc.domain.Client;
import com.luanoliveira.cursomc.domain.enuns.TypeAddress;
import com.luanoliveira.cursomc.domain.enuns.TypeClient;
import com.luanoliveira.cursomc.dto.ClientDTO;
import com.luanoliveira.cursomc.dto.ClientNewDTO;
import com.luanoliveira.cursomc.repositories.AddressRepository;
import com.luanoliveira.cursomc.repositories.ClientRepository;
import com.luanoliveira.cursomc.services.exceptions.DataIntegrityException;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Client findById(Integer clientId) {
		Client obj = repo.findOne(clientId);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + clientId
					+ ", Tipo: " + Client.class.getName());
		}
		return obj;
	}

	public List<Client> findAll() {
		List<Client> obj = repo.findAll();
		return obj;
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null);
	}	
	
	public Client fromDTO(ClientNewDTO objDTO) {
		Client cli = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOrCnpj(), TypeClient.toEnum(objDTO.getTypeClient()));
		City cid = new City(objDTO.getCityId(), null, null);
		Address end = new Address(null, TypeAddress.toEnum(objDTO.getTypeAdress()), objDTO.getAddress(), objDTO.getNumber(), objDTO.getComplement(), objDTO.getDistrict(), objDTO.getZipCode(), cid, cli);
		cli.getAddresses().add(end);
		cli.getPhones().add(objDTO.getPhone1());
		if(objDTO.getPhone2() != null) cli.getPhones().add(objDTO.getPhone2());
		if(objDTO.getPhone3() != null) cli.getPhones().add(objDTO.getPhone3());
		return cli;
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.save(obj.getAddresses());	
		return obj;
	}

	public Client update(Client obj) {
		Client newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer clientId) {
		findById(clientId);
		try {
			repo.delete(clientId);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir o cliente pois há pedidos relacionados");
		}
	}	
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
