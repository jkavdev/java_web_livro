package br.com.jkavdev.javaweb.financeiro.web;

import java.util.List;

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
	private List<Usuario> lista;
	private String destinoSalvar;

	public String novoUsuario() {
		this.destinoSalvar = "usuario-sucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);

		return "/publico/usuario";
	}

	public String editarUsuario() {
		this.confirmarSenha = this.getUsuario().getSenha();

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
		
		System.out.println(this.destinoSalvar);

		return this.destinoSalvar;
	}

	public String excluirUsuario() {
		UsuarioService usuarioService = new UsuarioService();
		usuarioService.excluir(this.usuario);
		
		this.recarregarListaUsuario();

		return null;
	}

	private void recarregarListaUsuario() {
		lista = null;
	}

	public String ativar() {
		if (this.usuario.isAtivo()) {
			this.usuario.setAtivo(false);
		} else {
			this.usuario.setAtivo(true);
		}

		UsuarioService usuarioService = new UsuarioService();
		usuarioService.salvar(this.usuario);

		return null;
	}

	public List<Usuario> getLista() {
		if (this.lista == null) {
			UsuarioService usuarioService = new UsuarioService();
			this.lista = usuarioService.listar();
		}
		
		return lista;
	}
	
	public String getDestinoSalvar() {
		return destinoSalvar;
	}
	
	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
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
