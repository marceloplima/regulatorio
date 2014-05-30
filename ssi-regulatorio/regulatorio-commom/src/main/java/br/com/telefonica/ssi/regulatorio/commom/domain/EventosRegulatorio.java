package br.com.telefonica.ssi.regulatorio.commom.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name="Eventos",schema="regulatorio")
public class EventosRegulatorio extends AbstractEntity<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = -2692529777738306800L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idEvento")
	private Integer idEvento;

	@Column(name="dataCadastro")
	private Date dataCadastro;

	@Column(name="cnmComentario")
	private String comentario;

	@ManyToOne(targetEntity=StatusRegulatorio.class)
	@JoinColumn(name="idStatus",referencedColumnName="idStatus")
	private StatusRegulatorio status;

	@ManyToOne(targetEntity=StatusRegulatorio.class)
	@JoinColumn(name="idStatusAnteriores",referencedColumnName="idStatus")
	private StatusRegulatorio statusAnterior;

	@ManyToOne(targetEntity=MovimentoRevisaoPrazo.class)
	@JoinColumn(name="idMovimentoRevisao",referencedColumnName="idMovimentoRevisao")
	private MovimentoRevisaoPrazo movimentoRevisao;

	@ManyToOne(targetEntity=MovimentoFollowUp.class)
	@JoinColumn(name="idFollowUp",referencedColumnName="idFollowUp")
	private MovimentoFollowUp followUp;

	public Integer getId() {
		return idEvento;
	}

	public void setId(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public StatusRegulatorio getStatus() {
		return status;
	}

	public void setStatus(StatusRegulatorio status) {
		this.status = status;
	}

	public StatusRegulatorio getStatusAnterior() {
		return statusAnterior;
	}

	public void setStatusAnterior(StatusRegulatorio statusAnterior) {
		this.statusAnterior = statusAnterior;
	}
}
