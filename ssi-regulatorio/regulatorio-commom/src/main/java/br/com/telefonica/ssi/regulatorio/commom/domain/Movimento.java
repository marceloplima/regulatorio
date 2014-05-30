package br.com.telefonica.ssi.regulatorio.commom.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@ManyToOne(targetEntity=Pessoas.class)
	@JoinColumn(name="idPessoaAutor",referencedColumnName="idpessoa")
	private Pessoas autor;

	@OneToMany(targetEntity=MovimentoAcionamentoArea.class,mappedBy="movimento")
	private Collection<MovimentoAcionamentoArea> movimentosArea;

	@OneToMany(targetEntity=MovimentoAnaliseOperacional.class,mappedBy="movimento")
	private Collection<MovimentoAnaliseOperacional> movimentosAreaOperacional;

	@OneToMany(targetEntity=MovimentoAnaliseTecnica.class,mappedBy="movimento")
	private Collection<MovimentoAnaliseTecnica> movimentosAnaliseTecnica;

	@OneToMany(targetEntity=MovimentoConclusao.class,mappedBy="movimento")
	private Collection<MovimentoConclusao> movimentosConclusao;

	@OneToMany(targetEntity=MovimentoFollowUp.class,mappedBy="movimento")
	private Collection<MovimentoFollowUp> movimentosFollowUp;

	@OneToMany(targetEntity=MovimentoRevisaoPrazo.class,mappedBy="movimento")
	private Collection<MovimentoRevisaoPrazo> movimentosRevisao;

	@OneToMany(targetEntity=MovimentoTecnico.class,mappedBy="movimento")
	private Collection<MovimentoTecnico> movimentosTecnico;

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

	public Collection<MovimentoAcionamentoArea> getMovimentosArea() {
		return movimentosArea;
	}

	public void setMovimentosArea(
			Collection<MovimentoAcionamentoArea> movimentosArea) {
		this.movimentosArea = movimentosArea;
	}

	public Collection<MovimentoAnaliseOperacional> getMovimentosAreaOperacional() {
		return movimentosAreaOperacional;
	}

	public void setMovimentosAreaOperacional(
			Collection<MovimentoAnaliseOperacional> movimentosAreaOperacional) {
		this.movimentosAreaOperacional = movimentosAreaOperacional;
	}

	public Collection<MovimentoAnaliseTecnica> getMovimentosAnaliseTecnica() {
		return movimentosAnaliseTecnica;
	}

	public void setMovimentosAnaliseTecnica(
			Collection<MovimentoAnaliseTecnica> movimentosAnaliseTecnica) {
		this.movimentosAnaliseTecnica = movimentosAnaliseTecnica;
	}

	public Collection<MovimentoConclusao> getMovimentosConclusao() {
		return movimentosConclusao;
	}

	public void setMovimentosConclusao(
			Collection<MovimentoConclusao> movimentosConclusao) {
		this.movimentosConclusao = movimentosConclusao;
	}

	public Collection<MovimentoFollowUp> getMovimentosFollowUp() {
		return movimentosFollowUp;
	}

	public void setMovimentosFollowUp(
			Collection<MovimentoFollowUp> movimentosFollowUp) {
		this.movimentosFollowUp = movimentosFollowUp;
	}

	public Collection<MovimentoRevisaoPrazo> getMovimentosRevisao() {
		return movimentosRevisao;
	}

	public void setMovimentosRevisao(
			Collection<MovimentoRevisaoPrazo> movimentosRevisao) {
		this.movimentosRevisao = movimentosRevisao;
	}

	public Collection<MovimentoTecnico> getMovimentosTecnico() {
		return movimentosTecnico;
	}

	public void setMovimentosTecnico(Collection<MovimentoTecnico> movimentosTecnico) {
		this.movimentosTecnico = movimentosTecnico;
	}

	public DemandasRegulatorio getDemanda() {
		return demanda;
	}

	public void setDemanda(DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}

	@Transient
	public String getTipo(){
		if(getMovimentosAnaliseTecnica()!=null && getMovimentosAnaliseTecnica().size()>0){
			return "ANÁLISE TÉCNICA";
		}
		if(getMovimentosArea()!=null && getMovimentosArea().size()>0){
			return "ACIONAMENTO ÁREA OPERACIONAL";
		}
		if(getMovimentosAreaOperacional()!=null && getMovimentosAreaOperacional().size()>0){
			return "ACIONAMENTO ÁREA OPERACIONAL";
		}
		if(getMovimentosConclusao()!=null && getMovimentosConclusao().size()>0){
			return "CONCLUSÃO DA DEMANDA";
		}
		if(getMovimentosFollowUp()!=null && getMovimentosFollowUp().size()>0){
			return "REGISTRO DE FOLLOW-UP";
		}
		if(getMovimentosRevisao()!=null && getMovimentosRevisao().size()>0){
			return "REVISÃO DE PRAZO";
		}
		if(getMovimentosTecnico()!=null && getMovimentosTecnico().size()>0){
			return "DEFINIÇÃO DE TÉCNICO";
		}
		else{
			return "SALVAMENTO OU ENCAMINHAMENTO";
		}
	}

}
