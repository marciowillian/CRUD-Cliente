package com.mwcc.util.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mwcc.util.dao.UsuarioDAO;
import com.mwcc.util.filter.UsuarioFilter;
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

	private Usuario usuario;
	private Usuario usuarioSelecionado;

	private UsuarioFilter usuarioFilter;

	private List<Usuario> usuarios;

	public void limpar() {
		usuario = new Usuario();
		usuarioFilter = new UsuarioFilter();
		usuarios = new ArrayList<>();
		usuarioSelecionado = new Usuario();
		usuarios = usuarioDAO.buscarTodos();
	}

	public void init() {
		if (usuario == null) {
			limpar();
		}

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

	public void pesquisar() {
		System.out.println("Entrei no pesquisar...");
		usuarios = usuarioDAO.filtrados(usuarioFilter);
		
		for(Usuario usu : usuarios) {
		System.out.println(usu.getNome());	
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

	public UsuarioFilter getUsuarioFilter() {
		return usuarioFilter;
	}

	public void setUsuarioFilter(UsuarioFilter usuarioFilter) {
		this.usuarioFilter = usuarioFilter;
	}

}
