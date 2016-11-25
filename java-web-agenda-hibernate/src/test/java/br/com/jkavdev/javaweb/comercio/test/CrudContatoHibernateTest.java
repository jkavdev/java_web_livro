package br.com.jkavdev.javaweb.comercio.test;

import java.sql.Date;

import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import br.com.jkavdev.javaweb.hibernate.comercio.dao.ContatoDao;
import br.com.jkavdev.javaweb.hibernate.comercio.modelo.Contato;

public class CrudContatoHibernateTest {

	ContatoDao contatoDao;
	Transaction transacao;
	
	@Before
	public void init(){
		contatoDao = new ContatoDao();
		transacao = contatoDao.getSession().getTransaction();
	}

	@Test
	public void savarContatoTest() {
		transacao.begin();
		
		Contato ian = criaContato("Ian Alves", "ian@gmail.com", "99346554", "Novo Cliente");
		Contato italo = criaContato("Italo Alves", "italo@gmail.com", "99446554", "Novo Cliente");
		Contato felipe = criaContato("Felipe Alves", "felipe@gmail.com", "99344554", "Novo Cliente");

		contatoDao.salvar(ian);
		contatoDao.salvar(italo);
		contatoDao.salvar(felipe);

		transacao.commit();
	}
	
	@Test
	public void alterarContatoTest(){
		transacao.begin();
		
		Contato ian = contatoDao.buscaContato(1);
		
		ian.setObservacao("Retomar Contato");
		contatoDao.atualizar(ian);

		System.out.println("Total de registros cadastrados: " + contatoDao.listar().size());
		
		transacao.commit();
	}
	
	@Test
	public void removerContatoTest(){
		transacao.begin();
		
		Contato felipe = contatoDao.buscaContato(2);

		System.out.println("Total de registros cadastrados: " + contatoDao.listar().size());
		contatoDao.excluir(felipe);
		System.out.println("Total de registros cadastrados: " + contatoDao.listar().size());
		
		transacao.commit();
	}

	private static Contato criaContato(String nome, String email,
			String telefone, String observacao) {

		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setTelefone(telefone);
		contato.setObservacao(observacao);
		contato.setDataCadastro(new Date(System.currentTimeMillis()));

		return contato;
	}

}
