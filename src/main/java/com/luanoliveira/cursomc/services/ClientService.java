package com.luanoliveira.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.luanoliveira.cursomc.domain.Address;
import com.luanoliveira.cursomc.domain.City;
import com.luanoliveira.cursomc.domain.Client;
import com.luanoliveira.cursomc.domain.enuns.Profile;
import com.luanoliveira.cursomc.domain.enuns.TypeAddress;
import com.luanoliveira.cursomc.domain.enuns.TypeClient;
import com.luanoliveira.cursomc.dto.ClientDTO;
import com.luanoliveira.cursomc.dto.ClientNewDTO;
import com.luanoliveira.cursomc.repositories.AddressRepository;
import com.luanoliveira.cursomc.repositories.ClientRepository;
import com.luanoliveira.cursomc.security.UserSS;
import com.luanoliveira.cursomc.services.exceptions.AuthorizationException;
import com.luanoliveira.cursomc.services.exceptions.DataIntegrityException;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired 
	private S3Service s3Service;
	
	@Autowired 
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer	 size;
	
	public Client findById(Integer clientId) {
		
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !clientId.equals(user.getId())){
			throw new AuthorizationException("Acesso Negado");
		}
		
		Client obj = repo.findOne(clientId);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + clientId
					+ ", Tipo: " + Client.class.getName());
		}
		return obj; 
	}

	public Client findByEmail(String email) {
		
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())){
			throw new AuthorizationException("Acesso Negado");
		}
		
		Client obj = repo.findByEmail(email);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Email: " + email + ", Tipo: " + Client.class.getName());
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
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null, null);
	}	
	
	public Client fromDTO(ClientNewDTO objDTO) {
		Client cli = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOrCnpj(), TypeClient.toEnum(objDTO.getTypeClient()), bCrypt.encode(objDTO.getPassword()));
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
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		BufferedImage jpgImage = imageService.getJPGImagemFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		
	}
	
}
