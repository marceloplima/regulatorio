package br.com.telefonica.ssi.regulatorio.commom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;

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

	@Column(name="ativo")
	private Boolean ativo;

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

	public String getDescTipoDemanda() {
		return descTipoDemanda;
	}

	public void setDescTipoDemanda(String descTipoDemanda) {
		this.descTipoDemanda = descTipoDemanda;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public int hashCode() {
		return this.idTipoDemanda != null ?
		this.getClass().hashCode() + this.idTipoDemanda.hashCode() :
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		TipoDemanda objint = (TipoDemanda)obj;

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