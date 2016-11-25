package br.com.jkavdev.javaweb.comercio.test;

import org.junit.Test;

import br.com.jkavdev.javaweb.hibernate.comercio.util.HibernateUtility;

public class HibernateConnectionTest {

	@Test
	public void connectionTest() {
		HibernateUtility.getSessionFactory().openSession();

		System.out.println("Aberta conexão com o banco db_javaweb_comercio");
	}

}
