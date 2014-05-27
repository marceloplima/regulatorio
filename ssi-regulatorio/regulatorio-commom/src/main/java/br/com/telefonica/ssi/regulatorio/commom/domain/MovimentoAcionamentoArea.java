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
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;

@Entity
@Table(name="MovimentoAcionamentoArea",schema="regulatorio")
public class MovimentoAcionamentoArea extends AbstractEntity<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = 2539971952957421262L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idMovimentoAreaOperacional")
	private Integer idMovimento;

	@ManyToOne(targetEntity=Movimento.class)
	@JoinColumn(name="idMovimento",referencedColumnName="idMovimento")
	private Movimento movimento;

	@ManyToOne(targetEntity=Areas.class)
	@JoinColumn(name="idAreaOperacional",referencedColumnName="idArea")
	private Areas areaOperacional;

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

	public Areas getAreaOperacional() {
		return areaOperacional;
	}

	public void setAreaOperacional(Areas areaOperacional) {
		this.areaOperacional = areaOperacional;
	}


}
