package br.com.telefonica.ssi.regulatorio.service;

import java.util.List;

import javax.ejb.Stateless;

import br.com.telefonica.ssi.regulatorio.commom.domain.TipoRede;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.TipoRedeService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class TipoRedeServiceBean extends AbstractCrudServiceBean<TipoRede, Integer> implements TipoRedeService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7939637820866588203L;

	@Override
	public List<TipoRede> recuperaTodos() {
		return findAll();
	}
	
	
}
