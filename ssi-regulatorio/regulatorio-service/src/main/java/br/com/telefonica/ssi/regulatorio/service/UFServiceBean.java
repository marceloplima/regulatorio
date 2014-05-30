package br.com.telefonica.ssi.regulatorio.service;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.UF;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.UFService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class UFServiceBean extends AbstractCrudServiceBean<UF, Integer> implements UFService{

	/**
	 *
	 */
	private static final long serialVersionUID = -3589864848381752218L;

	@Override
	public UF findByName(String name) {
		TypedQuery<UF> q = getEntityManager().createQuery("select u from UF u where upper(u.descricao) = :desc",UF.class);
		q.setParameter("desc", name.toUpperCase());
		UF uf = q.getSingleResult();
		return uf;
	}
}
