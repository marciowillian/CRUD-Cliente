package com.mwcc.util.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mwcc.util.jsf.FacesUtil;
import com.mwcc.util.model.Usuario;
import com.mwcc.util.service.CadastroUsuarioService;
import com.mwcc.util.service.NegocioException;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	private Usuario usuario;

	@PostConstruct
	public void init() {
		limpar();
	}

	public void limpar() {
		usuario = new Usuario();
	}

	public void salvar() {
		try {
			cadastroUsuarioService.salvar(usuario);
			FacesUtil.addSuccessMessage("Usuário salvo com sucesso!");
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
