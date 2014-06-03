package br.com.telefonica.ssi.regulatorio.commom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Entity
@Table(name="MovimentoTecnico",schema="regulatorio")
public class MovimentoTecnico extends AbstractEntity<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = -231747869863990038L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idMovimentoTecnico")
	private Integer idMovimento;

	@ManyToOne(targetEntity=Pessoas.class)
	@JoinColumn(name="idpessoaEncarregado",referencedColumnName="idpessoa")
	private Pessoas encarregado;

	@OneToOne(targetEntity=Movimento.class)
	@JoinColumn(name="idMovimento",referencedColumnName="idMovimento")
	private Movimento movimento;

	public Integer getId() {
		return idMovimento;
	}

	public void setId(Integer idMovimento) {
		this.idMovimento = idMovimento;
	}

	public Pessoas getEncarregado() {
		return encarregado;
	}

	public void setEncarregado(Pessoas encarregado) {
		this.encarregado = encarregado;
	}

	public Movimento getMovimento() {
		return movimento;
	}

	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}
}
