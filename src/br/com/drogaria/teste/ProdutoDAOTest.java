package br.com.drogaria.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(10L);
		
		Produto produto = new Produto();
		produto.setDescricao("Paracetamol");
		produto.setPreco(new BigDecimal(8.99D));
		produto.setQuantidade(35);
		produto.setFabricante(fabricante);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(3L);
		
		System.out.println(produto);
	}
	
	@Test
	@Ignore
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos = produtoDAO.listar();
		
		for(Produto produto: produtos) {
			System.out.println(produto);
		}
	}
	@Test
	@Ignore
	public void excluir() {
		Produto produto = new Produto();
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(9L);
		
		produto.setCodigo(2L);
		produto.setDescricao("Descricao y");
		produto.setPreco(new BigDecimal(45.99D));
		produto.setQuantidade(10);
		produto.setFabricante(fabricante);
		
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.excluir(produto);
		
	}
	
	@Test
	@Ignore
	public void editar() {
		Produto produto = new Produto();
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(2L);
		
		produto.setCodigo(5L);
		produto.setDescricao("Lactopurga");
		produto.setPreco(new BigDecimal(11.85D));
		produto.setQuantidade(20);
		produto.setFabricante(fabricante);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.editar(produto);
	}
}
