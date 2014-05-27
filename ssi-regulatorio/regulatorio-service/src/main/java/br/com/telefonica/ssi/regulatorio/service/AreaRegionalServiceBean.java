package br.com.telefonica.ssi.regulatorio.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.telefonica.ssi.regulatorio.commom.domain.AreasRegionais;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.AreaRegionalService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class AreaRegionalServiceBean extends AbstractCrudServiceBean<AreasRegionais, Long> implements AreaRegionalService{

	/**
	 *
	 */
	private static final long serialVersionUID = -8486138611810716233L;

	@Override
	public List<AreasRegionais> recuperarTodos() {
		List<AreasRegionais> result = new ArrayList<AreasRegionais>();
		result = findAll();
		return result;
	}
}
