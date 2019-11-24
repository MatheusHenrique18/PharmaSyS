package br.com.drogaria.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;

public class FuncionarioDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Funcionario funcionario = new Funcionario();
		Funcionario funcionario2 = new Funcionario();

		funcionario.setNome("Joao Kleber");
		funcionario.setCpf("456.912.54-49");
		funcionario.setFuncao("Farmacêutico");
		funcionario.setSenha("senha098");

		funcionario2.setNome("Chaves");
		funcionario2.setCpf("555.555.666-32");
		funcionario2.setFuncao("Caixa");
		funcionario2.setSenha("senha@123");

		FuncionarioDAO dao = new FuncionarioDAO();
		dao.salvar(funcionario);
		dao.salvar(funcionario2);
	}

	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> funcionarios = funcionarioDAO.listar();

		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(1L);

		System.out.println(funcionario);
	}

	@Test
	@Ignore
	public void editar() {
		Funcionario funcionario = new Funcionario();

		funcionario.setCodigo(2L);
		funcionario.setCpf("999.999.999-99");
		funcionario.setFuncao("Farmacêutico");
		funcionario.setNome("Jeanderson");
		funcionario.setSenha("semsenha@2019");

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.editar(funcionario);
	}

	@Test
	@Ignore
	public void excluir() {
		Funcionario funcionario = new Funcionario();

		funcionario.setCodigo(5L);
		funcionario.setCpf("555.555.666-32");
		funcionario.setFuncao("Caixa");
		funcionario.setNome("Chaves");
		funcionario.setSenha("senha@123");

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.excluir(funcionario);
	}
	
	@Test
	@Ignore
	public void autenticar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.autenticar("123.456.789-09", "senha123");
		
		System.out.println("Funcionário: " + funcionario);
	}
}
