package br.com.telefonica.ssi.web.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoService;

@Named
@ViewScoped
public class EventosBean extends AbstractManagedBean{

	/**
	 *
	 */
	private static final long serialVersionUID = 8312455313913706230L;

	@EJB
	private MovimentoService movimentoService;

	private DemandasRegulatorio demanda;


	public void listenerDemanda(@Observes DemandasRegulatorio demanda){
		this.demanda = demanda;
	}

	public List<Movimento> getMovimentacoes(){
		return movimentoService.todosPorDemanda(demanda);
	}
}
