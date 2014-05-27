package br.com.telefonica.ssi.regulatorio.service;

import java.util.List;

import javax.ejb.Stateless;

import br.com.telefonica.ssi.regulatorio.commom.domain.TipoDemanda;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.TipoDemandaService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class TipoDemandaServiceBean extends AbstractCrudServiceBean<TipoDemanda, Integer> implements TipoDemandaService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3184495673178452082L;

	@Override
	public List<TipoDemanda> recuperaTodos() {
		return findAll();
	}
	
	
}
