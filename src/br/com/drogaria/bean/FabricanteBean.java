package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean {

	private Fabricante fabricanteCadastro;
	private List<Fabricante> listaFabricantes;
	private List<Fabricante> listaFabricantesFiltrados;
	private String acao;
	private Long codigo;

	public Fabricante getFabricanteCadastro() {
		return fabricanteCadastro;
	}

	public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
	}

	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}
	
	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}
	
	public List<Fabricante> getListaFabricantesFiltrados() {
		return listaFabricantesFiltrados;
	}
	
	public void setListaFabricantesFiltrados(List<Fabricante> listaFabricantesFiltrados) {
		this.listaFabricantesFiltrados = listaFabricantesFiltrados;
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
	
	public void salvar() {

		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricanteCadastro);
			fabricanteCadastro = new Fabricante();
			
			FacesUtil.addMsgInfo("Fabricante salvo com sucesso !");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar incluir Fabricante: "+ ex.getMessage());
		}
		
	}
	
	public void limpar() {
		fabricanteCadastro = new Fabricante();
	}
	
	public void carregarPesquisa() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabricantes = fabricanteDAO.listar();
			
		}catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar os Fabricantes: "+ ex.getMessage());
		}
	}
	
	public void carregarCadastro() {
		try {
			
			if(codigo != null) {
				FabricanteDAO fabricanteDAO = new FabricanteDAO();
				fabricanteCadastro = fabricanteDAO.buscarPorCodigo(codigo);
			}else {
				fabricanteCadastro = new Fabricante();
			}
			
		}catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar obter os dados do Fabricante: "+ ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricanteCadastro);
			
			FacesUtil.addMsgInfo("Fabricante excluído com sucesso !");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar excluir Fabricante: "+ ex.getMessage());
		}
		
	}
	
	public void editar() {

		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.editar(fabricanteCadastro);
			
			FacesUtil.addMsgInfo("Fabricante editado com sucesso !");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar editar Fabricante: "+ ex.getMessage());
		}
		
	}
	
}
