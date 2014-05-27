package br.com.telefonica.ssi.regulatorio.commom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;

@Entity
@Table(name="TipoAnexo",schema="regulatorio")
public class TipoAnexo extends AbstractEntity<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7162932991719734519L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idTipoAnexo")
	private Integer idTipoAnexo;
	
	@Column(name="cnmTipoAnexo")
	private String tipoAnexo;
	
	@Column(name="cnmNomeArquivo")
	private String nomeArquio;
	
	@Column(name="flagExibeMenuAnexo")
	private Character flagExibeMenuAnexo;

	public Integer getId() {
		return idTipoAnexo;
	}

	public void setId(Integer idTipoAnexo) {
		this.idTipoAnexo = idTipoAnexo;
	}

	public String getTipoAnexo() {
		return tipoAnexo;
	}

	public void setTipoAnexo(String tipoAnexo) {
		this.tipoAnexo = tipoAnexo;
	}

	public String getNomeArquio() {
		return nomeArquio;
	}

	public void setNomeArquio(String nomeArquio) {
		this.nomeArquio = nomeArquio;
	}

	public boolean isFlagExibeMenuArquivo() {
		return Boolean.valueOf(this.flagExibeMenuAnexo.toString());
	}

	public void setFlagExibeMenuArquivo(boolean exibeMenuArquivo) {
		this.flagExibeMenuAnexo = Boolean.valueOf(exibeMenuArquivo).toString().charAt(0);
	}
	
}
