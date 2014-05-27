package br.com.telefonica.ssi.regulatorio.service;

import javax.ejb.Stateless;

import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoTecnico;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoTecnicoService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class MovimentoTecnicoServiceBean extends AbstractCrudServiceBean<MovimentoTecnico , Integer> implements MovimentoTecnicoService{

	/**
	 *
	 */
	private static final long serialVersionUID = -7602163217456790946L;

}
