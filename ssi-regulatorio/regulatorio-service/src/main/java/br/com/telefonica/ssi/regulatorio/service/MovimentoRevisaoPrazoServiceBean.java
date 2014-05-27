package br.com.telefonica.ssi.regulatorio.service;

import javax.ejb.Stateless;

import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoRevisaoPrazo;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoRevisaoPrazoService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class MovimentoRevisaoPrazoServiceBean extends AbstractCrudServiceBean<MovimentoRevisaoPrazo, Integer> implements MovimentoRevisaoPrazoService{

	/**
	 *
	 */
	private static final long serialVersionUID = -5578182235302006203L;

}
