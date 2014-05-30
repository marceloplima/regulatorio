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
@Table(name="MovimentoRevisaoPrazo",schema="regulatorio")
public class MovimentoRevisaoPrazo extends AbstractEntity<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1035279056199141831L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idMovimentoRevisao")
	private Integer idMovimento;

	@Column(name="dtPrazoProposto")
	private Date prazo;

	@ManyToOne(targetEntity=Movimento.class)
	@JoinColumn(name="idMovimento",referencedColumnName="idMovimento")
	private Movimento movimento;

	public Integer getId() {
		return idMovimento;
	}

	public void setId(Integer idMovimento) {
		this.idMovimento = idMovimento;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public Movimento getMovimento() {
		return movimento;
	}

	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}
}
