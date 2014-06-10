package br.com.telefonica.ssi.web.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Model;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoFacade;
import br.com.telefonica.ssi.web.datamodel.MovimentosDataModel;

@Model
@SessionScoped
public class MovimentosBean extends AbstractManagedBean{

	/**
	 *
	 */
	private static final long serialVersionUID = -7505728611934356987L;

	private MovimentosDataModel dataModel;

	private DemandasRegulatorio demanda;

	List<Movimento> movimentos = new ArrayList<Movimento>();

	@EJB
	private MovimentoFacade movimentoService;

	@PostConstruct
	private void init(){
		if(demanda != null && demanda.getId() != null){
			carregarMovimentos();
		}

	}

	private void carregarMovimentos() {
		this.movimentos = movimentoService.getMovimentosPorDemanda(demanda);
	}

	public void listenerDemanda(@Observes DemandasRegulatorio demanda){
		this.demanda = demanda;
		carregarMovimentos();
		Map<String, Object> filtros = new HashMap<String, Object>();
		dataModel = new MovimentosDataModel(movimentoService, filtros, demanda);
	}

	public MovimentosDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(MovimentosDataModel dataModel) {
		this.dataModel = dataModel;
	}

	public void setMovimentos(List<Movimento> movimentos) {
		this.movimentos = movimentos;
	}


}
