package br.com.telefonica.ssi.regulatorio.commom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name="ResultadoAnalise",schema="regulatorio")
public class ResultadoAnalise extends AbstractEntity<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8366096283960925895L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idResultadoAnalise")
	private Integer idResultadoAnalise;
	
	@Column(name="cnmDescResultadoAnalise")
	private String descricao;

	public Integer getId() {
		return idResultadoAnalise;
	}

	public void setId(Integer id) {
		this.idResultadoAnalise = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
