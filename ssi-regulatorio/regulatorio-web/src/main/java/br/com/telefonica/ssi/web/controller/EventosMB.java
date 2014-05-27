package br.com.telefonica.ssi.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class EventosMB implements java.io.Serializable{

//	private static final long serialVersionUID = -4717357800384649130L;
//
//	@EJB
//	private EventosInt eventosint;
//	
//	private EventosRegulatorio evento;
//	
//	private List<EventosRegulatorio> eventos;
//	private List<EventosRegulatorio> eventoschecked = new ArrayList<EventosRegulatorio>();
//	
//	private DemandasBean demandasmb = RecuperadorInstanciasBean.recuperarInstanciaDemandasBean();
//	
//	private boolean exibepopupdetev_6 = false;
//	
//	//Controlador de exibi��o do select de a��es (disabled/enabled)
//	private boolean ocultaacoes;
//	
//	private String acao; // a��o selecionada no combo de A��es
//	
//	@PostConstruct
//    public void init() {
//		
//		setEvento(new EventosRegulatorio());
//		
//		// A��es vem inicialmente, ocultas
//		ocultaacoes = true;
//		
//		setEventos(new ArrayList<EventosRegulatorio>());
//		setEventoschecked(new ArrayList<EventosRegulatorio>());
//    }
//	
//	public void selecionarCheckbox(EventosRegulatorio obj){
////		if(eventoschecked.contains(obj)){
////			obj.setChecked(false);
////			eventoschecked.remove(obj);
////		}else{
////			obj.setChecked(true);
////			eventoschecked.add(obj);
////		}
//		
//		// Verifica se apenas 1 checkbox est� checado. Caso positivo, habilita A��es
//		setOcultaacoes(true);
//		if(eventoschecked.size() == 1){
//			setOcultaacoes(false);
//		}
//	}
//	
//	public void fecharPopups(){
//		setExibepopupdetev_6(false);
//	}
//	
//	public EventosDataModel getDataModel() {
//		setOcultaacoes(true);
//		acao = "0"; // reseta o combobox de a��es
//		eventoschecked = new ArrayList<EventosRegulatorio>();
//		
//        //return new EventosDataModel(eventosint, demandasmb.getDemandas());
//		return null;
//    }
//	
//	public void abrirPopups(EventosRegulatorio evento){
//		
//		// Primeiro seta todos os popups pra fechados (rendered=false)
//		fecharPopups();
//		
//		exibepopupdetev_6 = true;
////		evento.setErc(eventosint.recuperarEventosRegulatorioConcluir(evento));
//		
//		setEvento(evento);
//	}
//
//	public boolean isOcultaacoes() {
//		return ocultaacoes;
//	}
//
//	public void setOcultaacoes(boolean ocultaacoes) {
//		this.ocultaacoes = ocultaacoes;
//	}
//
//	public String getAcao() {
//		return acao;
//	}
//
//	public void setAcao(String acao) {
//		this.acao = acao;
//	}
//
//	public List<EventosRegulatorio> getEventos() {
//		return eventos;
//	}
//
//	public void setEventos(List<EventosRegulatorio> eventos) {
//		this.eventos = eventos;
//	}
//
//	public List<EventosRegulatorio> getEventoschecked() {
//		return eventoschecked;
//	}
//
//	public void setEventoschecked(List<EventosRegulatorio> eventoschecked) {
//		this.eventoschecked = eventoschecked;
//	}
//
//	public EventosRegulatorio getEvento() {
//		
//		return evento;
//	}
//
//	public void setEvento(EventosRegulatorio evento) {
//			this.evento = evento;
//	}
//
//	public DemandasBean getDemandasmb() {
//		return demandasmb;
//	}
//
//	public void setDemandasmb(DemandasBean demandasmb) {
//		this.demandasmb = demandasmb;
//	}
//
//	public boolean isExibepopupdetev_6() {
//		return exibepopupdetev_6;
//	}
//
//	public void setExibepopupdetev_6(boolean exibepopupdetev_6) {
//		this.exibepopupdetev_6 = exibepopupdetev_6;
//	}

}
