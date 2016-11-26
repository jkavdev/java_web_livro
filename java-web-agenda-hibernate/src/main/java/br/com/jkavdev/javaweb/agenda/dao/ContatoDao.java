package br.com.jkavdev.javaweb.agenda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jkavdev.javaweb.agenda.modelo.Contato;
import br.com.jkavdev.javaweb.agenda.util.HibernateUtility;

public class ContatoDao {

	Session sessao;

	public ContatoDao() {
		sessao = HibernateUtility.getSessionFactory().openSession();
	}

	public void salvar(Contato contato) {
		this.sessao.save(contato);
	}

	public void atualizar(Contato contato) {
		this.sessao.update(contato);
	}

	public void excluir(Contato contato) {
		this.sessao.delete(contato);
		this.sessao.flush();
	}

	@SuppressWarnings({ "unchecked" })
	public List<Contato> listar() {
		return this.sessao.createQuery("from Contato").list();
	}

	@SuppressWarnings({})
	public Contato buscaContato(int codigo) {
		Query consulta = this.sessao.createQuery("from Contato where codigo = :codigo")
				.setInteger("codigo", codigo);

		return (Contato) consulta.uniqueResult();
	}

	public Session getSession() {
		return sessao;
	}

}
