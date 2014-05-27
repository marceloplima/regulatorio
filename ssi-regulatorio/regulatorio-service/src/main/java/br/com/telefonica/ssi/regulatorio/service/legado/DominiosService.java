package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Dominios;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.DominiosInt;


@Stateless
public class DominiosService implements DominiosInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	public DominiosService(){}
	
	@Override
	public List<Dominios> recuperar() {
		final String jpaquery = "SELECT dominio FROM Dominios dominio";
		TypedQuery<Dominios> q = em.createQuery(jpaquery, Dominios.class);
		return q.getResultList();
	}
	
	@Override
	public Dominios recuperarUnico(Long iddominio) {
		final String jpaquery = "SELECT dominio FROM Dominios dominio WHERE iddominio = "+iddominio;
		
		return em.createQuery(jpaquery, Dominios.class).getResultList().get(0);
	}

	@Override
	public boolean remover() {
		// TODO Auto-generated method stub
		return false;
	}	
	
}
