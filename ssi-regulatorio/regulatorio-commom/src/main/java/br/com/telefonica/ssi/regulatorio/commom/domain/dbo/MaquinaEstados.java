package br.com.telefonica.ssi.regulatorio.commom.domain.dbo;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * Classe que representa os estados (status) os quais um documento de m�dulo pode assumir
 */

@Entity
@Cacheable
@Table(name="MaquinaEstados",schema="dbo")
public class MaquinaEstados implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8621149174702116168L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idmaquinaestado")
	private Long idmaquinaestado;
	
	@Column(name="cnmmaquinaestado")
	private String cnmmaquinaestado;

	public Long getId() {
		return idmaquinaestado;
	}

	public void setId(Long idmaquinaestado) {
		this.idmaquinaestado = idmaquinaestado;
	}

	public String getCnmmaquinaestado() {
		return cnmmaquinaestado;
	}

	public void setCnmmaquinaestado(String cnmmaquinaestado) {
		this.cnmmaquinaestado = cnmmaquinaestado;
	}
	
	@Override
	public int hashCode() {
		return this.idmaquinaestado != null ? 
		this.getClass().hashCode() + this.idmaquinaestado.hashCode() : 
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		MaquinaEstados objint = (MaquinaEstados)obj;
		
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
