package com.mwcc.util.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import com.mwcc.util.dao.PerfilDAO;
import com.mwcc.util.dao.UsuarioDAO;
import com.mwcc.util.jsf.FacesUtil;
import com.mwcc.util.model.Perfil;
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
	@Inject
	private PerfilDAO perfilDAO;

	private Usuario usuario;

	private DualListModel<Perfil> perfis;
	private List<Perfil> perfisUsuario;

	public void inicializar() {
		
		if(usuario == null) {
			limpar();
		}else {
			perfisUsuario = perfilDAO.buscarTodos();
			perfisUsuario.removeAll(usuario.getPerfil());
			
			perfis = new DualListModel<>(perfisUsuario, new ArrayList<>(usuario.getPerfil()));
		}
		
		
	}

	public void limpar() {
		usuario = new Usuario();
		
		List<Perfil> vazia = new ArrayList<>();
		perfis = new DualListModel<>(perfilDAO.buscarTodos(), vazia);
		//perfis = usuarioDAO.buscarTodosPerfis();
	}

	public void salvar() {
		try {
			usuario.setPerfil(perfis.getTarget());
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

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

	public DualListModel<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(DualListModel<Perfil> perfis) {
		this.perfis = perfis;
	}

	

}
