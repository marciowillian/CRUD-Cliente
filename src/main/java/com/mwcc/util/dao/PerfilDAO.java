package com.mwcc.util.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.mwcc.util.model.Perfil;

public class PerfilDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	public Perfil porId(Long id) {
		return em.find(Perfil.class, id);
	}
	
	public List<Perfil> buscarTodos(){
		return em.createQuery("From Perfil", Perfil.class).getResultList();
	}
	
}
