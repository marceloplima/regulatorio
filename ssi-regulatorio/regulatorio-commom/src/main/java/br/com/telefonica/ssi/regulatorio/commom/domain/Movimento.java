package br.com.telefonica.ssi.regulatorio.commom.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Entity
@Table(name="Movimento",schema="regulatorio")
public class Movimento extends AbstractEntity<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = -6307976263114647555L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idMovimento")
	private Integer idMovimento;

	@Column(name="dataHora")
	private Date dataHora;

	@Column(name="cnmComentario")
	private String comentario;

	@OneToOne(targetEntity=Pessoas.class)
	@JoinColumn(name="idpessoaAutor",referencedColumnName="idpessoa")
	private Pessoas autor;

	@OneToOne(targetEntity=MovimentoAcionamentoArea.class,mappedBy="movimento")
	private MovimentoAcionamentoArea movimentosArea;

	@OneToOne(targetEntity=MovimentoAnaliseOperacional.class,mappedBy="movimento")
	private MovimentoAnaliseOperacional movimentosAreaOperacional;

	@OneToOne(targetEntity=MovimentoAnaliseTecnica.class,mappedBy="movimento")
	private MovimentoAnaliseTecnica movimentosAnaliseTecnica;

	@OneToOne(targetEntity=MovimentoConclusao.class,mappedBy="movimento")
	private MovimentoConclusao movimentosConclusao;

	@OneToOne(targetEntity=MovimentoFollowUp.class,mappedBy="movimento")
	private MovimentoFollowUp movimentosFollowUp;

	@OneToOne(targetEntity=MovimentoRevisaoPrazo.class,mappedBy="movimento")
	private MovimentoRevisaoPrazo movimentosRevisao;

	@OneToOne(targetEntity=MovimentoTecnico.class,mappedBy="movimento")
	private MovimentoTecnico movimentosTecnico;

	public Integer getId() {
		return idMovimento;
	}

	public void setId(Integer idMovimento) {
		this.idMovimento = idMovimento;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Pessoas getAutor() {
		return autor;
	}

	public void setAutor(Pessoas autor) {
		this.autor = autor;
	}

	@ManyToOne(targetEntity=DemandasRegulatorio.class)
	@JoinColumn(name="idDemanda",referencedColumnName="idDemanda")
	private DemandasRegulatorio demanda;

	public DemandasRegulatorio getDemanda() {
		return demanda;
	}

	public void setDemanda(DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}

	public MovimentoAcionamentoArea getMovimentosArea() {
		return movimentosArea;
	}

	public void setMovimentosArea(MovimentoAcionamentoArea movimentosArea) {
		this.movimentosArea = movimentosArea;
	}

	public MovimentoAnaliseOperacional getMovimentosAreaOperacional() {
		return movimentosAreaOperacional;
	}

	public void setMovimentosAreaOperacional(
			MovimentoAnaliseOperacional movimentosAreaOperacional) {
		this.movimentosAreaOperacional = movimentosAreaOperacional;
	}

	public MovimentoAnaliseTecnica getMovimentosAnaliseTecnica() {
		return movimentosAnaliseTecnica;
	}

	public void setMovimentosAnaliseTecnica(
			MovimentoAnaliseTecnica movimentosAnaliseTecnica) {
		this.movimentosAnaliseTecnica = movimentosAnaliseTecnica;
	}

	public MovimentoConclusao getMovimentosConclusao() {
		return movimentosConclusao;
	}

	public void setMovimentosConclusao(MovimentoConclusao movimentosConclusao) {
		this.movimentosConclusao = movimentosConclusao;
	}

	public MovimentoFollowUp getMovimentosFollowUp() {
		return movimentosFollowUp;
	}

	public void setMovimentosFollowUp(MovimentoFollowUp movimentosFollowUp) {
		this.movimentosFollowUp = movimentosFollowUp;
	}

	public MovimentoRevisaoPrazo getMovimentosRevisao() {
		return movimentosRevisao;
	}

	public void setMovimentosRevisao(MovimentoRevisaoPrazo movimentosRevisao) {
		this.movimentosRevisao = movimentosRevisao;
	}

	public MovimentoTecnico getMovimentosTecnico() {
		return movimentosTecnico;
	}

	public void setMovimentosTecnico(MovimentoTecnico movimentosTecnico) {
		this.movimentosTecnico = movimentosTecnico;
	}

	@Transient
	public String getTipo(){
		if(getMovimentosAnaliseTecnica()!=null){
			return "ANÁLISE TÉCNICA";
		}
		if(getMovimentosArea()!=null){
			return "ACIONAMENTO ÁREA OPERACIONAL";
		}
		if(getMovimentosAreaOperacional()!=null){
			return "ACIONAMENTO ÁREA OPERACIONAL";
		}
		if(getMovimentosConclusao()!=null){
			return "CONCLUSÃO DA DEMANDA";
		}
		if(getMovimentosFollowUp()!=null){
			return "REGISTRO DE FOLLOW-UP";
		}
		if(getMovimentosRevisao()!=null){
			return "REVISÃO DE PRAZO";
		}
		if(getMovimentosTecnico()!=null){
			return "DEFINIÇÃO DE TÉCNICO";
		}
		else{
			return "SALVAMENTO OU ENCAMINHAMENTO";
		}
	}

}
