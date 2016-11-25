package br.com.jkavdev.javaweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

	String senha = "123456";
	String usuario = "jkavdev";
	String url = "jdbc:mysql://localhost:3306/db_javaweb_agenda";

	public Connection getConnection() {

		Connection conexao = null;

		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conexao;
	}
}
