package br.com.telefonica.ssi.regulatorio.commom.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name="CategoriaRegulatorio",schema="regulatorio")
public class CategoriaRegulatorio extends AbstractEntity<Integer>{


	/**
	 *
	 */
	private static final long serialVersionUID = -1851331969707867416L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCategoria")
	private Integer id;

	@Column(name="cnDescCategoria")
	private String descricao;

	@OneToMany(targetEntity=Procedencia.class,mappedBy="categoria")
	private List<Procedencia> procedencias;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Procedencia> getProcedencias() {
		return procedencias;
	}

	public void setProcedencias(List<Procedencia> procedencias) {
		this.procedencias = procedencias;
	}

	@Override
	public int hashCode() {
		return this.id != null ?
		this.getClass().hashCode() + this.id.hashCode() :
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		CategoriaRegulatorio objint = (CategoriaRegulatorio)obj;

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