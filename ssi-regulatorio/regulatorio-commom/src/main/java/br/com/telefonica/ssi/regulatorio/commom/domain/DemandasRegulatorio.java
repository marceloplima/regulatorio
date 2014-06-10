package br.com.telefonica.ssi.regulatorio.commom.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.DateTime;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Entity
@Table(name = "DemandasRegulatorio", schema = "regulatorio")
public class DemandasRegulatorio extends AbstractEntity<Integer> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3573409715513091398L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDemanda")
	private Integer id;

	@Column(name = "cnnumeroDemanda")
	private String numeroDemanda;

	@ManyToOne(targetEntity = Pessoas.class)
	@JoinColumn(name = "idPessoaAutor", referencedColumnName = "idpessoa")
	private Pessoas autor;

	@ManyToOne(targetEntity = Pessoas.class)
	@JoinColumn(name = "idpessoaSolicitante", referencedColumnName = "idpessoa")
	private Pessoas solicitante;

	@ManyToOne(targetEntity = Areas.class)
	@JoinColumn(name = "idareaOrigem", referencedColumnName = "idarea")
	private Areas origem;

	@Column(name = "dataHoraDemanda")
	private Date dataHoraDemanda;

	@Column(name = "cnmQuestao")
	private String questao;

	@Column(name = "cnmObservacoes")
	private String observacoes;

	@Column(name = "prazo")
	private Date prazo;

	@ManyToOne(targetEntity = StatusRegulatorio.class)
	@JoinColumn(name = "idStatus", referencedColumnName = "idStatus")
	private StatusRegulatorio status;

	@ManyToOne(targetEntity = CategoriaRegulatorio.class)
	@JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")
	private CategoriaRegulatorio categoria;

	@ManyToOne(targetEntity = Procedencia.class)
	@JoinColumn(name = "idProcedencia", referencedColumnName = "idProcedencia")
	private Procedencia procedencia;

	@ManyToOne(targetEntity = AreasRegionais.class)
	@JoinColumn(name = "idAreaRegional", referencedColumnName = "idAreaRegional")
	private AreasRegionais areaRegional;

	@OneToMany(targetEntity = AnexosRegulatorio.class, mappedBy = "demanda")
	private Collection<AnexosRegulatorio> anexos;

	@ManyToMany(targetEntity = UF.class,fetch=FetchType.EAGER)
	@JoinTable(name = "regulatorio.UF_Demandas",
		joinColumns = { @JoinColumn(name = "idDemanda",referencedColumnName="idDemanda") },
		inverseJoinColumns = { @JoinColumn(name = "iduf",referencedColumnName="iduf") })
	private Collection<UF> ufs;

	@ManyToOne(targetEntity = TipoDemanda.class)
	@JoinColumn(name = "idTipoDemanda", referencedColumnName = "idTipoDemanda")
	private TipoDemanda tipoDemanda;

	@ManyToOne(targetEntity = Pessoas.class)
	@JoinColumn(name = "idPessoaEncarregado", referencedColumnName = "idpessoa")
	private Pessoas encarregado;

	@ManyToOne(targetEntity = TipoRede.class)
	@JoinColumn(name = "idTipoRede", referencedColumnName = "idTipoRede")
	private TipoRede tipoRede;

	@ManyToOne(targetEntity = DocumentoOrigem.class)
	@JoinColumn(name = "idDocumentoOrigem", referencedColumnName = "idDocumentoOrigem")
	private DocumentoOrigem documentoOrigem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroDemanda() {
		return numeroDemanda;
	}

	public void setNumeroDemanda(String numeroDemanda) {
		this.numeroDemanda = numeroDemanda;
	}

	public Pessoas getAutor() {
		return autor;
	}

	public void setAutor(Pessoas autor) {
		this.autor = autor;
	}

	public Pessoas getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Pessoas solicitante) {
		this.solicitante = solicitante;
	}

	public Areas getOrigem() {
		return origem;
	}

	public void setOrigem(Areas origem) {
		this.origem = origem;
	}

	public Date getDataHoraDemanda() {
		return dataHoraDemanda;
	}

	public void setDataHoraDemanda(Date dataHoraDemanda) {
		this.dataHoraDemanda = dataHoraDemanda;
	}

	public String getQuestao() {
		return questao;
	}

	public void setQuestao(String questao) {
		this.questao = questao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public StatusRegulatorio getStatus() {
		return status;
	}

	public void setStatus(StatusRegulatorio status) {
		this.status = status;
	}

	public CategoriaRegulatorio getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaRegulatorio categoria) {
		this.categoria = categoria;
	}

	public Procedencia getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(Procedencia procedencia) {
		this.procedencia = procedencia;
	}

	public TipoDemanda getTipoDemanda() {
		return tipoDemanda;
	}

	public void setTipoDemanda(TipoDemanda tipoDemanda) {
		this.tipoDemanda = tipoDemanda;
	}

	public Pessoas getEncarregado() {
		return encarregado;
	}

	public void setEncarregado(Pessoas encarregado) {
		this.encarregado = encarregado;
	}

	public TipoRede getTipoRede() {
		return tipoRede;
	}

	public void setTipoRede(TipoRede tipoRede) {
		this.tipoRede = tipoRede;
	}

	public DocumentoOrigem getDocumentoOrigem() {
		return documentoOrigem;
	}

	public void setDocumentoOrigem(DocumentoOrigem documentoOrigem) {
		this.documentoOrigem = documentoOrigem;
	}

	public boolean isNovaDemanda() {
		if (this.numeroDemanda == null || this.numeroDemanda.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public Collection<UF> getUfs() {
		return ufs;
	}

	public void setUfs(Collection<UF> ufs) {
		this.ufs = ufs;
	}

	public Collection<AnexosRegulatorio> getAnexos() {
		return anexos;
	}

	public void setAnexos(Collection<AnexosRegulatorio> anexos) {
		this.anexos = anexos;
	}

	public AreasRegionais getAreaRegional() {
		return areaRegional;
	}

	public void setAreaRegional(AreasRegionais areaRegional) {
		this.areaRegional = areaRegional;
		if (areaRegional == null) {
			this.ufs = null;
		}
	}

	public boolean isVencendoNoDia() {
		DateTime dataPrazo = new DateTime(this.prazo);
		DateTime hoje = new DateTime();

		if(dataPrazo.getYear() == hoje.getYear() &&
				dataPrazo.getMonthOfYear() == hoje.getMonthOfYear() &&
				dataPrazo.getDayOfMonth() == hoje.getDayOfMonth()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isVencido() {
		DateTime dataPrazo = new DateTime(this.prazo);
		DateTime hoje = new DateTime();

		if(dataPrazo.getYear()<hoje.getYear() || dataPrazo.getMonthOfYear()<hoje.getMonthOfYear()){
			return true;
		}
		else {
			if(dataPrazo.getDayOfMonth()-hoje.getDayOfMonth() <= 2){
				return true;
			}
			else{
				return false;
			}
		}
	}

	public boolean isVenceEmDoisDias() {
		DateTime dataPrazo = new DateTime(this.prazo);
		DateTime hoje = new DateTime();

		if(dataPrazo.getYear() == hoje.getYear() &&
				dataPrazo.getMonthOfYear() == hoje.getMonthOfYear()){
			int diff_dias =dataPrazo.getDayOfMonth() - hoje.getDayOfMonth();
			if(diff_dias>=1 && diff_dias<=2){
				return true;
			}
			else return false;
		}
		else{
			return false;
		}
	}

	public int getEstadoDemanda(){
		if(isVencendoNoDia()){
			return 3;
		}
		if(isVenceEmDoisDias()){
			return 2;
		}
		if(isVencido()){
			return 1;
		}
		else{
			return 4;
		}
	}
}
