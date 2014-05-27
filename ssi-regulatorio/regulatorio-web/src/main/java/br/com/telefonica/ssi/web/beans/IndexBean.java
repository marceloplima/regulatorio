package br.com.telefonica.ssi.web.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.DemandaService;
import br.com.telefonica.ssi.web.datamodel.DemandasRegulatorioDataModel;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@Named
@SessionScoped
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
	private DemandaService demandaService;

	@PostConstruct
	public void init(){
		//TODO: dar um jeito no dataModel que nao esta funcionando direito! Por isso estou usando um arraylist
		//Sei que é ruim mas é temporario
		Map<String, Object> filtros = new HashMap<String,Object>();
		Pessoas pessoa = RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado();

		filtros.put("autor", pessoa);
		filtros.put("encarregado", pessoa);

		this.dataModel = new DemandasRegulatorioDataModel(demandaService, filtros);
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

	public String consultaDemanda(){
		if(this.idDemanda!=null){
			DemandasRegulatorio demanda = demandaService.findById(idDemanda);
			eventoDemanda.fire(demanda);
			return "cadssi";
		}
		else{
			return "cadssi";
		}
	}

	public void novaDemanda(){

	}

	public Integer getIdDemanda() {
		return idDemanda;
	}

	public void setIdDemanda(Integer idDemanda) {
		this.idDemanda = idDemanda;
	}
}
