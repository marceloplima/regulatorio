package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Regionais;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.RegionaisInt;

@Stateless
public class RegionaisService implements RegionaisInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;

	public RegionaisService() {}

	@Override
	public List<Regionais> recuperar() {
		final String jpaquery = "SELECT r FROM Regionais r";
		TypedQuery<Regionais> q = em.createQuery(jpaquery, Regionais.class);
		return q.getResultList();
	}

	@Override
	public Regionais recuperarUnico(Regionais regional) {
		final String jpaquery = "SELECT r FROM Regionais r where r=:regional";
		TypedQuery<Regionais> q = em.createQuery(jpaquery, Regionais.class);
		q.setParameter("regional", regional);
		return q.getResultList().get(0);
	}
}
