package br.com.telefonica.ssi.regulatorio.service;

import java.util.List;

import javax.ejb.Stateless;

import br.com.telefonica.ssi.regulatorio.commom.domain.DocumentoOrigem;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.DocumentoOrigemService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class DocumentoOrigemServiceBean extends AbstractCrudServiceBean<DocumentoOrigem, Integer> implements DocumentoOrigemService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3944368368812412889L;

	@Override
	public List<DocumentoOrigem> recuperaTodos() {
		return findAll();
	}
	
}
