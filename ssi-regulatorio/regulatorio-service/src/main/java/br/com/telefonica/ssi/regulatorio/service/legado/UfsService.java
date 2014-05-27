package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Ufs;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.UfsInt;

@Stateless
public class UfsService implements UfsInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	public UfsService() {}
	
	@Override
	public List<Ufs> recuperar() {
		final String jpaquery = "SELECT uf FROM Ufs uf ORDER BY uf.cnmuf";
		TypedQuery<Ufs> q = em.createQuery(jpaquery, Ufs.class);
		return q.getResultList();	
	}

	@Override
	public Ufs recuperaUnico(Ufs uf) {
		return em.find(Ufs.class, uf.getId());
	}

}
