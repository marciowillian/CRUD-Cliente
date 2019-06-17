package com.mwcc.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mwcc.util.model.Perfil;

public class CadastroPerfis {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clientePU");
		EntityManager em = emf.createEntityManager();
		
		Perfil p = new Perfil();
		
		p.setDescricao("Gerente");
		
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();

	}

}
