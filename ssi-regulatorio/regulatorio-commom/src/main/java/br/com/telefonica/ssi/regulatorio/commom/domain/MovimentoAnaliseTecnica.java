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
@Table(name="MovimentoAnaliseTecnica",schema="regulatorio")
public class MovimentoAnaliseTecnica extends AbstractEntity<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = 832007280502845027L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idMovimentoAnalise")
	private Integer idMovimento;

	@Column(name="dtPrevisao")
	private Date previsao;

	@Column(name="cnmEsclarecimentos")
	private String esclarecimentos;

	@ManyToOne(targetEntity=ResultadoAnalise.class)
	@JoinColumn(name="idResultadoAnalise",referencedColumnName="idResultadoAnalise")
	private ResultadoAnalise resultadoAnalise;

	@ManyToOne(targetEntity=Complexidade.class)
	@JoinColumn(name="idComplexidade",referencedColumnName="idComplexidade")
	private Complexidade complexidade;

	@ManyToOne(targetEntity=Execucao.class)
	@JoinColumn(name="idExecucao",referencedColumnName="idExecucao")
	private Execucao execucao;

	@ManyToOne(targetEntity=Movimento.class)
	@JoinColumn(name="idMovimento",referencedColumnName="idMovimento")
	private Movimento movimento;

	public Integer getId() {
		return idMovimento;
	}

	public void setId(Integer idMovimento) {
		this.idMovimento = idMovimento;
	}

	public Date getPrevisao() {
		return previsao;
	}

	public void setPrevisao(Date previsao) {
		this.previsao = previsao;
	}

	public String getEsclarecimentos() {
		return esclarecimentos;
	}

	public void setEsclarecimentos(String esclarecimentos) {
		this.esclarecimentos = esclarecimentos;
	}

	public ResultadoAnalise getResultadoAnalise() {
		return resultadoAnalise;
	}

	public void setResultadoAnalise(ResultadoAnalise resultadoAnalise) {
		this.resultadoAnalise = resultadoAnalise;
	}

	public Complexidade getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(Complexidade complexidade) {
		this.complexidade = complexidade;
	}

	public Movimento getMovimento() {
		return movimento;
	}

	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}

	public Execucao getExecucao() {
		return execucao;
	}

	public void setExecucao(Execucao execucao) {
		this.execucao = execucao;
	}
}
