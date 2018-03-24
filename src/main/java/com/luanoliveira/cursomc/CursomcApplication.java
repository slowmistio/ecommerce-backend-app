package com.luanoliveira.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luanoliveira.cursomc.domain.Categoria;
import com.luanoliveira.cursomc.domain.Cidade;
import com.luanoliveira.cursomc.domain.Cliente;
import com.luanoliveira.cursomc.domain.Endereco;
import com.luanoliveira.cursomc.domain.Estado;
import com.luanoliveira.cursomc.domain.ItemPedido;
import com.luanoliveira.cursomc.domain.Pagamento;
import com.luanoliveira.cursomc.domain.PagamentoBoleto;
import com.luanoliveira.cursomc.domain.PagamentoCartao;
import com.luanoliveira.cursomc.domain.Pedido;
import com.luanoliveira.cursomc.domain.Produto;
import com.luanoliveira.cursomc.domain.enuns.EstadoPagamento;
import com.luanoliveira.cursomc.domain.enuns.TipoCliente;
import com.luanoliveira.cursomc.domain.enuns.TipoEndereco;
import com.luanoliveira.cursomc.repositories.CategoriaRepository;
import com.luanoliveira.cursomc.repositories.CidadeRepository;
import com.luanoliveira.cursomc.repositories.ClienteRepository;
import com.luanoliveira.cursomc.repositories.EnderecoRepository;
import com.luanoliveira.cursomc.repositories.EstadoRepository;
import com.luanoliveira.cursomc.repositories.ItemPedidoRepository;
import com.luanoliveira.cursomc.repositories.PagamentoRepository;
import com.luanoliveira.cursomc.repositories.PedidoRepository;
import com.luanoliveira.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	PagamentoRepository pagamentoRepository;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 400.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(prod1,prod2,prod3));
		
		Estado est1 = new Estado(null, "Santa Catarina");
		Cidade cid1 = new Cidade(null, "Florianópolis", est1);
		Cidade cid2 = new Cidade(null, "Joinville", est1);
		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		
		Estado est2 = new Estado(null, "Paraná");
		Cidade cid3 = new Cidade(null, "Curitiba", est2);
		Cidade cid4 = new Cidade(null, "Londrina", est2);
		Cidade cid5 = new Cidade(null, "Cascavel", est2);
		est2.getCidades().addAll(Arrays.asList(cid3, cid4, cid5));
		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(cid1,cid2,cid3,cid4,cid5));
		
		Cliente c1 = new Cliente(null, "Luan Oliveira", "luannn@gmail.com", "05049043964", TipoCliente.PESSOAFISICA);
		c1.getTelefones().addAll(Arrays.asList("47996665106"));
		
		Endereco e1 = new Endereco(null, TipoEndereco.RESIDENCIAL, "Rua Herman Lange", "31", "ap2", "Costa e Silva", "89219260", cid2, c1);	
		Endereco e2 = new Endereco(null, TipoEndereco.COMERCIAL, "Rua Otto Boehn", "442", "", "Atiradores", "89222111", cid2, c1);	
		
		c1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.save(Arrays.asList(c1));
		enderecoRepository.save(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), c1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:32"), c1, e2);
		
		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		c1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pagto1,pagto2));		
		
		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 3, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped1.getItens().addAll(Arrays.asList(ip3));
		
		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip2));
		prod3.getItens().addAll(Arrays.asList(ip3));
		
		itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
		
	}
	
}
