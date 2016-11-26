package br.com.jkavdev.javaweb.financeiro.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jkavdev.javaweb.financeiro.util.HibernateUtil;

@WebFilter(urlPatterns = { "*.jsf" })
public class ConexaoHibernateFilter implements Filter {

	private SessionFactory factory;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.factory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Session currentSession = this.factory.getCurrentSession();
		Transaction transaction = currentSession.getTransaction();

		try {
			transaction.begin();

			chain.doFilter(request, response);

			transaction.commit();

			if (currentSession.isOpen()) {
				currentSession.close();
			}
		} catch (Throwable ex) {
			try {
				if (transaction.isActive()) {
					transaction.rollback();
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}

			throw new ServletException(ex);
		}
	}

	@Override
	public void destroy() {

	}

}
