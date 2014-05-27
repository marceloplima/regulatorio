package br.com.telefonica.ssi.regulatorio.commom.domain.dbo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Cacheable
@Table(name="Regionais",schema="dbo")
public class Regionais implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -772310265412779062L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idregional")
	private Long idregional;

	@Column(name="cnmregional")
	private String cnmregional;

	@Transient
	private boolean checked;

	@ManyToMany(cascade=CascadeType.ALL,targetEntity=Ufs.class)
	@JoinTable(name="Regionais_UF",
		joinColumns={@JoinColumn(name="idregional", referencedColumnName="idregional")},
		inverseJoinColumns={@JoinColumn(name="iduf", referencedColumnName="iduf")}
	)
	private Set<Ufs> ufsregionais;

	public Long getId() {
		return idregional;
	}

	public void setId(Long idregional) {
		this.idregional = idregional;
	}

	public String getCnmregional() {
		return cnmregional;
	}

	public void setCnmregional(String cnmregional) {
		this.cnmregional = cnmregional;
	}

	public List<Ufs> getUfsregionais() {
		if(ufsregionais != null && ufsregionais.size() >= 1)
			return new ArrayList<Ufs>(ufsregionais);
		else
			return new ArrayList<Ufs>();
	}

	public void setUfsregionais(Set<Ufs> ufsregionais) {
		this.ufsregionais = ufsregionais;
	}

	@Override
	public int hashCode() {
		return this.getId() != null ?
		this.getClass().hashCode() + this.getId().hashCode() :
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Regionais objint = (Regionais)obj;

		if(this.getId() != null && objint.getId() != null){
			if(this.getId().equals(objint.getId())){
				objint = null;
				return true;
			}
		}

		objint = null;

		return false;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
