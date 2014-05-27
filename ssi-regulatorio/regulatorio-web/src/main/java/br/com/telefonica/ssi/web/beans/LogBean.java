package br.com.telefonica.ssi.web.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Log;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.LogService;

@Named
@RequestScoped
public class LogBean extends AbstractManagedBean {

	/**
	 *
	 */
	private static final long serialVersionUID = -8650050662695178071L;

	@EJB
	private LogService logService;

	private DemandasRegulatorio demanda;

	public void listenerDemanda(@Observes DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}

	public String getAcompanhamento() {
		StringBuilder sb = new StringBuilder();

		if (demanda != null && demanda.getId() != null) {
			List<Log> entradas = logService.logsPorDemanda(demanda);
			for (Log log : entradas) {
				sb.append(log.toString());
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public DemandasRegulatorio getDemanda() {
		return demanda;
	}

	public void setDemanda(DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}
}
