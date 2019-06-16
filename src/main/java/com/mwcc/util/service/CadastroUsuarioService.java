package com.mwcc.util.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mwcc.util.dao.UsuarioDAO;
import com.mwcc.util.jpa.Transactional;
import com.mwcc.util.model.Usuario;

public class CadastroUsuarioService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Transactional
	public void salvar(Usuario usuario) throws NegocioException {

		if (usuario.getNome() == null || usuario.getNome().trim().equals("")) {
			throw new NegocioException("O nome do usuario é obrigatório.");
		}

		this.usuarioDAO.salvar(usuario);

	}

}
