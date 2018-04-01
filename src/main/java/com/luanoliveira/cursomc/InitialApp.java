package com.luanoliveira.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luanoliveira.cursomc.domain.Address;
import com.luanoliveira.cursomc.domain.Category;
import com.luanoliveira.cursomc.domain.City;
import com.luanoliveira.cursomc.domain.Client;
import com.luanoliveira.cursomc.domain.ItemOrder;
import com.luanoliveira.cursomc.domain.Order;
import com.luanoliveira.cursomc.domain.Payment;
import com.luanoliveira.cursomc.domain.PaymentCard;
import com.luanoliveira.cursomc.domain.PaymentTicket;
import com.luanoliveira.cursomc.domain.Product;
import com.luanoliveira.cursomc.domain.State;
import com.luanoliveira.cursomc.domain.enuns.StatusPayment;
import com.luanoliveira.cursomc.domain.enuns.TypeAddress;
import com.luanoliveira.cursomc.domain.enuns.TypeClient;
import com.luanoliveira.cursomc.repositories.AddressRepository;
import com.luanoliveira.cursomc.repositories.CategoryRepository;
import com.luanoliveira.cursomc.repositories.CityRepository;
import com.luanoliveira.cursomc.repositories.ClientRepository;
import com.luanoliveira.cursomc.repositories.ItemOrderRepository;
import com.luanoliveira.cursomc.repositories.OrderRepository;
import com.luanoliveira.cursomc.repositories.PaymentRepository;
import com.luanoliveira.cursomc.repositories.ProductRepository;
import com.luanoliveira.cursomc.repositories.StateRepository;

@SpringBootApplication
public class InitialApp implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ItemOrderRepository itemOrderRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(InitialApp.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		
		Product prod1 = new Product(null, "Computador", 2000.00);
		Product prod2 = new Product(null, "Impressora", 400.00);
		Product prod3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProducts().addAll(Arrays.asList(prod2));
		
		prod1.getCategories().addAll(Arrays.asList(cat1));
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.save(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		productRepository.save(Arrays.asList(prod1,prod2,prod3));
		
		State est1 = new State(null, "Santa Catarina");
		City cid1 = new City(null, "Florianópolis", est1);
		City cid2 = new City(null, "Joinville", est1);
		est1.getCities().addAll(Arrays.asList(cid1, cid2));
		
		State est2 = new State(null, "Paraná");
		City cid3 = new City(null, "Curitiba", est2);
		City cid4 = new City(null, "Londrina", est2);
		City cid5 = new City(null, "Cascavel", est2);
		est2.getCities().addAll(Arrays.asList(cid3, cid4, cid5));
		
		stateRepository.save(Arrays.asList(est1,est2));
		cityRepository.save(Arrays.asList(cid1,cid2,cid3,cid4,cid5));
		
		Client cli1 = new Client(null, "Luan Oliveira", "luannn@gmail.com", "05049043964", TypeClient.PESSOAFISICA);
		cli1.getPhones().addAll(Arrays.asList("47996665106"));
		
		Address e1 = new Address(null, TypeAddress.RESIDENCIAL, "Rua Herman Lange", "31", "ap2", "Costa e Silva", "89219260", cid2, cli1);	
		Address e2 = new Address(null, TypeAddress.COMERCIAL, "Rua Otto Boehn", "442", "", "Atiradores", "89222111", cid2, cli1);	
		
		cli1.getAddresses().addAll(Arrays.asList(e1, e2));

		clientRepository.save(Arrays.asList(cli1));
		addressRepository.save(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Order ped2 = new Order(null, sdf.parse("10/10/2017 10:32"), cli1, e2);
		
		Payment pagto1 = new PaymentCard(null, StatusPayment.QUITADO, ped1, 6);
		ped1.setPayment(pagto1);
		
		Payment pagto2 = new PaymentTicket(null, StatusPayment.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);
		
		cli1.getOrders().addAll(Arrays.asList(ped1,ped2));
		
		orderRepository.save(Arrays.asList(ped1,ped2));
		paymentRepository.save(Arrays.asList(pagto1,pagto2));		
		
		ItemOrder ip1 = new ItemOrder(ped1, prod1, 0.00, 1, 2000.00);
		ItemOrder ip2 = new ItemOrder(ped1, prod3, 0.00, 3, 80.00);
		ItemOrder ip3 = new ItemOrder(ped2, prod2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped1.getItens().addAll(Arrays.asList(ip3));
		
		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip2));
		prod3.getItens().addAll(Arrays.asList(ip3));
		
		itemOrderRepository.save(Arrays.asList(ip1,ip2,ip3));
		
	}
	
}
