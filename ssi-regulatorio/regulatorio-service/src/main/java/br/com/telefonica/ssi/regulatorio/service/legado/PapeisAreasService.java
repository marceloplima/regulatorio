package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.PapeisAreas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.PapeisAreasInt;

@Stateless
public class PapeisAreasService implements PapeisAreasInt {
	
	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	public PapeisAreasService(){}

	@Override
	public List<PapeisAreas> recuperar() {
		final String jpaquery = "SELECT papelarea FROM PapeisAreas papelarea order by papelarea.cnmpapelarea";
		TypedQuery<PapeisAreas> q = em.createQuery(jpaquery, PapeisAreas.class);
		return q.getResultList();
	}

	@Override
	public PapeisAreas recuperarUnico(PapeisAreas papelarea) {
		final String jpaquery = "SELECT papelarea FROM PapeisAreas papelarea WHERE papelarea = :papelarea";
		TypedQuery<PapeisAreas> q = em.createQuery(jpaquery, PapeisAreas.class);
		q.setParameter("papelarea", papelarea);
		return q.getResultList().get(0);
	}

}
