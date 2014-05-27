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
import javax.persistence.Transient;

/**
 * Classe de configura��o SMTP para os m�dulos SSI
 * O m�dulo JMS ir� ler as configura��es efetuadas por esta classe
 *
 */
@Entity
@Cacheable
@Table(name="ConfigSMTP",schema="dbo")
public class ConfigSMTP implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7803447704900440423L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idsmtp")
	private Long idsmtp;
	
	@Column(name="cnmipsmtp")
	private String cnmipsmtp;
	
	@Column(name="cnmnomeservidor")
	private String cnmnomeservidor;
	
	@Column(name="flagativo")
	private boolean flagativo;
	
	@Column(name="datacadastro", insertable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datacadastro;
	
	@Transient
	private boolean checked;
	
	public Long getId() {
		return idsmtp;
	}
	public void setId(Long idsmtp) {
		this.idsmtp = idsmtp;
	}
	public String getCnmipsmtp() {
		return cnmipsmtp;
	}
	public void setCnmipsmtp(String cnmipsmtp) {
		this.cnmipsmtp = cnmipsmtp;
	}
	public String getCnmnomeservidor() {
		return cnmnomeservidor;
	}
	public void setCnmnomeservidor(String cnmnomeservidor) {
		this.cnmnomeservidor = cnmnomeservidor;
	}
	
	public Calendar getDatacadastro() {
		return datacadastro;
	}
	public void setDatacadastro(Calendar datacadastro) {
		this.datacadastro = datacadastro;
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
	
	public ConfigSMTP getByid(Long id){
		ConfigSMTP obj = new ConfigSMTP();
		obj.setId(id);
		return obj;
	}
	
	@Override
	public int hashCode() {
		return this.idsmtp != null ? 
		this.getClass().hashCode() + this.idsmtp.hashCode() : 
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		ConfigSMTP objint = (ConfigSMTP)obj;
		
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
