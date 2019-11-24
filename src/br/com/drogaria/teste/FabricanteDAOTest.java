package br.com.drogaria.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Fabricante f1 = new Fabricante();
		f1.setDescricao("DescricaoF");

		Fabricante f2 = new Fabricante();
		f2.setDescricao("DescricaoG");

		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(f1);
		dao.salvar(f2);

	}

	@Test
	@Ignore
	public void listar() {
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar();

		for (Fabricante fabricante : fabricantes) {
			System.out.println(fabricante);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FabricanteDAO dao = new FabricanteDAO();

		Fabricante f1 = dao.buscarPorCodigo(2L);

		System.out.println(f1);
	}

	@Test
	@Ignore
	public void excluir() {
		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(4L);
		fabricante.setDescricao("DescricaoB");

		FabricanteDAO dao = new FabricanteDAO();
		dao.excluir(fabricante);
	}

	@Test
	@Ignore
	public void editar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(2L);
		fabricante.setDescricao("testeX");

		FabricanteDAO dao = new FabricanteDAO();
		dao.editar(fabricante);
	}
	
}
