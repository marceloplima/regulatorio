package br.com.telefonica.ssi.regulatorio.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.core.application.exception.ApplicationException;
import br.com.telefonica.ssi.regulatorio.commom.domain.CategoriaRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.CategoriaService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class CategoriaServiceBean extends AbstractCrudServiceBean<CategoriaRegulatorio, Integer> implements CategoriaService{

	/**
	 *
	 */
	private static final long serialVersionUID = -4382557983273618650L;

	@Override
	public CategoriaRegulatorio findByName(String name) {
		Query q = getEntityManager().createQuery("select c from CategoriaRegulatorio c where UPPER(c.descricao) = :desc");
		q.setParameter("desc", name.toUpperCase());
		@SuppressWarnings("unchecked")
		List<CategoriaRegulatorio> results = q.getResultList();
		if(results!=null && results.size()>0){
			return (CategoriaRegulatorio)results.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public List<CategoriaRegulatorio> findAll() throws ApplicationException {
		TypedQuery<CategoriaRegulatorio> q = getEntityManager().createQuery("select c from CategoriaRegulatorio c order by c.descricao asc",CategoriaRegulatorio.class);
		return q.getResultList();
	}
}
