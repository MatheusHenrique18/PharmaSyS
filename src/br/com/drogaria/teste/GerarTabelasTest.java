package br.com.drogaria.teste;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.util.HibernateUtil;

public class GerarTabelasTest {
	@Test
	@Ignore
	public void gerar() {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}
}
