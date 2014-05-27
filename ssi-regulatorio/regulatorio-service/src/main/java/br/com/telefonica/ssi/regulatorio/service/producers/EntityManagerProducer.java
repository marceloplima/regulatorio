package br.com.telefonica.ssi.regulatorio.service.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ssifwpc");
	
	@Produces
	@RequestScoped
	public EntityManager createEntityManager(){
		return emf.createEntityManager();
	}
}
