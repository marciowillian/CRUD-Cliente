package com.mwcc.util.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.mwcc.util.jpa.Transactional;
import com.mwcc.util.model.Usuario;
import com.mwcc.util.service.NegocioException;

public class UsuarioDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	public Usuario porId(Long id) {
		return em.find(Usuario.class, id);
	}
	
	public List<Usuario> buscarTodos(){
		return em.createQuery("From Usuario", Usuario.class).getResultList();
	}
	
	public void salvar(Usuario usuario) {
		em.merge(usuario);
	}
	
	@Transactional
	public void remover(Usuario usuario) throws NegocioException {
		usuario = porId(usuario.getId());
		em.remove(usuario);
		em.flush();
	}
}
