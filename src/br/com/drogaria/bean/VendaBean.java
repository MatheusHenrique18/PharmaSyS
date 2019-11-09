package br.com.drogaria.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private List<Item> listaItens;
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}
	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}
	public List<Item> getListaItens() {
		if(listaItens == null) {
			listaItens = new ArrayList<>();
		}
		return listaItens;
	}
	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}
	
	public void carregarProdutos() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();
			
		}catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar os Produtos: "+ ex.getMessage());
		}
	}
	
	public void adicionar(Produto produto) {
		
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidade(1);
		item.setValor(produto.getPreco());
		
		listaItens.add(item);
		
	}
	
	public void remover (Item item) {
		listaItens.remove(item);
	}
	
}
