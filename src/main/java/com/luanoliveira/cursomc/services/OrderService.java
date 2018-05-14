package com.luanoliveira.cursomc.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luanoliveira.cursomc.domain.ItemOrder;
import com.luanoliveira.cursomc.domain.Order;
import com.luanoliveira.cursomc.domain.PaymentTicket;
import com.luanoliveira.cursomc.domain.enuns.StatusPayment;
import com.luanoliveira.cursomc.repositories.AddressRepository;
import com.luanoliveira.cursomc.repositories.ClientRepository;
import com.luanoliveira.cursomc.repositories.ItemOrderRepository;
import com.luanoliveira.cursomc.repositories.OrderRepository;
import com.luanoliveira.cursomc.repositories.PaymentRepository;
import com.luanoliveira.cursomc.repositories.ProductRepository;
import com.luanoliveira.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ItemOrderRepository itemOrderRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired 
	private EmailService emailService;
	
	public Order findById(Integer orderId) {
		
		Order obj = orderRepository.findOne(orderId);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + orderId
					+ ", Tipo: " + Order.class.getName());
		}
		return obj;
	}

	public List<Order> findAll() {
		
		List<Order> obj = orderRepository.findAll();
		return obj;
	}
	
	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return orderRepository.findAll(pageRequest);
	}
	
	@Transactional
	public Order insert(Order obj) {
		
		obj.setId(null); 
		obj.setRequestDate(new Date());
		obj.setClient(clientRepository.findOne(obj.getClient().getId()));
		obj.getPayment().setStatus(StatusPayment.PENDENTE);
		obj.getPayment().setOrder(obj);
		obj.setShippingAddress(addressRepository.findOne(obj.getShippingAddress().getId()));
		if (obj.getPayment() instanceof PaymentTicket ) {
			PaymentTicket payment = (PaymentTicket) obj.getPayment();
			ticketService.putPaymentoTicket(payment, obj.getRequestDate());
		}
		obj = orderRepository.save(obj);
		paymentRepository.save(obj.getPayment());
		for (ItemOrder io : obj.getItens()) {
			io.setDiscount(0.0);
			io.setProduct(productRepository.findOne(io.getProduct().getId()));
			io.setPrice(io.getProduct().getPrice());
			io.setOrder(obj);
		}
		itemOrderRepository.save(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		System.out.println(obj.toString());
		return obj;
	}
	
}
