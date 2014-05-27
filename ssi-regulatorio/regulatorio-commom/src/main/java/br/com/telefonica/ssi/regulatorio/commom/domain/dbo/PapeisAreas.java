package br.com.telefonica.ssi.regulatorio.commom.domain.dbo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Cacheable
@Table(name="PapeisAreas",schema="dbo")
public class PapeisAreas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1735387108919459023L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idpapelarea")
	private Long idpapelarea;
	
	@Column(length=50, name="cnmpapelarea")
	private String cnmpapelarea;
	
	@OneToMany(mappedBy="papelarea", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Areas> areas;

	public PapeisAreas getByid(Long id){
		PapeisAreas obj = new PapeisAreas();
		obj.setId(id);
		return obj;
	}
	
	public Long getId() {
		return idpapelarea;
	}

	public void setId(Long idpapelarea) {
		this.idpapelarea = idpapelarea;
	}
	
	public String getCnmpapelarea() {
		return cnmpapelarea;
	}

	public void setCnmpapelarea(String cnmpapelarea) {
		this.cnmpapelarea = cnmpapelarea;
	}
	
	@Override
	public int hashCode() {
		return this.idpapelarea != null ? 
		this.getClass().hashCode() + this.idpapelarea.hashCode() : 
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		PapeisAreas objint = (PapeisAreas)obj;
		
		if(this.getId() != null && objint.getId() != null){
			if(this.getId().equals(objint.getId())){
				objint = null;
				return true;
			}
		}
		
		objint = null;
		
		return false;
	}

	public List<Areas> getAreas() {
		return areas;
	}

	public void setAreas(List<Areas> areas) {
		this.areas = areas;
	}	

}
