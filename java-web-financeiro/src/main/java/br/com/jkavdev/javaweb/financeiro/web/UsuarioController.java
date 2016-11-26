package br.com.jkavdev.javaweb.financeiro.web;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.jkavdev.javaweb.financeiro.usuario.Usuario;
import br.com.jkavdev.javaweb.financeiro.usuario.UsuarioService;

@ManagedBean
@RequestScoped
public class UsuarioController {

	private Usuario usuario = new Usuario();
	private String confirmarSenha;

	public String novoUsuario() {
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);

		return "/publico/usuario";
	}

	public String salvarUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (!this.usuario.getSenha().equalsIgnoreCase(this.confirmarSenha)) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha confirmada incorretamente", ""));
			return null;
		}

		UsuarioService usuarioService = new UsuarioService();
		usuarioService.salvar(this.usuario);

		return "usuario-sucesso";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

}
