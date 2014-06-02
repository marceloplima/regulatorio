package br.com.telefonica.ssi.core.domain.patterns.entity;

import java.io.Serializable;

/**
 *
 * Esta classe define o comportamento padrão de toda e qualquer entidade
 * mapeada em um sistema através da API JPA.
 *
 * @author <a href='mailto:marcelo.batista@druid.com.br'>Marcelo Batista</a>
 * @date 17/04/2013 09:56:05
 * @version $Id: AbstractEntity.java 13404 2013-11-01 14:10:30Z marcelo.batista $
 */
@SuppressWarnings("all")
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
		return this.getId() != null ?
		this.getClass().hashCode() + this.getId().hashCode() :
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		AbstractEntity<Id> objint = (AbstractEntity<Id>)obj;

		if(this.getId() != null && objint.getId() != null){
			if(this.getId().equals(objint.getId())){
				objint = null;
				return true;
			}
		}

		objint = null;

		return false;
	}

}