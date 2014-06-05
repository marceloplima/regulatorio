package br.com.telefonica.ssi.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.StatusRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ParticipantesService;
import br.com.telefonica.ssi.web.beans.DemandasBean;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@ManagedBean
@ViewScoped
public class PermissoesMB implements Serializable{

	@EJB
	private ParticipantesService participantesService;

	@EJB
	private MovimentoService movimentoService;

	private static final long serialVersionUID = 8150454244775550747L;

	private DemandasRegulatorio demanda;
	private List<Integer> statusPossiveis = new ArrayList<Integer>();
	private List<Integer> statusNaoPossiveis = new ArrayList<Integer>();

	private DemandasBean demandasmb = RecuperadorInstanciasBean.recuperarInstanciaDemandasBean();


	@PostConstruct
	public void init() {
		this.demanda = demandasmb.getDemanda();

		if(this.demanda == null){
			this.demanda = new DemandasRegulatorio();
		}

	}


	public boolean verificarCamposClassificacao(){

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_PRELIMINAR);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		if(!isResponsavelCategoria()){
			return false;
		}

		return true;

	}

	public boolean verificarSalvar(){

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_PRELIMINAR);

		if(isStatusContidoEmLista(statusPossiveis) && isResponsavelCategoria()){
			return true;
		}

		return false;

	}

	public boolean verificarEncaminhar(){

		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return true;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_RASCUNHO);

		if(isStatusContidoEmLista(statusPossiveis) && isAutor()){
			return true;
		}

		return false;
	}

	public boolean verificarCancelar(){

		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusNaoPossiveis.add(StatusRegulatorio.ID_ANALISE_PRELIMINAR);

		if(!isStatusContidoEmLista(statusNaoPossiveis)){
			return false;
		}

		return isResponsavelCategoria();

	}

	public boolean verificarPodeAssumirDemanda(){
		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_PRELIMINAR);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		return isResponsavelCategoria();

	}

	public boolean verificarRevisarPrazo(){
		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_PRELIMINAR);
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_TECNICA);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		if(this.demanda.getStatus().getId().equals(StatusRegulatorio.ID_ANALISE_PRELIMINAR)){
			return isResponsavelCategoria();
		}

		return isTecnicoEncarregado();

	}

	public boolean verificarResponderRevisaoPrazo(){
		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_REVISAO_PRAZO);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		return isAutor();
	}

	public boolean verificarDefinirTecnico(){

		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_PRELIMINAR);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		return isResponsavelCategoria();

	}

	public boolean verificarAnaliseTecnica(){

		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_PRELIMINAR);
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_TECNICA);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		if(demanda.getStatus().getId() == StatusRegulatorio.ID_ANALISE_TECNICA){
			return isTecnicoEncarregado();
		}

		return isResponsavelCategoria();

	}

	public boolean verificarFollowUp(){

		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_PRELIMINAR);
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_TECNICA);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		if(demanda.getStatus().getId() == StatusRegulatorio.ID_ANALISE_TECNICA){
			return isTecnicoEncarregado();
		}

		return isResponsavelCategoria();

	}

/*	public boolean verificarConcluir(){

		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_TECNICA);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		return isTecnicoEncarregado();

	}*/

/*	public boolean verificarNecessidadeDados(){

		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_TECNICA);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		return isTecnicoEncarregado();

	}*/

/*	public boolean verificarNecessidadeEsclarecimento(){

		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_TECNICA);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		return isTecnicoEncarregado();

	}*/

/*	public boolean verificarEmAtendimento(){

		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_TECNICA);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		return isTecnicoEncarregado();

	}*/

	public boolean verificarAcionarAreasOperacionais(){

		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_TECNICA);

		if(!isStatusContidoEmLista(statusPossiveis)){
			return false;
		}

		return isTecnicoEncarregado();

	}

	//TODO Falta ligar na ação do menu no cadssi.xhtml
	public boolean verificarAnaliseOperacional(){

		//recuperaDemanda();

		if(!isDemandaPersistida()){
			return false;
		}

		this.statusPossiveis = new ArrayList<Integer>();
		this.statusPossiveis.add(StatusRegulatorio.ID_ANALISE_OPERACIONAL);

		Pessoas pessoaLogada = recuperaPessoaLogada();
		for(Pessoas pessoa:movimentoService.pessoaAreaOperacionalDaDemanda(demanda)){
			if(pessoa.equals(pessoaLogada)){
				return true;
			}
		}

		return false;

	}




	/*-----------------------------------------*/

	private boolean isResponsavelCategoria(){
		//recuperaDemanda();
		Pessoas responsavelCategoria = participantesService.recuperarResponsavelCategoria(demanda);
		if(responsavelCategoria==null){
			return false;
		}

		return responsavelCategoria.equals(recuperaPessoaLogada());

	}

	private boolean isTecnicoEncarregado(){
		//recuperaDemanda();
		Pessoas tecnicoEncarregado = demanda.getEncarregado();

		if(tecnicoEncarregado == null){
			return false;
		}

		return tecnicoEncarregado.equals(recuperaPessoaLogada());

	}

	private boolean isAutor(){
		//recuperaDemanda();
		Pessoas autor = demanda.getAutor();

		if(autor == null){
			return false;
		}

		return autor.equals(recuperaPessoaLogada());

	}

	public Pessoas recuperaPessoaLogada(){
		return (Pessoas) RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado();
	}


	/*private void recuperaDemanda(){

		DemandasBean demandaBean = (DemandasBean) RecuperadorInstanciasBean.recuperarInstanciaDemandasBean();

		if(demandaBean!=null){
			this.demanda = demandaBean.getDemanda();
		}

	}
*/

	private boolean isStatusContidoEmLista(List<Integer> status){

		if(this.demanda.getStatus()==null){
			return false;
		}

		return status.contains(this.demanda.getStatus().getId());

	}

	private boolean isDemandaPersistida(){
		if(this.demanda == null || this.demanda.getId() == null){
			return false;
		}

		return true;
	}


	/*Getter e Setter*/

	public DemandasRegulatorio getDemanda() {
		return demanda;
	}

	public void setDemanda(DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}

}