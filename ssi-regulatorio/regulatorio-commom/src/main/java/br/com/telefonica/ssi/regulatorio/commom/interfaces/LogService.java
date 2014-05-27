package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Log;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface LogService extends CrudService<Log, Integer>{

	List<Log> logsPorDemanda(DemandasRegulatorio demanda);
	void salvarLog(DemandasRegulatorio demanda, Pessoas pessoa, String info);

}
