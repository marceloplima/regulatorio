package br.com.telefonica.ssi.regulatorio.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.core.application.exception.ApplicationException;
import br.com.telefonica.ssi.regulatorio.commom.domain.CategoriaRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Procedencia;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ProcedenciaService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class ProcedenciaServiceBean extends AbstractCrudServiceBean<Procedencia, Integer> implements ProcedenciaService{

	/**
	 *
	 */
	private static final long serialVersionUID = -8547852468071064173L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Procedencia> findByCategoria(CategoriaRegulatorio categoria) {
		Query q = getEntityManager().createQuery("select p from Procedencia p where p.categoria.descricao = :name");
		q.setParameter("name", categoria.getDescricao());
		List<Procedencia> resultado = q.getResultList();
		return resultado;
	}

	@Override
	public List<Procedencia> findAll() throws ApplicationException {
		TypedQuery<Procedencia> q = getEntityManager().createQuery("select p from Procedencia p where p.ativo = 1 order by p.descricao asc",Procedencia.class);
		return q.getResultList();
	}


}
