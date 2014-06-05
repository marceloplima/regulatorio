package br.com.telefonica.ssi.regulatorio.commom.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
	private Integer idMovimentoAcionamento;

	@OneToOne(targetEntity=Movimento.class)
	@JoinColumn(name="idMovimento",referencedColumnName="idMovimento")
	private Movimento movimento;

	@ManyToMany(targetEntity=Areas.class)
	@JoinTable(name="regulatorio.MovimentoAcionamentoArea_Areas",
		joinColumns={@JoinColumn(name="idMovimentoAreaOperacional",referencedColumnName="idMovimentoAreaOperacional")},
		inverseJoinColumns={@JoinColumn(name="idarea",referencedColumnName="idarea")}
			)
	private Collection<Areas> areasOperacionais;

	public Integer getId() {
		return idMovimentoAcionamento;
	}

	public void setId(Integer idMovimentoAcionamento) {
		this.idMovimentoAcionamento = idMovimentoAcionamento;
	}

	public Movimento getMovimento() {
		return movimento;
	}

	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}

	public Collection<Areas> getAreasOperacionais() {
		return areasOperacionais;
	}

	public void setAreasOperacionais(Collection<Areas> areasOperacionais) {
		this.areasOperacionais = areasOperacionais;
	}
}
