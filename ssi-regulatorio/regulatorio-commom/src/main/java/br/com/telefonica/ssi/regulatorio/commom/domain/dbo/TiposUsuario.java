package br.com.telefonica.ssi.regulatorio.commom.domain.dbo;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/** 
 * Classe que representa os tipos de usu�rios que utilizam os sistemas SSI
 */

@Entity
@Cacheable
@Table(name="TiposUsuario",schema="dbo")
public class TiposUsuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2921798134468891536L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtipousuario")
	private Long idtipousuario;
	
	@Column(name="cnmtipousuario")
	private String cnmtipousuario;
	
	@Column(name="flagativo")
	private boolean flagativo;
	
	@Transient
	private boolean checked;
	
	public Long getId() {
		return idtipousuario;
	}

	public void setId(Long idtipousuario) {
		this.idtipousuario = idtipousuario;
	}

	public String getCnmtipousuario() {
		return cnmtipousuario;
	}

	public void setCnmtipousuario(String cnmtipousuario) {
		this.cnmtipousuario = cnmtipousuario;
	}
	
	public TiposUsuario getByid(Long id){
		TiposUsuario obj = new TiposUsuario();
		obj.setId(id);
		return obj;
	}
	
	public boolean isFlagativo() {
		return flagativo;
	}

	public void setFlagativo(boolean flagativo) {
		this.flagativo = flagativo;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	@Override
	public int hashCode() {
		return this.idtipousuario != null ? 
		this.getClass().hashCode() + this.idtipousuario.hashCode() : 
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		TiposUsuario objint = (TiposUsuario)obj;
		
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
