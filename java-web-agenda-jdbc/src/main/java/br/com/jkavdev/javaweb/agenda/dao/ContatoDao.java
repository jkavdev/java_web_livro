package br.com.jkavdev.javaweb.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jkavdev.javaweb.JdbcConnection;
import br.com.jkavdev.javaweb.agenda.modelo.Contato;

public class ContatoDao {

	Connection conexao = null;

	public ContatoDao() {
		conexao = new JdbcConnection().getConnection();
	}

	public void salvar(Contato contato) {
		PreparedStatement preparedStatement = null;
		String sql = "insert into contato(nome, telefone, email, data_cadastro, observacao) values(?, ?, ?, ?, ?)";

		try {
			preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.setString(1, contato.getNome());
			preparedStatement.setString(2, contato.getTelefone());
			preparedStatement.setString(3, contato.getEmail());
			preparedStatement.setDate(4, new Date(contato.getDataCadastro().getTime()));
			preparedStatement.setString(5, contato.getObservacao());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro ao incluir Contato: " + e.getMessage());

			try {
				preparedStatement.close();
				conexao.close();
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar operacoes: " + e.getMessage());
			}
		}
	}

	public List<Contato> listar() {
		String sql = "select * from Contato";
		List<Contato> contatos = new ArrayList<>();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			prepareStatement = conexao.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Contato contato = criaContato(resultSet);

				contatos.add(contato);
			}

			return contatos;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar contato: " + e.getMessage());

			try {
				resultSet.close();
				prepareStatement.close();
				conexao.close();
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar operacoes: " + e.getMessage());
			}
		}
		return contatos;
	}

	public List<Contato> findAllContatosByName(String nome) {
		String sql = "select * from Contatos c where c.nome like ?";
		List<Contato> contatos = new ArrayList<>();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setString(1, nome + "%");
			resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Contato contato = criaContato(resultSet);

				contatos.add(contato);
			}

			resultSet.close();
			prepareStatement.close();

			return contatos;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar contato: " + e.getMessage());

			try {
				resultSet.close();
				prepareStatement.close();
				conexao.close();
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar operacoes: " + e.getMessage());
			}
		}
		return contatos;
	}

	public Contato find(Long id) {
		String sql = "select * from Contatos c where c.id = ?";
		Contato contato = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setLong(1, id);
			resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				contato = criaContato(resultSet);
			}

			resultSet.close();
			prepareStatement.close();

			return contato;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar contato: " + e.getMessage());

			try {
				resultSet.close();
				prepareStatement.close();
				conexao.close();
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar operacoes: " + e.getMessage());
			}
		}
		return contato;

	}

	public void update(Contato contato) {
		String sql = "update Contatos "
				+ "set nome = ?, email = ?, endereco = ?, dataNascimento = ? "
				+ "where id = ?";
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setString(1, contato.getNome());
			prepareStatement.setString(2, contato.getTelefone());
			prepareStatement.setString(3, contato.getEmail());
			prepareStatement.setDate(4, new Date(contato.getDataCadastro() .getTime()));
			prepareStatement.setString(5, contato.getObservacao());
			prepareStatement.setLong(6, contato.getCodigo());

			prepareStatement.execute();
			prepareStatement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao incluir Contato: " + e.getMessage());

			try {
				prepareStatement.close();
				conexao.close();
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar operacoes: " + e.getMessage());
			}
		}
	}

	public void remove(Contato contato) {
		String sql = "delete from Contato where codigo = ?";
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setLong(1, contato.getCodigo());

			prepareStatement.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao incluir Contato: " + e.getMessage());

			try {
				prepareStatement.close();
				conexao.close();
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar operacoes: " + e.getMessage());
			}
		}
	}

	public Contato criaContato(ResultSet resultSet) throws SQLException {

		Contato contato = new Contato();
		contato.setCodigo(resultSet.getInt("codigo"));
		contato.setNome(resultSet.getString("nome"));
		contato.setTelefone(resultSet.getString("telefone"));
		contato.setEmail(resultSet.getString("email"));
		contato.setObservacao(resultSet.getString("observacao"));
		contato.setDataCadastro(new java.util.Date(resultSet.getDate("data_cadastro").getTime()));

		return contato;
	}

}
