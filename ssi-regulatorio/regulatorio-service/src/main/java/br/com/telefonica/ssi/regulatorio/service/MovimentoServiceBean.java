package br.com.telefonica.ssi.regulatorio.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.GruposModulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
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

	@Override
	public List<Pessoas> pessoaAreaOperacionalDaDemanda(DemandasRegulatorio demanda) {

		final String jpaquery = "SELECT pessoas from Movimento movimento INNER JOIN movimento.demanda demanda"
				+ " INNER JOIN movimento.movimentosArea maa"
				+ " INNER JOIN maa.areasOperacionais areaoperacional"
				+ " INNER JOIN areaoperacional.areaspessoas pessoas WHERE movimento.demanda = :demanda";

		TypedQuery<Pessoas> q = getEntityManager().createQuery(jpaquery, Pessoas.class);

		q.setParameter("demanda", demanda);

		try{
			return q.getResultList();
		}
		catch(IndexOutOfBoundsException e){
			return new ArrayList<Pessoas>();
		}

	}

}