package br.com.jkavdev.javaweb.financeiro.usuario;

import java.util.List;

import br.com.jkavdev.javaweb.financeiro.util.DaoFactory;

public class UsuarioService {

	private UsuarioDao usuarioDao;

	public UsuarioService() {
		this.usuarioDao = DaoFactory.criaUsuarioDao();
	}

	public Usuario carregar(Integer codigo) {
		return this.usuarioDao.buscarUsuario(codigo);
	}

	public Usuario buscarPorLogin(String login) {
		return this.usuarioDao.buscarPorLogin(login);
	}

	public void salvar(Usuario usuario) {
		Integer codigo = usuario.getCodigo();
		if (codigo == null || codigo == 0) {
			this.usuarioDao.salvar(usuario);
		} else {
			this.usuarioDao.atualizar(usuario);
		}
	}

	public void excluir(Usuario usuario) {
		this.usuarioDao.excluir(usuario);
	}

	public List<Usuario> listar() {
		return this.usuarioDao.listar();
	}

}
