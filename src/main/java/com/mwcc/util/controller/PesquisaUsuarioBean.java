package com.mwcc.util.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mwcc.util.dao.UsuarioDAO;
import com.mwcc.util.jsf.FacesUtil;
import com.mwcc.util.model.Usuario;
import com.mwcc.util.service.NegocioException;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	private Usuario usuarioSelecionado;

	private List<Usuario> usuarios;

	public void limpar() {
		usuarios = usuarioDAO.buscarTodos();
		usuarioSelecionado = new Usuario();
	}

	@PostConstruct
	public void init() {
		limpar();
	}

	public void excluir() {
		try {
			usuarioDAO.remover(usuarioSelecionado);
			this.usuarios.remove(usuarioSelecionado);
			FacesUtil.addSuccessMessage("Usuário " + usuarioSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}


	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
