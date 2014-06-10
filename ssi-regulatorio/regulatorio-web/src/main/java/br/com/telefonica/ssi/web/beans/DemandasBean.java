
package br.com.telefonica.ssi.web.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.GruposModulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.LogService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.SendMailService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.GruposModulosInt;
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

	@EJB
	private GruposModulosInt grupoModuloService;

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

		List<String> mensagens = new ArrayList<String>();

		if(demanda.getPrazo()==null){
			mensagens.add("Prazo não informado!");
			index.setMsgspanel(mensagens);
			index.setPanelexibeerro(true);
			return;
		}
		if(demanda.getPrazo().before(new Date())){
			mensagens.add("Prazo anterior ou igual à data atual!");
			index.setMsgspanel(mensagens);
			index.setPanelexibeerro(true);
			return;
		}
		if(demanda.getQuestao()==null||demanda.getQuestao().equals("")){
			mensagens.add("Questão não informada!");
			index.setMsgspanel(mensagens);
			index.setPanelexibeerro(true);
			return;
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

		if(demanda.getPrazo() == null || demanda.getPrazo().before(Calendar.getInstance().getTime()) ||
				demanda.getQuestao()==null || demanda.getQuestao().equals("")){
			List<String> messages = new ArrayList<String>();
			if(demanda.getPrazo() == null || demanda.getQuestao() == null){
				messages.add("Os campos prazo, e questão são obrigatórios!");
			}
			else if(demanda.isVencido()){
				messages.add("Prazo anterior ou igual à data atual!");
			}
			else if(demanda.getQuestao()==null || demanda.getQuestao().equals("")){
				messages.add("Campo questão não preenchido!");
			}
			index.setMsgspanel(messages);
			index.setPanelexibeerro(true);
		}
		else{
			GruposModulos gmodulos = grupoModuloService.recuperarPorNome(demanda.getCategoria().getDescricao());

			Pessoas responsavel = null;

			List<Pessoas> pessoas = gmodulos.getGruposmodulospessoas();

			Iterator<Pessoas> it = pessoas.iterator();

			while(it.hasNext()){
				Pessoas p = it.next();
				if(p.getCargo().getCnmcargo().equalsIgnoreCase("gestor")){
					responsavel = p;
					break;
				}
			}

//			if(responsavel == null){
//				List<String> messages = new ArrayList<String>();
//				messages.add("Esta categoria não possui um responsável cadastrado!");
//				index.setMsgspanel(messages);
//				index.setPanelexibeerro(true);
//				return;
//			}
			demanda.setEncarregado(responsavel);

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