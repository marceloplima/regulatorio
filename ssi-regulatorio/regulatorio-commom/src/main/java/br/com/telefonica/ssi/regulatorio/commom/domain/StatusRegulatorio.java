package br.com.telefonica.ssi.regulatorio.commom.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name="StatusRegulatorio",schema="regulatorio")
public class StatusRegulatorio extends AbstractEntity<Integer>{

	private static final long serialVersionUID = -2800140808722248785L;

	public static final int ID_RASCUNHO = 26;
	public static final int ID_ANALISE_PRELIMINAR = 27;
	public static final int ID_REVISAO_PRAZO = 28;
	public static final int ID_PENDENTE_DADOS = 29;
	public static final int ID_CANCELADA = 30;
	public static final int ID_CONCLUIDA = 31;
	public static final int ID_PENDENTE_ESCALRECIMENTO = 32;
	public static final int ID_ANALISE_TECNICA = 33;
	public static final int ID_ATENDIMENTO = 34;
	public static final int ID_ANALISE_OPERACIONAL = 35;
	public static final int ID_ANALISE_FINAL = 36;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idStatus")
	private Integer idStatus;

	@Column(name="cnmDescStatus")
	private String descricao;

	@OneToMany(targetEntity=StatusRegulatorio.class)
	@JoinColumn(name="idStatusAnteriores")
	private Collection<StatusRegulatorio> statusAnteriores;

	@OneToMany(targetEntity=StatusRegulatorio.class)
	@JoinColumn(name="idStatusSucessores")
	private Collection<StatusRegulatorio> statusPosteriores;

	public Integer getId() {
		return idStatus;
	}

	public void setId(Integer idStatus) {
		this.idStatus = idStatus;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Collection<StatusRegulatorio> getStatusAnteriores() {
		return statusAnteriores;
	}

	public void setStatusAnteriores(Collection<StatusRegulatorio> statusAnteriores) {
		this.statusAnteriores = statusAnteriores;
	}

	public Collection<StatusRegulatorio> getStatusPosteriores() {
		return statusPosteriores;
	}

	public void setStatusPosteriores(Collection<StatusRegulatorio> statusPosteriores) {
		this.statusPosteriores = statusPosteriores;
	}
}