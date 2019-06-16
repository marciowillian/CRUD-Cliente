package com.mwcc.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mwcc.util.model.Usuario;

public class CadastroUsuario {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clientePU");
		EntityManager em = emf.createEntityManager();
		
		Usuario usuario = new Usuario();
		
		usuario.setNome("Marcio Willian");
		usuario.setEmail("willian_mw.cc@hotmail.com");
		usuario.setSenha("123456");
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.clear();
		
	}

}
