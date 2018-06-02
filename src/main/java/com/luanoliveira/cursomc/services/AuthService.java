package com.luanoliveira.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luanoliveira.cursomc.domain.Client;
import com.luanoliveira.cursomc.repositories.ClientRepository;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	BCryptPasswordEncoder bCrypt;
	
	@Autowired
	EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Client client = clientRepository.findByEmail(email);
		if (client == null) {
			throw new ObjectNotFoundException("Email não encontrado.");
		}
		
		String newPass = newPassword();
		client.setPassword(bCrypt.encode(newPass));
		
		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
		
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i = 0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 0) { //gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else { // gera letra minuscula
			return (char) (rand.nextInt(10) + 97);
		}
	}
	
}
