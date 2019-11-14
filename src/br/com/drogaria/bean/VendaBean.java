package br.com.drogaria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private Venda vendaCadastro;
	
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
	public Venda getVendaCadastro() {
		if(vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00"));
		}
		return vendaCadastro;
	}
	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
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
		
		int posicaoEncontrada = -1;
		
		//percorrendo a lista, o laço termina ao achar o índice correto 
		for( int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0 ; pos++) {
			Item itemTemp = listaItens.get(pos);
			
			if(itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}
		}
		
		Item item = new Item();
		item.setProduto(produto);
		
		//Caso seja um produto novo na lista
		if(posicaoEncontrada < 0) {
			item.setQuantidade(1);
			item.setValor(produto.getPreco());
			listaItens.add(item);
			
		}else {		//Caso seja um produto que ja existe na lista, qtd e valor serão somados
			Item itemTemp = listaItens.get(posicaoEncontrada);
			item.setQuantidade(itemTemp.getQuantidade() +1);
			item.setValor(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			listaItens.set(posicaoEncontrada, item);
		}
		
		//Atualizando o valor total da venda
		vendaCadastro.setValor(vendaCadastro.getValor().add(produto.getPreco()));
		
	}
	
	public void remover (Item item) {
		
		int posicaoEncontrada = -1;
		
		//percorrendo a lista, o laço termina ao achar o índice correto
		for( int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0 ; pos++) {
			Item itemTemp = listaItens.get(pos);
			
			if(itemTemp.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = pos;
			}
		}
		
		if(posicaoEncontrada > -1) {
			listaItens.remove(posicaoEncontrada);
			//Atualizando o valor total da venda
			vendaCadastro.setValor(vendaCadastro.getValor().subtract(item.getValor()));
		}
		
	}
	
	public void carregarDadosVenda() {
		vendaCadastro.setHorario(new Date());
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionarioVenda = funcionarioDAO.buscarPorCodigo(1L);
		
		vendaCadastro.setFuncionario(funcionarioVenda);
	}
	
	public void salvar() {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			Long codigoVenda = vendaDAO.salvar(vendaCadastro);
			Venda vendaFK = vendaDAO.buscarPorCodigo(codigoVenda);
			
			for (Item item : listaItens) {
				item.setVenda(vendaFK);
				
				ItemDAO itemDAO = new ItemDAO();
				itemDAO.salvar(item);
			}
			
			vendaCadastro = new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00"));
			
			listaItens = new ArrayList<Item>();
			
			FacesUtil.addMsgInfo("Venda salva com sucesso !");
		}catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar os Produtos: "+ ex.getMessage());
		}
	}
	
}
