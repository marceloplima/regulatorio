package br.com.telefonica.ssi.regulatorio.commom.domain;

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
@Table(name="MovimentoConclusao",schema="regulatorio")
public class MovimentoConclusao extends AbstractEntity<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3358422509878953341L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idMovimentoConclusao")
	private Integer idMovimento;
	
	@ManyToOne(targetEntity=Movimento.class)
	@JoinColumn(name="idMovimento",referencedColumnName="idMovimento")
	private Movimento movimento;
	
	@ManyToOne(targetEntity=Complexidade.class)
	@JoinColumn(name="idComplexidade",referencedColumnName="idComplexidade")
	private Complexidade complexidade;
	
	@ManyToOne(targetEntity=Execucao.class)
	@JoinColumn(name="idExecucao",referencedColumnName="idExecucao")
	private Execucao execucao;

	public Integer getId() {
		return idMovimento;
	}

	public void setId(Integer idMovimento) {
		this.idMovimento = idMovimento;
	}

	public Movimento getMovimento() {
		return movimento;
	}

	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}

	public Complexidade getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(Complexidade complexidade) {
		this.complexidade = complexidade;
	}

	public Execucao getExecucao() {
		return execucao;
	}

	public void setExecucao(Execucao execucao) {
		this.execucao = execucao;
	}
}
