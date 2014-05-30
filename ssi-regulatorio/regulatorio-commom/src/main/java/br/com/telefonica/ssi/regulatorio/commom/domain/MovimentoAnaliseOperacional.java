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
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;

@Entity
@Table(name="MovimentoAnaliseOperacional",schema="regulatorio")
public class MovimentoAnaliseOperacional extends AbstractEntity<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = 6546582132971535159L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idMovimentoAnaliseOperacional")
	private Integer idMovmentoAnaliseOperacional;

	@ManyToOne(targetEntity=Movimento.class)
	@JoinColumn(name="idMovimento",referencedColumnName="idMovimento")
	private Movimento movimento;

	@OneToOne(targetEntity=Areas.class)
	@JoinColumn(name="idarea",referencedColumnName="idarea")
	private Areas areaOperacional;

	public Integer getId() {
		return idMovmentoAnaliseOperacional;
	}

	public void setId(Integer idMovmentoAnaliseOperacional) {
		this.idMovmentoAnaliseOperacional = idMovmentoAnaliseOperacional;
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
