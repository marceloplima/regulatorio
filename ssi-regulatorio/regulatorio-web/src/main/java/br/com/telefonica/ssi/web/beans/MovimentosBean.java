package br.com.telefonica.ssi.web.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Model;
import javax.faces.bean.ViewScoped;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoFacade;
import br.com.telefonica.ssi.web.datamodel.MovimentosDataModel;

@Model
@ViewScoped
public class MovimentosBean extends AbstractManagedBean{

	/**
	 *
	 */
	private static final long serialVersionUID = -7505728611934356987L;

	private MovimentosDataModel dataModel;

	private DemandasRegulatorio demanda;

	@EJB
	private MovimentoFacade movimentoService;

	public void listenerDemanda(@Observes DemandasRegulatorio demanda){
		this.demanda = demanda;
		Map<String, Object> filtros = new HashMap<String, Object>();
		dataModel = new MovimentosDataModel(movimentoService, filtros, demanda);
	}

	public List<Movimento> getMovimentos(){
		if(demanda!=null){
			return movimentoService.getMovimentosPorDemanda(demanda);
		}
		else{
			return new ArrayList<Movimento>();
		}
	}

	public MovimentosDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(MovimentosDataModel dataModel) {
		this.dataModel = dataModel;
	}
}
