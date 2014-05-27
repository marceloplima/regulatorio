package br.com.telefonica.ssi.regulatorio.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.telefonica.ssi.regulatorio.commom.domain.Complexidade;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ComplexidadeService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class ComplexidadeServiceBean extends AbstractCrudServiceBean<Complexidade, Integer> implements ComplexidadeService{

	/**
	 *
	 */
	private static final long serialVersionUID = -56011175240548284L;

	@Override
	public List<Complexidade> buscarTodos() {
		return findAll();
	}

	@Override
	public Complexidade findByName(String name) {
		Query q = getEntityManager().createQuery("select c from Complexidade c where upper(c.cnmDescComplexidade) = :name");
		q.setParameter("name", name.toUpperCase());
		Complexidade c = (Complexidade)q.getSingleResult();
		return c;
	}


}
