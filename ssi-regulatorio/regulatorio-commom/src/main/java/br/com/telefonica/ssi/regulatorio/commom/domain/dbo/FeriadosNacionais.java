package br.com.telefonica.ssi.regulatorio.commom.domain.dbo;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Cacheable
@Table(name="FeriadosNacionais",schema="dbo")
public class FeriadosNacionais implements Serializable{


	private static final long serialVersionUID = 4307733881506021650L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idferiadonacional")
	private Long idferiadonacional;
	
	@Column(name="dia")
	private int dia;
	
	@Column(name="mes")
	private int mes;
	
	@OneToOne
	@JoinColumn(name="idferiado")
	private Feriados feriadonacional;
	
	public FeriadosNacionais() {}

	public Long getId() {
		return idferiadonacional;
	}

	public void setId(Long idferiadonacional) {
		this.idferiadonacional = idferiadonacional;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}
	
	public Feriados getFeriadonacional() {
		return feriadonacional;
	}

	public void setFeriadonacional(Feriados feriadonacional) {
		this.feriadonacional = feriadonacional;
	}

	@Override
	public int hashCode() {
		return this.getId() != null ? 
		this.getClass().hashCode() + this.getId().hashCode() : 
		super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		FeriadosNacionais objint = (FeriadosNacionais)obj;
		
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
