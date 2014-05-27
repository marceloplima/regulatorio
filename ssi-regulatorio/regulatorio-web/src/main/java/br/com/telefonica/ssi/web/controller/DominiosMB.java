package br.com.telefonica.ssi.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Dominios;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.DominiosInt;

@ManagedBean
@RequestScoped
public class DominiosMB{

	@EJB
	private DominiosInt dominiosproxy;
	
	private Dominios dominio = new Dominios();
	private List<Dominios> dominios = new ArrayList<Dominios>();
	
	public Dominios getDominio() {
		return dominio;
	}
	public void setDominio(Dominios dominio) {
		this.dominio = dominio;
	}
	public List<Dominios> getDominios() {
		return dominios;
	}
	public void setDominios(List<Dominios> dominios) {
		this.dominios = dominios;
	}
	
	@PostConstruct
	public void init(){
		dominios = recuperar();
	}
	
	public List<Dominios> recuperar(){
		if(dominios==null || dominios.size()<=1){
			dominios = dominiosproxy.recuperar();
			return dominios;
		}
		else
			return dominios;
	}

}
