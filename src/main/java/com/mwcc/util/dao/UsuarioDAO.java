package com.mwcc.util.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mwcc.util.filter.UsuarioFilter;
import com.mwcc.util.jpa.Transactional;
import com.mwcc.util.model.Perfil;
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
	
	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter usuario){
		
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);
		
		if(StringUtils.isNotBlank(usuario.getNome())) {
			criteria.add(Restrictions.ilike("nome", usuario.getNome(), MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(usuario.getEmail())) {
			criteria.add(Restrictions.ilike("email", usuario.getEmail(), MatchMode.ANYWHERE));
		}
		
		
		return criteria.addOrder(Order.asc("nome")).list();
		
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
