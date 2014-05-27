package br.com.telefonica.ssi.regulatorio.service;

import javax.ejb.Stateless;

import br.com.telefonica.ssi.regulatorio.commom.domain.ResponsavelTecnico;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ResponsavelTecnicoService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class ResponsavelTecnicoServiceBean extends AbstractCrudServiceBean<ResponsavelTecnico, Integer> implements ResponsavelTecnicoService{

	/**
	 *
	 */
	private static final long serialVersionUID = 4508115375330180163L;

}
