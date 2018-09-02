package com.luanoliveira.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.luanoliveira.cursomc.domain.enuns.Profile;
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

@Service
public class DBService {

	@Autowired
	BCryptPasswordEncoder bCrypt;

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

	public void instantiateTestDatabase() throws ParseException {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 400.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV Smart", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);

		Product p12 = new Product(null, "Product 12", 10.00);
		Product p13 = new Product(null, "Product 13", 10.00);
		Product p14 = new Product(null, "Product 14", 10.00);
		Product p15 = new Product(null, "Product 15", 10.00);
		Product p16 = new Product(null, "Product 16", 10.00);
		Product p17 = new Product(null, "Product 17", 10.00);
		Product p18 = new Product(null, "Product 18", 10.00);
		Product p19 = new Product(null, "Product 19", 10.00);
		Product p20 = new Product(null, "Product 20", 10.00);
		Product p21 = new Product(null, "Product 21", 10.00);
		Product p22 = new Product(null, "Product 22", 10.00);
		Product p23 = new Product(null, "Product 23", 10.00);
		Product p24 = new Product(null, "Product 24", 10.00);
		Product p25 = new Product(null, "Product 25", 10.00);
		Product p26 = new Product(null, "Product 26", 10.00);
		Product p27 = new Product(null, "Product 27", 10.00);
		Product p28 = new Product(null, "Product 28", 10.00);
		Product p29 = new Product(null, "Product 29", 10.00);
		Product p30 = new Product(null, "Product 30", 10.00);
		Product p31 = new Product(null, "Product 31", 10.00);
		Product p32 = new Product(null, "Product 32", 10.00);
		Product p33 = new Product(null, "Product 33", 10.00);
		Product p34 = new Product(null, "Product 34", 10.00);
		Product p35 = new Product(null, "Product 35", 10.00);
		Product p36 = new Product(null, "Product 36", 10.00);
		Product p37 = new Product(null, "Product 37", 10.00);
		Product p38 = new Product(null, "Product 38", 10.00);
		Product p39 = new Product(null, "Product 39", 10.00);
		Product p40 = new Product(null, "Product 40", 10.00);
		Product p41 = new Product(null, "Product 41", 10.00);
		Product p42 = new Product(null, "Product 42", 10.00);
		Product p43 = new Product(null, "Product 43", 10.00);
		Product p44 = new Product(null, "Product 44", 10.00);
		Product p45 = new Product(null, "Product 45", 10.00);
		Product p46 = new Product(null, "Product 46", 10.00);
		Product p47 = new Product(null, "Product 47", 10.00);
		Product p48 = new Product(null, "Product 48", 10.00);
		Product p49 = new Product(null, "Product 49", 10.00);
		Product p50 = new Product(null, "Product 50", 10.00);
		
		cat1.getProducts().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		p12.getCategories().add(cat1);
		p13.getCategories().add(cat1);
		p14.getCategories().add(cat1);
		p15.getCategories().add(cat1);
		p16.getCategories().add(cat1);
		p17.getCategories().add(cat1);
		p18.getCategories().add(cat1);
		p19.getCategories().add(cat1);
		p20.getCategories().add(cat1);
		p21.getCategories().add(cat1);
		p22.getCategories().add(cat1);
		p23.getCategories().add(cat1);
		p24.getCategories().add(cat1);
		p25.getCategories().add(cat1);
		p26.getCategories().add(cat1);
		p27.getCategories().add(cat1);
		p28.getCategories().add(cat1);
		p29.getCategories().add(cat1);
		p30.getCategories().add(cat1);
		p31.getCategories().add(cat1);
		p32.getCategories().add(cat1);
		p33.getCategories().add(cat1);
		p34.getCategories().add(cat1);
		p35.getCategories().add(cat1);
		p36.getCategories().add(cat1);
		p37.getCategories().add(cat1);
		p38.getCategories().add(cat1);
		p39.getCategories().add(cat1);
		p40.getCategories().add(cat1);
		p41.getCategories().add(cat1);
		p42.getCategories().add(cat1);
		p43.getCategories().add(cat1);
		p44.getCategories().add(cat1);
		p45.getCategories().add(cat1);
		p46.getCategories().add(cat1);
		p47.getCategories().add(cat1);
		p48.getCategories().add(cat1);
		p49.getCategories().add(cat1);
		p50.getCategories().add(cat1);
		
		categoryRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		productRepository.save(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20));
		productRepository.save(Arrays.asList(p21, p22, p23, p24, p25, p26, p27, p28, p29 ));
		productRepository.save(Arrays.asList(p30, p31, p32, p34, p35, p36, p37, p38));
		productRepository.save(Arrays.asList(p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		State est1 = new State(null, "Santa Catarina");
		City cid1 = new City(null, "Florianópolis", est1);
		City cid2 = new City(null, "Joinville", est1);
		est1.getCities().addAll(Arrays.asList(cid1, cid2));

		State est2 = new State(null, "Paraná");
		City cid3 = new City(null, "Curitiba", est2);
		City cid4 = new City(null, "Londrina", est2);
		City cid5 = new City(null, "Cascavel", est2);
		est2.getCities().addAll(Arrays.asList(cid3, cid4, cid5));

		stateRepository.save(Arrays.asList(est1, est2));
		cityRepository.save(Arrays.asList(cid1, cid2, cid3, cid4, cid5));

		Client cli1 = new Client(null, "Luan Oliveira", "luannn@gmail.com", "05049043964", TypeClient.PESSOAFISICA, bCrypt.encode("123") );
		cli1.getPhones().addAll(Arrays.asList("47996665106"));

		Client cli2 = new Client(null, "Charlise Aguilera", "charlise.ag@gmail.com", "95498924097", TypeClient.PESSOAFISICA, bCrypt.encode("123") );
		cli2.getPhones().addAll(Arrays.asList("47996665106", "4734456669"));
		cli2.addProfile(Profile.ADMIN);
		
		Address e1 = new Address(null, TypeAddress.RESIDENCIAL, "Rua Herman Lange", "31", "ap2", "Costa e Silva", "89219260", cid2, cli1);
		Address e2 = new Address(null, TypeAddress.COMERCIAL, "Rua Otto Boehn", "442", "", "Atiradores", "89222111", cid2, cli1);
		Address e3 = new Address(null, TypeAddress.RESIDENCIAL, "Rua Adalberto Schamlz", "544", "", "Glória", "89219220", cid2, cli2);

		cli1.getAddresses().addAll(Arrays.asList(e1, e2));
		cli2.getAddresses().addAll(Arrays.asList(e3));

		clientRepository.save(Arrays.asList(cli1, cli2));
		addressRepository.save(Arrays.asList(e1, e2, e3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Order ped2 = new Order(null, sdf.parse("10/10/2017 10:32"), cli1, e2);

		Payment pagto1 = new PaymentCard(null, StatusPayment.QUITADO, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new PaymentTicket(null, StatusPayment.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getOrders().addAll(Arrays.asList(ped1, ped2));

		orderRepository.save(Arrays.asList(ped1, ped2));
		paymentRepository.save(Arrays.asList(pagto1, pagto2));

		ItemOrder ip1 = new ItemOrder(ped1, p1, 0.00, 1, 2000.00);
		ItemOrder ip2 = new ItemOrder(ped1, p3, 0.00, 3, 80.00);
		ItemOrder ip3 = new ItemOrder(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped1.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p3.getItens().addAll(Arrays.asList(ip2));
		p2.getItens().addAll(Arrays.asList(ip3));

		itemOrderRepository.save(Arrays.asList(ip1, ip2, ip3));

	}

}
