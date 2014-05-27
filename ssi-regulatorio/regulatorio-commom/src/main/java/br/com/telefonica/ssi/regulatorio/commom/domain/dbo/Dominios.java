package br.com.telefonica.ssi.regulatorio.commom.domain.dbo;


import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Cacheable
@Table(name="Dominios",schema="dbo")
public class Dominios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7578284859981237543L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="iddominio")
	private Integer iddominio;
	
	@Column(name="cnmdominio")
	private String cnmdominio;
	
	@Column(name="cnmip")
	private String cnmip;
	
	public Integer getId() {
		return iddominio;
	}
	public void setId(Integer iddominio) {
		this.iddominio = iddominio;
	}
	public String getCnmdominio() {
		return cnmdominio;
	}
	public void setCnmdominio(String cnmdominio) {
		this.cnmdominio = cnmdominio;
	}
	public String getCnmip() {
		return cnmip;
	}
	public void setCnmip(String cnmip) {
		this.cnmip = cnmip;
	}
	
	public Dominios getByid(Integer id_dominio){
		Dominios dom = new Dominios();
		dom.setId(id_dominio);
		return dom;
	}
	
	@Override
	public int hashCode() {
		return this.iddominio != null ? 
		this.getClass().hashCode() + this.iddominio.hashCode() : 
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		Dominios objint = (Dominios)obj;
		
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
