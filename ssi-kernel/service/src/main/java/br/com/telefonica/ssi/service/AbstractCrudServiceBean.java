package br.com.telefonica.ssi.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.log4j.Logger;

import br.com.telefonica.ssi.core.application.exception.ApplicationException;
import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

/**
 *
 * Classe abstrata que define o comportamento padrão de SessionBeans que
 * implementam/necessitam de operações CRUD.
 *
 * @author <a href='mailto:marcelo.batista@druid.com.br'>Marcelo Batista</a>
 * @date 17/04/2013 10:27:37
 * @version $Id: AbstractCrudServiceBean.java 14624 2013-12-16 10:25:45Z
 *          marcelo.batista $
 */

@SuppressWarnings("serial")
public abstract class AbstractCrudServiceBean<E extends AbstractEntity<Id>, Id>
		extends AbstractServiceBean<E> implements CrudService<E, Id> {

	private static final String ANY_EXPRESSION = "%{0}%";
	private static final Logger LOGGER = Logger
			.getLogger(AbstractCrudServiceBean.class);

	/**
	 * Cria uma nova instância de AbstractServiceBean.
	 */
	public AbstractCrudServiceBean() throws ApplicationException {
		super();
	}

	/**
	 * Cria uma Instancia passando manualmente o entity class caso passe pelo
	 * problema: java.lang.Class cannot be cast to
	 * java.lang.reflect.ParameterizedType no construtor default.
	 */
	public AbstractCrudServiceBean(Class<E> entityClass) {
		super(entityClass);
	}

	public List<E> findByFilter(Map<String, String> filters, int first,
			int pageSize) throws ApplicationException {
		LOGGER.debug("Iniciando findByFilter(filters)");
		try {

			List<E> resultList = new ArrayList<E>();

			final CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();
			final CriteriaQuery<E> criteriaQuery = criteriaBuilder
					.createQuery(getEntityClass());

			final Root<E> raiz = criteriaQuery.from(getEntityClass());

			// Se não passar filtro faz um findAll
			if (filters != null && !filters.isEmpty()) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();

				for (Map.Entry<String, String> entry : filters.entrySet()) {
					String attributeName = entry.getKey();
					String attributeValue = entry.getValue();
					Predicate equalsPredicate = null;

					if (raiz.get(attributeName) != null
							&& attributeValue != null
							&& !attributeValue.trim().equals("")) {
						equalsPredicate = criteriaBuilder
								.like(criteriaBuilder.upper(raiz
										.<String> get(attributeName)),
										buildAnyExpression(attributeValue
												.toUpperCase()));
						predicates.add(equalsPredicate);
					}
				}

				if (!predicates.isEmpty()) {
					criteriaQuery.where(criteriaBuilder.and(predicates
							.toArray(new Predicate[predicates.size()])));
				}

			}

			criteriaQuery.orderBy(criteriaBuilder.asc(raiz.get(this
					.getIdProperty(getEntityClass()))));

			TypedQuery<E> typedQuery = getEntityManager().createQuery(
					criteriaQuery).setFirstResult(first);

			if (pageSize > 0) {
				typedQuery = typedQuery.setMaxResults(pageSize);
			}

			resultList = typedQuery.getResultList();

			return resultList;

		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando findByFilter(filters)");
		}
	}

	public Long countByFilter(Map<String, String> filters)
			throws ApplicationException {
		LOGGER.debug("Iniciando countByFilter(filters)");

		try {

			final CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();
			final CriteriaQuery<Long> criteriaQuery = criteriaBuilder
					.createQuery(Long.class);

			final Root<E> raiz = criteriaQuery.from(getEntityClass());

			criteriaQuery.select(criteriaBuilder.count(raiz));

			// Se não passar filtro faz um findAll
			if (filters != null && !filters.isEmpty()) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();

				for (Map.Entry<String, String> entry : filters.entrySet()) {
					String attributeName = entry.getKey();
					String attributeValue = entry.getValue();
					Predicate equalsPredicate = null;

					if (raiz.get(attributeName) != null
							&& attributeValue != null
							&& !attributeValue.trim().equals("")) {
						equalsPredicate = criteriaBuilder
								.like(criteriaBuilder.upper(raiz
										.<String> get(attributeName)),
										buildAnyExpression(attributeValue
												.toUpperCase()));
						predicates.add(equalsPredicate);
					}
				}

				if (!predicates.isEmpty()) {
					criteriaQuery.where(criteriaBuilder.and(predicates
							.toArray(new Predicate[predicates.size()])));
				}
			}
			return getEntityManager().createQuery(criteriaQuery)
					.getSingleResult();

		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando countByFilter(filters)");
		}
	}

	public String getIdProperty(Class<E> entityClass) {
		String idProperty = null;
		Metamodel metamodel = getEntityManager().getMetamodel();
		EntityType<E> entity = metamodel.entity(entityClass);
		Set<SingularAttribute<? super E, ?>> singularAttributes = entity
				.getSingularAttributes();
		for (SingularAttribute<? super E, ?> singularAttribute : singularAttributes) {
			if (singularAttribute.isId()) {
				idProperty = singularAttribute.getName();
				break;
			}
		}
		if (idProperty == null)
			throw new RuntimeException("Campo id da entidade "
					+ entityClass.toString() + " não encontrado");
		return idProperty;
	}

	/**
	 * Cria uma expressão do tipo '%?%'.
	 *
	 * @param parameter
	 *            o valor do parâmetro que deve ser utilizado para montar a
	 *            expressão.
	 * @return A expressão criada.
	 */
	protected String buildAnyExpression(final Object parameter)
			throws ApplicationException {
		LOGGER.debug("Iniciando buildAnyExpression(parameter)");
		try {
			if (parameter == null) {
				throw new ApplicationException(
						"O parâmetro utilizado na expressão não pode ser nulo");
			}
			return MessageFormat.format(ANY_EXPRESSION, parameter);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando buildAnyExpression(parameter)");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> findAll() throws ApplicationException {
		LOGGER.debug("Iniciando findAll()");
		try {
			final CriteriaQuery<E> criteriaQuery = getEntityManager()
					.getCriteriaBuilder().createQuery(getEntityClass());
			final Root<E> entity = criteriaQuery.from(getEntityClass());
			criteriaQuery.select(entity);
			return getEntityManager().createQuery(criteriaQuery)
					.getResultList();
		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando findAll()");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> findAll(String orderByAttribute) throws ApplicationException {
		LOGGER.debug("Iniciando findAll(orderByAttribute)");
		try {
			if (orderByAttribute == null || orderByAttribute.isEmpty()) {
				throw new ApplicationException(
						"O atributo de ordena��o � nulo.");
			}

			final CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();
			final CriteriaQuery<E> criteriaQuery = criteriaBuilder
					.createQuery(getEntityClass());
			final Root<E> root = criteriaQuery.from(getEntityClass());
			criteriaQuery.orderBy(criteriaBuilder.asc(root
					.<String> get(orderByAttribute)));
			criteriaQuery.select(root);
			return getEntityManager().createQuery(criteriaQuery)
					.getResultList();
		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando findAll(orderByAttribute)");
		}
	}

	public Long countAll() throws ApplicationException {

		LOGGER.debug("Iniciando countAll()");

		try {

			CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder
					.createQuery(Long.class);
			criteriaQuery.select(criteriaBuilder.count(criteriaQuery
					.from(getEntityClass())));
			// criteriaQuery.where(/*caso tenha alguma restrição*/);
			return getEntityManager().createQuery(criteriaQuery)
					.getSingleResult();

		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando countAll()");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E findByAttributeEquals(final String attributeName,
			final String attributeValue) throws ApplicationException {
		LOGGER.debug("Iniciando findByAttributeEquals(attributeName, attributeValue)");
		try {
			if (attributeName == null || attributeName.isEmpty()) {
				throw new ApplicationException(
						"O nome do atributo não pode ser nulo/vazio");
			}
			if (attributeValue == null) {
				throw new ApplicationException(
						"O valor do atributo não pode ser nulo");
			}

			final CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();
			final CriteriaQuery<E> criteriaQuery = criteriaBuilder
					.createQuery(getEntityClass());
			final Root<E> raiz = criteriaQuery.from(getEntityClass());

			Predicate equalsPredicate = null;
			if (raiz.get(attributeName) != null) {
				equalsPredicate = criteriaBuilder.equal(
						raiz.<String> get(attributeName), attributeValue);
			}

			criteriaQuery.where(equalsPredicate);
			criteriaQuery.orderBy(criteriaBuilder.asc(raiz
					.<String> get(attributeName)));

			E result = null;
			List<E> resultList = getEntityManager().createQuery(criteriaQuery)
					.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				result = resultList.get(0);
			}
			return result;
		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando findByAttributeEquals(attributeName, attributeValue)");
		}
	}

	public List<E> findByAttributeLike(final String attributeName,
			final String attributeValue) throws ApplicationException {
		LOGGER.debug("Iniciando findByAttributeLike(attributeName, attributeValue)");
		try {
			if (attributeName == null || attributeName.isEmpty()) {
				throw new ApplicationException(
						"O nome do atributo não pode ser nulo/vazio");
			}
			if (attributeValue == null) {
				throw new ApplicationException(
						"O valor do atributo não pode ser nulo");
			}

			final CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();
			final CriteriaQuery<E> criteriaQuery = criteriaBuilder
					.createQuery(getEntityClass());
			final Root<E> raiz = criteriaQuery.from(getEntityClass());

			Expression<String> upperExpression = null;
			Predicate likePredicate = null;
			if (raiz.get(attributeName) != null) {
				upperExpression = criteriaBuilder.upper(raiz
						.<String> get(attributeName));
				likePredicate = criteriaBuilder.like(upperExpression,
						buildAnyExpression(attributeValue.toUpperCase()));
			}

			criteriaQuery.where(likePredicate);
			criteriaQuery.orderBy(criteriaBuilder.asc(raiz
					.<String> get(attributeName)));

			return getEntityManager().createQuery(criteriaQuery)
					.getResultList();
		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando findByAttributeLike(attributeName, attributeValue)");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E findById(Id id) throws ApplicationException {
		LOGGER.debug("Iniciando findById(id)");
		try {
			if (id == null) {
				throw new ApplicationException("O id não pode se nulo");
			}
			E entity = null;
			if (id != null) {
				entity = getEntityManager().find(getEntityClass(), id);
			}
			return entity;
		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando findById(id)");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(E entity) throws ApplicationException {
		LOGGER.debug("Iniciando remove(entity)");
		try {
			if (entity == null) {
				throw new ApplicationException("A entidade não pode ser nula");
			}
			final E attachedEntity = getEntityManager().merge(entity);

			getEntityManager().remove(attachedEntity);

		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando remove(entity)");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(E entity) throws ApplicationException {
		LOGGER.debug("Iniciando save(entity)");
		try {
			if (entity.getId() != null) {
				E merged = getEntityManager().merge(entity);
				getEntityManager().persist(merged);
			} else {

				getEntityManager().persist(entity);
			}
		} catch (PersistenceException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
			LOGGER.debug("Encerrando save(entity)");
		}
	}

}