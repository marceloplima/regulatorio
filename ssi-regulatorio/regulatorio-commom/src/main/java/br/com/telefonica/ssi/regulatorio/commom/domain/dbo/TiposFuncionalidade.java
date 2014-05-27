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
 * Classe que representa os tipos de funcionalidades dos m�dulos (ex: Relat�rio, Formul�rio, etc)
 */

@Entity
@Cacheable
@Table(name="TiposFuncionalidade",schema="dbo")
public class TiposFuncionalidade implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7750655038023888827L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idtipofuncionalidade")
	private Long idtipofuncionalidade;
	
	@Column(name="cnmtipofuncionalidade")
	private String cnmtipofuncionalidade;

	@Column(name="flagativo")
	private boolean flagativo;
	
	@Transient
	private boolean checked;
	
	public Long getId() {
		return idtipofuncionalidade;
	}

	public void setId(Long idtipofuncionalidade) {
		this.idtipofuncionalidade = idtipofuncionalidade;
	}
	
	public String getCnmtipofuncionalidade() {
		return cnmtipofuncionalidade;
	}

	public void setCnmtipofuncionalidade(String cnmtipofuncionalidade) {
		this.cnmtipofuncionalidade = cnmtipofuncionalidade;
	}
	
	public TiposFuncionalidade getByid(Long id){
		TiposFuncionalidade obj = new TiposFuncionalidade();
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
		return this.idtipofuncionalidade != null ? 
		this.getClass().hashCode() + this.idtipofuncionalidade.hashCode() : 
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		TiposFuncionalidade objint = (TiposFuncionalidade)obj;
		
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
