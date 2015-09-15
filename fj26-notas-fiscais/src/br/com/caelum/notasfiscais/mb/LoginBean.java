package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class LoginBean implements Serializable {
	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDao dao;

	public String efetuaLogin() {
		boolean loginValido = dao.existe(this.usuario);
		if (loginValido){
			return "produto?faces-redirect=true";
		} else {
			this.usuario = new Usuario();
			return "login";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
