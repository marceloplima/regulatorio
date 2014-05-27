package br.com.telefonica.ssi.regulatorio.commom.domain.dbo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/** 
 * Classe que representa os telefones das pessoas que usam os sistemas SSI
 * 
 * @see TiposTelefone tipotelefone
 */

@Entity
@Cacheable
@Table(name="Telefones",schema="dbo")
public class Telefones implements Serializable{
	
	private static final long serialVersionUID = -6246277290542484352L;	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtelefone")
	private Long idtelefone;
	
	@ManyToOne
	@JoinColumn(name="idtipotelefone", referencedColumnName="idtipotelefone")
	private TiposTelefone tipotelefone;
	
	@Column(name="cnmtelefone")
	private String cnmtelefone;
	
	@Column(name="datacadastro", insertable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datacadastro;

	@ManyToOne
	@JoinColumn(name="idpessoa", referencedColumnName="idpessoa")
	private Pessoas telefonespessoa;
	
	@Transient
	private boolean checked;
	
	@Column(name="flagativo", columnDefinition="default true")
	private boolean flagativo;
	
	public Long getId() {
		return idtelefone;
	}

	public void setId(Long idtelefone) {
		this.idtelefone = idtelefone;
	}

	public TiposTelefone getTipotelefone() {
		return tipotelefone;
	}

	public void setTipotelefone(TiposTelefone tipotelefone) {
		this.tipotelefone = tipotelefone;
	}

	public String getCnmtelefone() {
		return cnmtelefone;
	}

	public void setCnmtelefone(String cnmtelefone) {
		this.cnmtelefone = cnmtelefone;
	}
	
	public Calendar getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(Calendar datacadastro) {
		this.datacadastro = datacadastro;
	}
	
	@Override
	public int hashCode() {
		return this.idtelefone != null ? 
		this.getClass().hashCode() + this.idtelefone.hashCode() : 
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		Telefones objint = (Telefones)obj;
		
		if(this.getId() != null && objint.getId() != null){
			if(this.getId().equals(objint.getId())){
				objint = null;
				return true;
			}
		}
		
		objint = null;
		
		return false;
	}

	public Pessoas getTelefonespessoa() {
		return telefonespessoa;
	}

	public void setTelefonespessoa(Pessoas telefonespessoa) {
		this.telefonespessoa = telefonespessoa;
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
	
}
