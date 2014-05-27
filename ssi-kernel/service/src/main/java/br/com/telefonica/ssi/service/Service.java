package br.com.telefonica.ssi.service;

import java.io.Serializable;
import java.util.List;

import br.com.telefonica.ssi.core.application.exception.ApplicationException;
import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;



/**
 * 
 * Interface que define o comportamento de Serviços.
 *
 * @author <a href='mailto:marcelo.batista@druid.com.br'>Marcelo Batista</a>
 * @date 05/09/2013 15:48:09
 * @version $Id: Service.java 13404 2013-11-01 14:10:30Z marcelo.batista $
 */
public interface Service<E extends AbstractEntity<?>> extends Serializable {
	
	public List<E> executeNamedQuery(final String queryName, final Object... params) throws ApplicationException;
	
	public int executeNamedQueryCount(final String queryName, final Object... params) throws ApplicationException;
	
}