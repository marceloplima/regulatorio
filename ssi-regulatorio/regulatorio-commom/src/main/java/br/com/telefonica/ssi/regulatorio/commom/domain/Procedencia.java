package br.com.telefonica.ssi.regulatorio.commom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name="Procedencia",schema="regulatorio")
public class Procedencia extends AbstractEntity<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3208190775884969879L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="")
	private Integer idProcedencia;
	
	@Column(name="cnmDescProcedencia")
	private String descricao;
	
	@ManyToOne(targetEntity=CategoriaRegulatorio.class)
	@JoinColumn(name="idCategoria")
	private CategoriaRegulatorio categoria;

	public Integer getId() {
		return idProcedencia;
	}

	public void setId(Integer id) {
		this.idProcedencia = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CategoriaRegulatorio getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaRegulatorio categoria) {
		this.categoria = categoria;
	}
}
