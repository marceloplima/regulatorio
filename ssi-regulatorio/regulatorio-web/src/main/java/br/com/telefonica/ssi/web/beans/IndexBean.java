package br.com.telefonica.ssi.web.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.CategoriaRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Procedencia;
import br.com.telefonica.ssi.regulatorio.commom.domain.StatusRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.CategoriaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.DemandaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ProcedenciaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.StatusRegulatorioService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.AreasInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.facade.DemandaServiceFacade;
import br.com.telefonica.ssi.web.datamodel.DemandasRegulatorioDataModel;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@ManagedBean
@ViewScoped
public class IndexBean extends AbstractManagedBean{

	/**
	 *
	 */
	private static final long serialVersionUID = -4694941060284682650L;

	private DemandasRegulatorioDataModel dataModel;

	private Integer idDemanda;

	@Inject
	Event<DemandasRegulatorio> eventoDemanda;

	@EJB
	private StatusRegulatorioService statusService;

	@EJB
	private DemandaService demandaService;

	@EJB
	private CategoriaService categoriaService;

	@EJB
	private ProcedenciaService procedenciaService;

	@EJB
	private DemandaServiceFacade facadeDemanda;

	@EJB
	private AreasInt areasService;

	private Areas area;

	private String procedencia;

	private String status;

	private String numeroDemanda;

	private String nomeSolicitante;

	private Date dataInicial;

	private Date dataFinal;

	private String categoria;

	@PostConstruct
	public void init(){
		String idDemanda = getUrlParameter("idDemanda");
		if(idDemanda!=null && !idDemanda.equals("")){
			String outcome = consultaDemanda(Integer.parseInt(idDemanda));
			try{
				getFacesContext().getExternalContext().redirect("interno/cadssi.xhtml?faces-redirect=true");
			}
			catch(Exception ex){
				System.out.println("Exceção ocorrida na pesquisa de demanda por url: ");
				ex.printStackTrace();
			}
		}

		Map<String, Object> filtros = new HashMap<String,Object>();

		dataModel = new DemandasRegulatorioDataModel(facadeDemanda, filtros, new DemandasRegulatorio());

	}

	public DemandasRegulatorioDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(DemandasRegulatorioDataModel dataModel) {
		this.dataModel = dataModel;
	}

	public List<DemandasRegulatorio> getMinhasDemandas(){
		Pessoas pessoa = RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado();
		return demandaService.getDemandasAVerComigo(pessoa);
	}

	public String consultaDemanda(Integer id){
		if(id!=null){
			DemandasRegulatorio demanda = demandaService.findById(id);
			eventoDemanda.fire(demanda);
			return "cadssi";
		}
		else{
			return "";
		}
	}

	public Integer getIdDemanda() {
		return idDemanda;
	}

	public void setIdDemanda(Integer idDemanda) {
		this.idDemanda = idDemanda;
	}

	public List<StatusRegulatorio> getStatusRegulatorio(){
		return statusService.findAll();
	}

	public String getNumeroDemanda() {
		return numeroDemanda;
	}

	public void setNumeroDemanda(String numeroDemanda) {
		this.numeroDemanda = numeroDemanda;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void buscar(){
		Map<String,Object> filtros = new HashMap<String, Object>();

		if(getNomeSolicitante()!=null && !getNomeSolicitante().equals("")){
			filtros.put("nomeSolicitante", getNomeSolicitante());
		}
		if(getNumeroDemanda()!=null && !getNumeroDemanda().equals("")){
			filtros.put("numeroDemanda", getNumeroDemanda());
		}
		if(getStatus()!=null && !getStatus().equals("")){
			filtros.put("status", getStatus());
		}
		if(getDataInicial()!=null){
			filtros.put("dataInicial", getDataInicial());
		}
		if(getDataFinal()!=null){
			filtros.put("dataFinal", getDataFinal());
		}
		if(getCategoria()!=null){
			filtros.put("categoria",getCategoria());
		}
		if(getProcedencia()!=null){
			filtros.put("procedencia", getProcedencia());
		}
		if(getArea()!=null){
			filtros.put("area", getArea().getCnmdescarea());
		}

		dataModel = new DemandasRegulatorioDataModel(facadeDemanda, filtros, new DemandasRegulatorio());
	}

	public String getStatus() {
		return status;
	}

	public List<CategoriaRegulatorio> getCategorias(){
		return categoriaService.findAll();
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Procedencia> getProcedencias(){
		return procedenciaService.findAll();
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public List<Areas> getAreas(){
		return areasService.recuperar();
	}

	public Areas getArea() {
		return area;
	}

	public void setArea(Areas area) {
		this.area = area;
	}
}
