package br.com.telefonica.ssi.web.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoTecnico;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoFacade;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.SendMailService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.StatusRegulatorioService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.GruposModulosInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.facade.DemandaServiceFacade;
import br.com.telefonica.ssi.web.controller.IndexMB;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@Named
@ViewScoped
public class DefinirTecnicoBean extends AbstractManagedBean{

	/**
	 *
	 */
	private static final long serialVersionUID = -3072202235312022876L;

	@EJB
	private DemandaServiceFacade facadeDemanda;

	@EJB
	private StatusRegulatorioService statusService;

	private DemandasRegulatorio demanda;

	@EJB
	private SendMailService mailService;

	@EJB
	private GruposModulosInt gruposModulosService;

	@EJB
	private MovimentoFacade movimentoService;

	@Inject
	private Event<DemandasRegulatorio> eventoDemanda;

	private Pessoas responsavel;

	private String comentario;

	public void listenerDemanda(@Observes DemandasRegulatorio demanda){
		this.demanda = demanda;
	}


	public DemandasRegulatorio getDemanda() {
		return demanda;
	}


	public void setDemanda(DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}


	public Pessoas getResponsavel() {
		return responsavel;
	}


	public void setResponsavel(Pessoas responsavel) {
		this.responsavel = responsavel;
	}

	public List<Pessoas> getTecnicosResponsaveis(){
		List<Pessoas> result = new ArrayList<Pessoas>();

		for(Pessoas p : gruposModulosService.recuperaPessoasDoGrupo(gruposModulosService.recuperarPorNome(demanda.getCategoria().getDescricao()))){
			if(p.getCargo().getCnmcargo().equalsIgnoreCase("colaborador")){
				result.add(p);
			}
		}
		return result;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void definirTecnico(){
		demanda.setEncarregado(this.responsavel);
		demanda.setStatus(statusService.findByName("ANÁLISE TÉCNICA"));

		facadeDemanda.salvaDemanda(demanda);

		Movimento movimento = new Movimento();
		MovimentoTecnico movimentoTecnico = new MovimentoTecnico();

		movimento.setAutor(RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado());
		movimento.setComentario(comentario);
		movimento.setDataHora(new Date());
		movimento.setDemanda(demanda);
		movimentoService.salvaMovimento(movimento);

		movimentoTecnico.setEncarregado(responsavel);
		movimentoTecnico.setMovimento(movimento);
		movimentoService.salvaMovimentoTecnico(movimentoTecnico);

		eventoDemanda.fire(demanda);

		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		index.setMsgpanel("Operacão realizada com sucesso!");
		index.setPanelexibesucesso(true);

		mailService.notificaSolicitante(mailService.getCorpo(demanda),mailService.getAssunto(demanda), demanda.getNumeroDemanda(), demanda);
		mailService.notificaTecnicoEncarregado(mailService.getCorpo(demanda), mailService.getAssunto(demanda), demanda.getNumeroDemanda(), demanda);
	}
}
