package br.com.telefonica.ssi.regulatorio.commom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name="TipoDemanda",schema="regulatorio")
public class TipoDemanda extends AbstractEntity<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6970898256047609305L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idTipoDemanda")
	private Integer idTipoDemanda;
	
	@Column(name="cnmDescTipoDemanda")
	private String descTipoDemanda;

	@Override
	public Integer getId() {
		return idTipoDemanda;
	}

	public void setIdTipoDemanda(Integer idTipoDemanda) {
		this.idTipoDemanda = idTipoDemanda;
	}

	public String getDescricao() {
		return descTipoDemanda;
	}

	public void setDescricao(String descricao) {
		this.descTipoDemanda = descricao;
	}
}
