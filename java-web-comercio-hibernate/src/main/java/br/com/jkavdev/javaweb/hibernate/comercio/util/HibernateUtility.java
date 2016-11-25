package br.com.jkavdev.javaweb.hibernate.comercio.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

	private static final SessionFactory FACTORY = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");

			StandardServiceRegistryBuilder registradorServico = new StandardServiceRegistryBuilder();
			registradorServico.applySettings(configuration.getProperties());
			StandardServiceRegistry servico = registradorServico.build();

			return configuration.buildSessionFactory(servico);
		} catch (Throwable e) {
			System.out.println("Erro SessionFactory: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return FACTORY;
	}

}
