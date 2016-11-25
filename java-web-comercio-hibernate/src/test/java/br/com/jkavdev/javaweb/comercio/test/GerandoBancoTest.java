package br.com.jkavdev.javaweb.comercio.test;

import org.junit.Test;

import br.com.jkavdev.javaweb.hibernate.comercio.util.HibernateUtility;

public class GerandoBancoTest {

	@Test
	public void geraBancoTest() {

		HibernateUtility.getSessionFactory().openSession();

		HibernateUtility.getSessionFactory().getCurrentSession().close();

		HibernateUtility.getSessionFactory().close();

	}

}
