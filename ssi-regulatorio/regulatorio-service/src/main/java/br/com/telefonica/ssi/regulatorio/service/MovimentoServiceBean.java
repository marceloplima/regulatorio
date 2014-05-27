package br.com.telefonica.ssi.regulatorio.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@SuppressWarnings("all")
@Stateless
public class MovimentoServiceBean extends AbstractCrudServiceBean<Movimento, Integer> implements MovimentoService{

	/**
	 *
	 */
	private static final long serialVersionUID = 8667315712984846562L;

	@Override
	public List<Movimento> todosPorDemanda(DemandasRegulatorio demanda) {
		Query q = getEntityManager().createQuery("select m from Movimento m where m.demanda = :demanda order by m.dataHora desc");
		q.setParameter("demanda", demanda);
		List<Movimento> result = q.getResultList();
		return result;
	}

}
