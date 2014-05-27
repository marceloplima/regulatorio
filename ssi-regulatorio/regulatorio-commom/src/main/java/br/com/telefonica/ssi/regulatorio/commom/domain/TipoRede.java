package br.com.telefonica.ssi.regulatorio.commom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name="TiposRede",schema="regulatorio")
public class TipoRede extends AbstractEntity<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2089035716747279241L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idTipoRede")
	private Integer idTipoRede;
	
	@Column(name="cnmDescTipoRede")
	private String descricao;

	public Integer getId() {
		return idTipoRede;
	}

	public void setId(Integer idTipoRede) {
		this.idTipoRede = idTipoRede;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
