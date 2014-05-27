package br.com.telefonica.ssi.regulatorio.commom.domain.dbo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/** 
 * Representa os grupos referentes aos m�dulos (Ex: Compras, etc) 
 * 
 */


@Entity
@Cacheable
@Table(name="GruposModulos",schema="dbo")
public class GruposModulos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1123710543527330919L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idgrupomodulo")
	private Long idgrupomodulo;
	
	@Column(name="cnmgrupomodulo")
	private String cnmgrupomodulo;
	
	@Column(name="flagativo")
	private boolean flagativo;
	
	@ManyToMany(mappedBy = "pessoasgruposmodulos", cascade=CascadeType.ALL)
	private List<Pessoas> gruposmodulospessoas;

	@ManyToOne
	@JoinColumn(name="idmodulo", referencedColumnName="idmodulo")
	private Modulos modulo;

	@Column(name="datacadastro", insertable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datacadastro;
	
	@Transient
	private boolean checked;
	
	public Long getId() {
		return idgrupomodulo;
	}

	public void setId(Long idgrupomodulo) {
		this.idgrupomodulo = idgrupomodulo;
	}

	public String getCnmgrupomodulo() {
		return cnmgrupomodulo;
	}

	public void setCnmgrupomodulo(String cnmgrupomodulo) {
		this.cnmgrupomodulo = cnmgrupomodulo;
	}
	
	public GruposModulos getByid(Long id){
		GruposModulos obj = new GruposModulos();
		obj.setId(id);
		return obj;
	}

	public List<Pessoas> getGruposmodulospessoas() {
		return gruposmodulospessoas;
	}

	public void setGruposmodulospessoas(List<Pessoas> gruposmodulospessoas) {
		this.gruposmodulospessoas = gruposmodulospessoas;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isFlagativo() {
		return flagativo;
	}

	public void setFlagativo(boolean flagativo) {
		this.flagativo = flagativo;
	}

	public Modulos getModulo() {
		return modulo;
	}

	public void setModulo(Modulos modulo) {
		this.modulo = modulo;
	}

	public Calendar getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(Calendar datacadastro) {
		this.datacadastro = datacadastro;
	}
			
	@Override
	public int hashCode() {
		return this.idgrupomodulo != null ? 
		this.getClass().hashCode() + this.idgrupomodulo.hashCode() : 
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		GruposModulos objint = (GruposModulos)obj;
		
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
