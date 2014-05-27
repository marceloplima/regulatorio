package br.com.telefonica.ssi.regulatorio.commom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Entity
@Table(name="ResponsavelTecnico",schema="regulatorio")
public class ResponsavelTecnico extends AbstractEntity<Integer>{


	/**
	 *
	 */
	private static final long serialVersionUID = -9066503706863020361L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idResponsavel")
	private Integer id;

	@OneToOne(targetEntity=Pessoas.class)
	@JoinColumn(name="idPessoa", referencedColumnName="idPessoa")
	private Pessoas pessoa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}
}
