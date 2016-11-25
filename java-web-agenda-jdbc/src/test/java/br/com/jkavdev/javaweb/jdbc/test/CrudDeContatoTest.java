package br.com.jkavdev.javaweb.jdbc.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.jkavdev.javaweb.agenda.dao.ContatoDao;
import br.com.jkavdev.javaweb.agenda.modelo.Contato;

public class CrudDeContatoTest {

	ContatoDao contatoDao;

	@Before
	public void init() {
		contatoDao = new ContatoDao();
	}

	@Test
	public void salvarContatoTest() {
		Contato jhonatan = createContato("Jhonatan", "jhonatan@gmail.com", "99346554", "Novo Cliente");
		Contato lucas = createContato("Lucas", "lucas@gmail.com", "91940455", "Novo Cliente");
		Contato douglas = createContato("Douglas", "douglas@gmail.com", "99220468", "Novo Cliente");

		contatoDao.salvar(jhonatan);
		contatoDao.salvar(lucas);
		contatoDao.salvar(douglas);

		System.out.println("Contatos Salvos");
	}

	@Test
	public void listandoContatosTest() {
		List<Contato> contatos = contatoDao.listar();

		for (Contato contato : contatos) {
			System.out.println(contato.getNome() + " - " + contato.getEmail());
		}
	}

	private Contato createContato(String nome, String email, String telefone, String observacao) {
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setTelefone(telefone);
		contato.setObservacao(observacao);
		contato.setDataCadastro(new java.util.Date(System.currentTimeMillis()));

		return contato;
	}

}
