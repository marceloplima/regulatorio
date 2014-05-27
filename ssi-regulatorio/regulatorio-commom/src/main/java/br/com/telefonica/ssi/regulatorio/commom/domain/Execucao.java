package br.com.telefonica.ssi.regulatorio.commom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name="Execucao",schema="regulatorio")
public class Execucao extends AbstractEntity<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3925434394939498375L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idExecucao")
	private Integer idExecucao;
	
	@Column(name="cnmDescExecucao")
	private String descricao;

	public Integer getId() {
		return idExecucao;
	}

	public void setId(Integer idExecucao) {
		this.idExecucao = idExecucao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
