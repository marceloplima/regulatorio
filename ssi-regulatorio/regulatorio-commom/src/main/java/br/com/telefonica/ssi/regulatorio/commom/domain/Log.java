package br.com.telefonica.ssi.regulatorio.commom.domain;

import java.text.SimpleDateFormat;
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
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Entity
@Table(name="LogsRegulatorio",schema="regulatorio")
public class Log extends AbstractEntity<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = -7520986533704050373L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="dataCadastro")
	private Date dataCadastro;

	@Column(name="info")
	private String info;

	@ManyToOne(targetEntity=DemandasRegulatorio.class)
	@JoinColumn(name="idDemanda",referencedColumnName="idDemanda")
	private DemandasRegulatorio demanda;

	@ManyToOne(targetEntity=Pessoas.class)
	@JoinColumn(name="idPessoa",referencedColumnName="idPessoa")
	private Pessoas pessoa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public DemandasRegulatorio getDemanda() {
		return demanda;
	}

	public void setDemanda(DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(dataCadastro==null?null:new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataCadastro)+" (");
		sb.append(info+") por ");
		sb.append(pessoa.getCnmnome());

		return sb.toString();
	}

}
