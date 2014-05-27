package br.com.telefonica.ssi.regulatorio.service;

import java.util.List;

import javax.ejb.Stateless;

import br.com.telefonica.ssi.regulatorio.commom.domain.ResultadoAnalise;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ResultadoAnaliseService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class ResultadoAnaliseServiceBean extends AbstractCrudServiceBean<ResultadoAnalise, Integer> implements ResultadoAnaliseService{

	/**
	 *
	 */
	private static final long serialVersionUID = 205257363683172428L;

	@Override
	public List<ResultadoAnalise> buscarTodos() {
		return findAll();
	}
}
