package br.com.telefonica.ssi.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Modulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Usuarios;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.ModulosInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.facade.DemandaServiceFacade;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@ManagedBean
@RequestScoped
public class IndexMB{

	@EJB
	private ModulosInt modulosint;

	@EJB
	private DemandaServiceFacade facadeDemanda;

	@Inject
	Event<DemandasRegulatorio> eventoDemanda;

	private Usuarios usuario = new Usuarios();
	private boolean panelexibesucesso;
	private boolean panelexibealerta;
	private boolean panelexibeerro;
	private String msgpanel;
	private List<String> msgspanel = new ArrayList<String>();
	private boolean renderizalistadeerros;
	private List<Modulos> modulos = new ArrayList<Modulos>();
	private boolean renderizadashboard;

	// Retorna o menu selecionado (arquivo.xhtml) ou destroi a sessão do usuário, caso saia do sistema.
	public String processarmenu(String acao){
		if(acao.equalsIgnoreCase("/interno/cadssi.xhtml")){
			Pessoas pessoa = RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado();
			DemandasRegulatorio novaDemanda = facadeDemanda.criaNovaDemanda(pessoa);
			eventoDemanda.fire(novaDemanda);
		}
		if(!acao.equals("sair")){
			return acao+"?faces-redirect=true&interno=1";
		}else{
			return "index.xhtml?deslogar=true";
		}
	}

	public void desabilitadashboard(){
		setRenderizadashboard(false);
	}
	public void habilitadashboard(){
		setRenderizadashboard(true);
	}

	public String exibeMenuLogin(){
		return "paginalogin";
	}

	public String exibeMenuTeste(){
		return "teste";
	}

	public void desabilitaSucesso(){
		this.panelexibesucesso = false;
	}

	public void desabilitaAlerta(){
		this.panelexibealerta = false;
	}

	public void desabilitaErro(){
		this.panelexibeerro = false;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public boolean isPanelexibesucesso() {
		return panelexibesucesso;
	}

	public void setPanelexibesucesso(boolean panelexibesucesso) {
		this.panelexibesucesso = panelexibesucesso;
	}

	public boolean isPanelexibealerta() {
		return panelexibealerta;
	}

	public void setPanelexibealerta(boolean panelexibealerta) {
		this.panelexibealerta = panelexibealerta;
	}

	public boolean isPanelexibeerro() {
		return panelexibeerro;
	}

	public void setPanelexibeerro(boolean panelexibeerro) {
		this.panelexibeerro = panelexibeerro;
	}

	public String getMsgpanel() {
		return msgpanel;
	}

	public void setMsgpanel(String msgpanel) {
		this.msgpanel = msgpanel;
	}

	public List<String> getMsgspanel() {
		return msgspanel;
	}

	public void setMsgspanel(List<String> msgspanel) {
		this.msgspanel = msgspanel;
	}

	public boolean isRenderizalistadeerros() {
		return renderizalistadeerros;
	}

	public void setRenderizalistadeerros(boolean renderizalistadeerros) {
		this.renderizalistadeerros = renderizalistadeerros;
	}

	public List<Modulos> getModulos() {
		modulos = modulosint.recuperar();
		return modulos;
	}

	public void setModulos(List<Modulos> modulos) {
		this.modulos = modulos;
	}

	public boolean isRenderizadashboard() {
		return renderizadashboard;
	}

	public void setRenderizadashboard(boolean renderizadashboard) {
		this.renderizadashboard = renderizadashboard;
	}

}
