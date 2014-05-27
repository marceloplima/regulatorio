package br.com.telefonica.ssi.regulatorio.commom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name="Complexidade",schema="regulatorio")
public class Complexidade extends AbstractEntity<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8927320348896833342L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idComplexidade")
	private Integer idComplexidade;
	
	@Column(name="cnmDescComplexidade")
	private String cnmDescComplexidade;

	public Integer getId() {
		return idComplexidade;
	}

	public void setId(Integer idComplexidade) {
		this.idComplexidade = idComplexidade;
	}

	public String getCnmDescricao() {
		return cnmDescComplexidade;
	}

	public void setCnmDescricao(String descricao) {
		this.cnmDescComplexidade = descricao;
	}
}
