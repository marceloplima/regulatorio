package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface MovimentoService extends CrudService<Movimento, Integer>{

	List<Movimento> todosPorDemanda(DemandasRegulatorio demanda);
	List<Pessoas> pessoaAreaOperacionalDaDemanda(DemandasRegulatorio demanda);

}