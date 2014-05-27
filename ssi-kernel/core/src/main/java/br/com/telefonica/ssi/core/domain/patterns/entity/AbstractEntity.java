package br.com.telefonica.ssi.core.domain.patterns.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * Esta classe define o comportamento padrão de toda e qualquer entidade
 * mapeada em um sistema através da API JPA.
 *
 * @author <a href='mailto:marcelo.batista@druid.com.br'>Marcelo Batista</a>
 * @date 17/04/2013 09:56:05
 * @version $Id: AbstractEntity.java 13404 2013-11-01 14:10:30Z marcelo.batista $
 */
public abstract class AbstractEntity<Id> implements Serializable {

	private static final long serialVersionUID = 2337679037272983736L;

	/**
	 * Retorna o valor de id
	 *
	 * @return o valor de id
	 */
	public abstract Id getId();

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
//	}
}