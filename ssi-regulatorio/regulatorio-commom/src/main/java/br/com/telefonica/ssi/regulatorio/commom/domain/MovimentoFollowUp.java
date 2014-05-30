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
@Table(name="MovimentoFollowUp",schema="regulatorio")
public class MovimentoFollowUp extends AbstractEntity<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = 5898500104750815470L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idFollowUp")
	private Integer idMovimento;

	@ManyToOne(targetEntity=Movimento.class)
	@JoinColumn(name="idMovimento",referencedColumnName="idMovimento")
	private Movimento movimento;

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
}
