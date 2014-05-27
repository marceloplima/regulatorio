package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.TipoDemanda;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface TipoDemandaService extends CrudService<TipoDemanda, Integer>{
	List<TipoDemanda> recuperaTodos();
}
