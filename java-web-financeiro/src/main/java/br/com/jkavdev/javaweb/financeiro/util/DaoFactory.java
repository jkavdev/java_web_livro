package br.com.jkavdev.javaweb.financeiro.util;

import br.com.jkavdev.javaweb.financeiro.usuario.UsuarioDao;
import br.com.jkavdev.javaweb.financeiro.usuario.UsuarioDaoHibernate;

public class DaoFactory {

	public static UsuarioDao criaUsuarioDao() {
		UsuarioDaoHibernate usuarioDaoHibernate = new UsuarioDaoHibernate();
		usuarioDaoHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());

		return usuarioDaoHibernate;
	}

}
