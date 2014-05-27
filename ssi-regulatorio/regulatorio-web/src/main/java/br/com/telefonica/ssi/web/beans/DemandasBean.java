
package br.com.telefonica.ssi.web.beans;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Procedencia;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Emails;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.LogService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.SendMailService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.facade.DemandaServiceFacade;
import br.com.telefonica.ssi.web.controller.IndexMB;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@Named
@SessionScoped
public class DemandasBean extends AbstractManagedBean {

	private static final long serialVersionUID = 4468503734082103168L;

	@EJB
	private DemandaServiceFacade facadeDemanda;

	@EJB
	private SendMailService sendMail;

	private DemandasRegulatorio demanda;

	private String novoEmail;

	private String tabselecionada;

	@EJB
	private LogService logger;

	@PostConstruct
	public void init() {
		tabselecionada = "tababertura";

		// Verifica se o request mandou alguma tab para ser selecionada por default. Se negativo, mantém a tab original
		String tab = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tab");

		if(tab !=null && !tab.equals("")){
			tabselecionada = tab;
		}
	}

	@Inject
	Event<DemandasRegulatorio> eventoDemanda;

	public void areaAlterada(ValueChangeEvent evt){
		demanda = facadeDemanda.alteraAreaDemandaPorIdArea(Long.toString(((Areas)evt.getNewValue()).getId()),demanda);
	}

	public DemandasRegulatorio getDemanda() {
		return demanda;
	}

	public void setDemanda(DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}

	public void salvarComoRascunho(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();

		if(demanda.getPrazo().before(Calendar.getInstance().getTime())){
			index.setMsgpanel("Prazo anterior à data atual!");

			index.setPanelexibeerro(true);
		}
		else{
			facadeDemanda.salvarComoRascunho(demanda);

			logger.salvarLog(demanda, RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado(), "Nova demanda criada e salva como rascunho.");

			index.setPanelexibesucesso(true);
			index.setMsgpanel("Demanda salva como rascunho!");
			eventoDemanda.fire(demanda);
		}
	}

	public void encaminhar(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();

		if(demanda.getPrazo().before(Calendar.getInstance().getTime())){
			index.setMsgpanel("Prazo anterior à data atual!");
			index.setPanelexibeerro(true);
		}
		else{
			this.demanda = facadeDemanda.encaminhar(demanda);
			index.setMsgpanel("Demanda encaminhada!");
			index.setPanelexibesucesso(true);

			logger.salvarLog(demanda, RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado(), "Demanda encaminhada para análise preliminiar.");


			sendMail.notificarResponsavel("Nova demanda regulatória número"
					+ " "+demanda.getNumeroDemanda()+" criada!", "Nova demanda regulatória criada.", demanda.getNumeroDemanda(), demanda);

			eventoDemanda.fire(demanda);
		}
	}

	public List<Procedencia> getProcedenciasCategoriaDaDemanda(){
		return facadeDemanda.getProcedenciasCategoriaDaDemanda(demanda.getCategoria());
	}

	public List<Areas> getAreasSolicitante(){
		return facadeDemanda.getPessoaService().retornarAreasPessoa(demanda.getSolicitante());
	}

	public List<Pessoas> getPessoasAreaOrigem(){
		return facadeDemanda.getAreasService().recuperarPessoasArea(demanda.getOrigem());
	}

	public String getEmailSolicitante(){
		return facadeDemanda.getEmailSolicitante(demanda.getSolicitante());
	}

	public boolean isSolicitantePossuiEmail(){
		return facadeDemanda.solicitantePossuiEmail(demanda.getSolicitante());
	}

	public void mudaSolicitante(ValueChangeEvent evt){
		Pessoas pessoa = facadeDemanda.getPessoaService().recuperarUnico(((Pessoas)evt.getNewValue()).getId());
		demanda.setSolicitante(pessoa);
	}

	public String getNovoEmail() {
		return novoEmail;
	}

	public void setNovoEmail(String novoEmail) {
		this.novoEmail = novoEmail;
	}

	public void salvaNovoEmail(){
		Emails email = new Emails();
		email.setCnmemail(this.novoEmail);
		Pessoas pessoa = facadeDemanda.getPessoaService().recuperarUnico(demanda.getSolicitante().getId());
		email.setEmailspessoa(pessoa);
		facadeDemanda.salvaNovoEmailSolicitante(email);
		this.novoEmail = null;
	}

	public void salvaDemanda(){
		facadeDemanda.salvaDemanda(this.demanda);

		eventoDemanda.fire(demanda);
	}

	public void listenerDemanda(@Observes DemandasRegulatorio demanda){
		this.demanda = demanda;
	}

	public String getTabselecionada() {
		return tabselecionada;
	}

	public void setTabselecionada(String tabselecionada) {
		this.tabselecionada = tabselecionada;
	}
}