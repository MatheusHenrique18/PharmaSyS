package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {
	private Produto produtoCadastro;
	private List<Fabricante> listaFabSelect;
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	private String acao;
	private Long codigo;
	public Produto getProdutoCadastro() {
		return produtoCadastro;
	}
	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}
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
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public List<Fabricante> getListaFabSelect() {
		return listaFabSelect;
	}
	public void setListaFabSelect(List<Fabricante> listaFabSelect) {
		this.listaFabSelect = listaFabSelect;
	}
	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produtoCadastro);
			produtoCadastro = new Produto();
			
			FacesUtil.addMsgInfo("Produto salvo com sucesso !");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar incluir Produto: "+ ex.getMessage());
		}
	}
	
	public void limpar() {
		produtoCadastro = new Produto();
	}
	
	public void carregarPesquisa() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();
			
		}catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar os Produtos: "+ ex.getMessage());
		}
	}
	
	public void carregarCadastro() {
		try {
			if(codigo != null) {
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtoCadastro = produtoDAO.buscarPorCodigo(codigo);
			}else {
				produtoCadastro = new Produto();
			}
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabSelect = fabricanteDAO.listar();
			
		}catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar obter os dados do Produto: "+ ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produtoCadastro);
			
			FacesUtil.addMsgInfo("Produto excluído com sucesso !");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar excluir Produto: "+ ex.getMessage());
		}
		
	}
	
	public void editar() {

		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produtoCadastro);
			
			FacesUtil.addMsgInfo("Produto editado com sucesso !");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar editar Produto: "+ ex.getMessage());
		}
		
	}
	
}
