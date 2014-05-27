package br.com.telefonica.ssi.service;

import java.io.Serializable;
import java.util.List;

import br.com.telefonica.ssi.core.application.exception.ApplicationException;
import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;


public interface CrudService<E extends AbstractEntity<Id>, Id> extends Serializable {
	
	List<E> findAll() throws ApplicationException;

	List<E> findAll(String orderByAttribute) throws ApplicationException;
	
	List<E> findByAttributeLike(String attribute, String value) throws ApplicationException;
	
	E findByAttributeEquals(final String attributeName, final String attributeValue) throws ApplicationException;

	E findById(Id id) throws ApplicationException;

	void remove(E entity) throws ApplicationException;

	void save(E entity) throws ApplicationException;
}