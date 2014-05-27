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
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Entity
@Table(name="AnexosRegulatorio",schema="regulatorio")
public class AnexosRegulatorio extends AbstractEntity<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = -6928983986742234933L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idAnexo")
	private Integer idAnexo;

	@ManyToOne(targetEntity=DemandasRegulatorio.class)
	@JoinColumn(name="idDemanda",referencedColumnName="idDemanda")
	private DemandasRegulatorio demanda;

	@ManyToOne(targetEntity=Pessoas.class)
	@JoinColumn(name="idpessoaAutor",referencedColumnName="idpessoa")
	private Pessoas autor;

	/*@ManyToOne(targetEntity=TipoAnexo.class)
	@JoinColumn(name="idTipoAnexo",referencedColumnName="idTipoAnexo")
	private TipoAnexo tipoAnexo;*/

	@Column(name="cnmCaminhoArquivo")
	private String caminhoArquivo;

	@Column(name="cnmVersao")
	private String versao;

	public Integer getId() {
		return idAnexo;
	}

	public void setId(Integer idAnexo) {
		this.idAnexo = idAnexo;
	}

	public DemandasRegulatorio getDemanda() {
		return demanda;
	}

	public void setDemanda(DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}

	public Pessoas getAutor() {
		return autor;
	}

	public void setAutor(Pessoas autor) {
		this.autor = autor;
	}

	/*public TipoAnexo getTipoAnexo() {
		return tipoAnexo;
	}

	public void setTipoAnexo(TipoAnexo tipoAnexo) {
		this.tipoAnexo = tipoAnexo;
	}*/

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}
}
