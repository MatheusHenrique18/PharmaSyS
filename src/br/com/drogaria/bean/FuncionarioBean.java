package br.com.drogaria.bean;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionarioCadastro;
	private List<Funcionario> listaFuncionarios;
	private List<Funcionario> listaFuncionariosFiltrados;
	private String acao;
	private Long codigo;
	
	public Funcionario getFuncionarioCadastro() {
		if(funcionarioCadastro == null) {
			funcionarioCadastro = new Funcionario();
		}
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}
	
	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}
	
	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
	
	public List<Funcionario> getListaFuncionariosFiltrados() {
		return listaFuncionariosFiltrados;
	}
	
	public void setListaFuncionariosFiltrados(List<Funcionario> listaFuncionariosFiltrados) {
		this.listaFuncionariosFiltrados = listaFuncionariosFiltrados;
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
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.salvar(funcionarioCadastro);
			funcionarioCadastro = new Funcionario();

			FacesUtil.addMsgInfo("Funcionário salvo com sucesso !");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar salvar Funcionário !" + ex.getMessage());
		}
	}

	public void limpar() {
		funcionarioCadastro = new Funcionario();
	}
	
	public void carregarPesquisa() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			listaFuncionarios = funcionarioDAO.listar();
			
		}catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar os Funcionários: "+ ex.getMessage());
		}
	}
	
	public void carregarCadastro() {
		try {
			
			if(codigo != null) {
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionarioCadastro = funcionarioDAO.buscarPorCodigo(codigo);
			}else {
				funcionarioCadastro = new Funcionario();
			}
			
		}catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar obter os dados do Funcionario: "+ ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionarioCadastro);
			
			FacesUtil.addMsgInfo("Funcionario excluído com sucesso !");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar excluir Funcionario: "+ ex.getMessage());
		}
		
	}
	
	public void editar() {

		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.editar(funcionarioCadastro);
			
			FacesUtil.addMsgInfo("Funcionario alterado com sucesso !");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar editar Funcionario: "+ ex.getMessage());
		}
		
	}
}
