package br.com.drogaria.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;

public class ItemDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(4L);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(4L);
		
		ItemDAO itemDAO = new ItemDAO();
		Item item = new Item();
		
		item.setVenda(venda);
		item.setProduto(produto);
		item.setQuantidade(2);
		item.setValor(new BigDecimal(19.70D));
		
		itemDAO.salvar(item);
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		ItemDAO itemDAO = new ItemDAO();
		Item item = itemDAO.buscarPorCodigo(2L);
		
		System.out.println(item);
	}
	
	@Test
	@Ignore
	public void listar() {
		ItemDAO itemDAO = new ItemDAO();
		List<Item> itens = itemDAO.listar();
		
		for(Item item : itens) {
			System.out.println(item);
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(1L);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(7L);
		
		ItemDAO itemDAO = new ItemDAO();
		Item item = new Item();
		
		item.setCodigo(2L);
		item.setVenda(venda);
		item.setProduto(produto);
		item.setQuantidade(4);
		item.setValor(new BigDecimal(48.50D));
		
		itemDAO.editar(item);
	}
	
	@Test
	public void excluir() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(4L);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(4L);
		
		ItemDAO itemDAO = new ItemDAO();
		Item item = new Item();
		
		item.setCodigo(3L);
		item.setVenda(venda);
		item.setProduto(produto);
		item.setQuantidade(2);
		item.setValor(new BigDecimal(19.70D));
		
		itemDAO.excluir(item);
	}
	
}
