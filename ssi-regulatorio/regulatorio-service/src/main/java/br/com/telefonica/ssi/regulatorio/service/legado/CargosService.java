package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Cargos;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.CargosInt;


@Stateless
public class CargosService implements CargosInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	public CargosService(){}
	
	@Override
	public List<Cargos> recuperar() {
		final String jpaquery = "SELECT obj FROM Cargos obj";
		TypedQuery<Cargos> q = em.createQuery(jpaquery, Cargos.class);
		return q.getResultList();
	}
	
	@Override
	public Cargos recuperarUnico(Cargos obj) {
		return em.find(Cargos.class, obj);
	}
	
}
