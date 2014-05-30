package br.com.telefonica.ssi.regulatorio.commom.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name = "AreasRegionais", schema = "regulatorio")
public class AreasRegionais extends AbstractEntity<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = 7644116682973143512L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAreaRegional")
	private Long id;

	@Column(name = "cnmDescAreaRegional")
	private String descricao;

	@ManyToMany(targetEntity = UF.class)
	@JoinTable(name = "regulatorio.UF_Areas",
		joinColumns = { @JoinColumn(name = "idAreaRegional", referencedColumnName = "idAreaRegional") },
		inverseJoinColumns = { @JoinColumn(name = "iduf", referencedColumnName = "iduf") })
	private Collection<UF> ufs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Collection<UF> getUfs() {
		return ufs;
	}

	public void setUfs(Collection<UF> ufs) {
		this.ufs = ufs;
	}

	@Override
	public int hashCode() {
		return this.id != null ?
		this.getClass().hashCode() + this.id.hashCode() :
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		AreasRegionais objint = (AreasRegionais)obj;

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
