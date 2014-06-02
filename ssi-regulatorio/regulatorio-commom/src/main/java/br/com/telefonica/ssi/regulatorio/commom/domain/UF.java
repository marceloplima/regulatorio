package br.com.telefonica.ssi.regulatorio.commom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name="UF",schema="regulatorio")
public class UF extends AbstractEntity<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = -2492479467963020658L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iduf")
	private Integer id;

	@Column(name="descricao")
	private String descricao;

	/*@ManyToMany(targetEntity=DemandasRegulatorio.class,mappedBy="ufs")
	private Collection<DemandasRegulatorio> demandas;*/
	@Override
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

	/*public Collection<DemandasRegulatorio> getDemandas() {
		return demandas;
	}*/

	/*public void setDemandas(Collection<DemandasRegulatorio> demandas) {
		this.demandas = demandas;
	}*/

	public UF getByid(Integer id){
		UF obj = new UF();
		obj.setId(id);
		return obj;
	}
}
