package br.com.telefonica.ssi.regulatorio.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.telefonica.ssi.regulatorio.commom.domain.Execucao;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ExecucaoService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class ExecucaoServiceBean extends AbstractCrudServiceBean<Execucao, Integer> implements ExecucaoService{

	/**
	 *
	 */
	private static final long serialVersionUID = -3479676126343984003L;

	@Override
	public List<Execucao> buscarTodos() {
		return findAll();
	}

	@Override
	public Execucao findByName(String name) {
		Query q = getEntityManager().createQuery("select e from Execucao e where upper(e.descricao) = :descricao");
		q.setParameter("descricao", name.toUpperCase());
		Execucao ex = (Execucao)q.getSingleResult();
		return ex;
	}


}
