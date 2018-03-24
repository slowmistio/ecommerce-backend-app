package com.luanoliveira.cursomc;

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
import com.luanoliveira.cursomc.domain.Produto;
import com.luanoliveira.cursomc.domain.enuns.TipoCliente;
import com.luanoliveira.cursomc.domain.enuns.TipoEndereco;
import com.luanoliveira.cursomc.repositories.CategoriaRepository;
import com.luanoliveira.cursomc.repositories.CidadeRepository;
import com.luanoliveira.cursomc.repositories.ClienteRepository;
import com.luanoliveira.cursomc.repositories.EnderecoRepository;
import com.luanoliveira.cursomc.repositories.EstadoRepository;
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
		
	}
	
}
