package br.com.telefonica.ssi.regulatorio.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Log;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.LogService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@SuppressWarnings("all")
@Stateless
public class LogServiceBean extends AbstractCrudServiceBean<Log, Integer> implements LogService{

	/**
	 *
	 */
	private static final long serialVersionUID = 8393000834931949420L;

	@Override
	public List<Log> logsPorDemanda(DemandasRegulatorio demanda) {
		Query q = getEntityManager().createQuery("Select l from Log l where l.demanda = :demanda order by l.dataCadastro desc");
		q.setParameter("demanda", demanda);
		List<Log> result = q.getResultList();
		return result;
	}

	@Override
	public void salvarLog(DemandasRegulatorio demanda, Pessoas pessoa, String info) {
		Log log = new Log();
		log.setDataCadastro(new Date());
		log.setDemanda(demanda);
		log.setInfo(info);
		log.setPessoa(pessoa);

		save(log);
	}

}
