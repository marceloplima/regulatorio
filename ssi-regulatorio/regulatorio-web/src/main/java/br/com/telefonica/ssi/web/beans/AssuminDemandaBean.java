package br.com.telefonica.ssi.web.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.telefonica.ssi.web.beans.popupBeans.AbstractPopupManagedBean;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@ManagedBean
@ViewScoped
public class AssuminDemandaBean extends AbstractPopupManagedBean{

	/**
	 *
	 */
	private static final long serialVersionUID = -244840298336601945L;

	@Override
	public void execute() {
		if(this.demanda != null){
			this.demanda.setEncarregado(getLogado());
			this.demanda.setStatus(facadeDemanda.getStatusService().findByName("ANÁLISE TÉCNICA"));
			facadeDemanda.salvaDemanda(demanda);

			logger.salvarLog(demanda, RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado(), "Demanda assumida por "+demanda.getEncarregado().getCnmnome()+" e enviada para "+demanda.getStatus().getDescricao());

			exibeMensagemSucesso("Operacão realizada com sucesso!");

			eventoDemanda.fire(demanda);

			mensageria.notificaSolicitante("Demanda assumida por "+demanda.getEncarregado().getCnmnome(), "Demanda sob nova responsabilidade.", demanda.getNumeroDemanda(), demanda);
		}
	}
}
