package br.com.telefonica.ssi.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.telefonica.ssi.core.application.exception.ApplicationException;
import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;


/**
 * 
 * Classe que define o comportamento padrão de qualquer SessionBean.
 *
 * @author <a href='mailto:marcelo.batista@druid.com.br'>Marcelo Batista</a>
 * @date 17/04/2013 10:26:52
 * @version $Id: AbstractServiceBean.java 13421 2013-11-01 16:08:34Z marcelo.batista $
 */

@SuppressWarnings("serial")
public abstract class AbstractServiceBean<E extends AbstractEntity<?>> implements Service<E>, Serializable {

	private static final Logger LOGGER = Logger.getLogger(AbstractServiceBean.class);

	private Class<E> entityClass;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Cria uma instancia de AbstractServiceBean.
	 */
	@SuppressWarnings("unchecked")
	public AbstractServiceBean() {
		this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	  * Cria uma Instancia passando manualmente o entity class caso passe pelo problema: 
	 * java.lang.Class cannot be cast to java.lang.reflect.ParameterizedType no construtor default. 
	 */
	public AbstractServiceBean(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	/**
	 * Executa uma NamedQuery JPQL.
	 * 
	 * @param queryName
	 *            O nome da query
	 * @param params
	 *            Os parametros da query.
	 * @return Uma lista com o resultado da execução da query.
	 * @throws ApplicationException
	 *             Qualquer erro/exceção que ocorra durante a execução do
	 *             método.
	 */
	@SuppressWarnings("unchecked")
	public List<E> executeNamedQuery(final String queryName, final Object... params) throws ApplicationException {
		LOGGER.debug("Iniciando executeNamedQuery(queryName, params)");
		try {
			if (queryName == null || queryName.isEmpty()) {
				throw new ApplicationException("O nome da query não pode ser nulo/vazio.");
			}
			Query query = getEntityManager().createNamedQuery(queryName);
			if (params != null && params.length > 0) {
				int i = 1;
				for (Object param : params) {
					query.setParameter(i++, param);
				}
			}
			List<E> result = query.getResultList();
			return result;
		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando executeNamedQuery(queryName, params)");
		}
	}

	/**
	 * Executa uma NamedQueryCount JPQL.
	 * 
	 * @param queryName
	 *            O nome da query
	 * @param params
	 *            Os parametros da query.
	 * @return Uma lista com o resultado da execução da query.
	 * @throws ApplicationException
	 *             Qualquer erro/exceção que ocorra durante a execução do
	 *             método.
	 */
	public int executeNamedQueryCount(final String queryName, final Object... params) throws ApplicationException {
		LOGGER.debug("Iniciando executeNamedQueryCount(queryName, params)");
		try {
			if (queryName == null || queryName.isEmpty()) {
				throw new ApplicationException("O nome da query não pode ser nulo/vazio.");
			}
			Query query = getEntityManager().createNamedQuery(queryName);
			if (params != null && params.length > 0) {
				int i = 1;
				for (Object param : params) {
					query.setParameter(i++, param);
				}
			}
			int result = ((Number)query.getSingleResult()).intValue();
			return result;
		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando executeNamedQueryCount(queryName, params)");
		}
	}
	
	/**
	 * Retorna o valor de entityClass.
	 * 
	 * @return o valor de entityClass
	 */
	protected Class<E> getEntityClass() throws ApplicationException {
		return entityClass;
	}

	/**
	 * Retorna o valor de entityManager.
	 * 
	 * @return o valor de entityManager
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Altera o valor de entityClass.
	 * 
	 * @param entityClass
	 *            o novo valor de entityClass
	 */
	protected void setEntityClass(Class<E> entityClass) throws ApplicationException {
		this.entityClass = entityClass;
	}

	/**
	 * Altera o valor de entityManager.
	 * 
	 * @param entityManager
	 *            o novo valor de entityManager
	 */
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
