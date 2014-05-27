package br.com.telefonica.ssi.regulatorio.commom.domain.dbo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Cacheable
@Table(name="LogAcesso",schema="dbo")
public class LogAcesso implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1150856908364113412L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idlogacesso")
	private Long idlogacesso;
	
	@Column(name="cnmlogin")
	private String cnmlogin;
	
	@Column(name="cnmmodulo")
	private String cnmmodulo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datacriado", insertable=false, updatable=false)
	private Calendar datacriado;

	public Calendar getDatacriado() {
		return datacriado;
	}

	public void setDatacriado(Calendar datacriado) {
		this.datacriado = datacriado;
	}

	public Long getId() {
		return idlogacesso;
	}

	public void setId(Long idlogacesso) {
		this.idlogacesso = idlogacesso;
	}
	
	public String getCnmlogin() {
		return cnmlogin;
	}

	public void setCnmlogin(String cnmlogin) {
		this.cnmlogin = cnmlogin;
	}

	public String getCnmmodulo() {
		return cnmmodulo;
	}

	public void setCnmmodulo(String cnmmodulo) {
		this.cnmmodulo = cnmmodulo;
	}
	
	@Override
	public int hashCode() {
		return this.idlogacesso != null ? 
		this.getClass().hashCode() + this.idlogacesso.hashCode() : 
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		LogAcesso objint = (LogAcesso)obj;
		
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
