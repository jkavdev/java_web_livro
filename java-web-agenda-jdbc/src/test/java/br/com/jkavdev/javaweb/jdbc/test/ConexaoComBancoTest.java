package br.com.jkavdev.javaweb.jdbc.test;

import org.junit.Test;

import br.com.jkavdev.javaweb.JdbcConnection;

public class ConexaoComBancoTest {

	@Test
	public void connectionTest() {
		new JdbcConnection().getConnection();

		System.out.println("Aberta conexão com o banco db_javaweb_agenda");

	}

}
