package br.com.telefonica.ssi.web.beans.popupBeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.PopupDemanda;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.LogService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.SendMailService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.facade.DemandaServiceFacade;
import br.com.telefonica.ssi.web.controller.IndexMB;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

public abstract class AbstractPopupManagedBean extends AbstractManagedBean {

	@EJB
	protected DemandaServiceFacade facadeDemanda;

	@EJB
	protected LogService logger;

	@EJB
	protected SendMailService mensageria;

	/**
	 *
	 */
	private static final long serialVersionUID = 8878537860110570240L;

	protected boolean mostraPopup;

	protected DemandasRegulatorio demanda;

	@Inject
	protected Event<DemandasRegulatorio> eventoDemanda;

	public Pessoas getLogado() {
		return RecuperadorInstanciasBean.recuperarInstanciaLoginBean()
				.recuperarPessoaLogado();
	}

	public boolean isShowPopup() {
		return mostraPopup;
	}

	public void setShowPopup(boolean showPopup) {
		this.mostraPopup = showPopup;
	}

	public void setHidden() {
		this.demanda = null;
	}

	public void listenerDemanda(@Observes @PopupDemanda DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}

	public DemandasRegulatorio getDamanda() {
		return demanda;
	}

	public void setDamanda(DemandasRegulatorio damanda) {
		this.demanda = damanda;
	}

	public abstract void execute();

	public boolean validaDemanda() {
		if (demanda != null) {
			List<String> messages = new ArrayList<String>();
			if (demanda.getTipoDemanda() == null) {
				exibeMensagemDeErro("Assunto não informado! ");
				this.mostraPopup = false;
				return false;
			}
			if (demanda.getAreaRegional() == null) {
				messages.add("Área regional não informada! ");
				exibeMensagemDeErro("Area regional não informada! ");
				this.mostraPopup = false;
				return false;
			}
			if (demanda.getDocumentoOrigem() == null) {
				exibeMensagemDeErro("Documento de origem não informado! ");
				this.mostraPopup = false;
				return false;
			}
			if (demanda.getTipoRede() == null) {
				exibeMensagemDeErro("Tipo de rede não informada! ");
				this.mostraPopup = false;
				return false;
			}
			if (demanda.getUfs() == null || demanda.getUfs().isEmpty()) {
				exibeMensagemDeErro("UFs não informadas! ");
				this.mostraPopup = false;
				return false;
			} else {
				return true;
			}
		}
		else{
			return false;
		}
	}

	public void exibeMensagemSucesso(String msg) {
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		index.setMsgpanel(msg);
		index.setPanelexibesucesso(true);
	}

	public void exibeMensagemDeErro(String msg) {
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		List<String> messages = new ArrayList<String>();
		messages.add(msg);
		index.setMsgspanel(messages);
		index.setPanelexibeerro(true);
	}
}
