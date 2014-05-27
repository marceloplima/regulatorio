package br.com.telefonica.ssi.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Regionais;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.RegionaisInt;

@ManagedBean
@ViewScoped
public class RegionaisMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4692394757666852025L;

	@EJB
	private RegionaisInt regionalint;
	
	private List<Regionais> regionais;
		
	public RegionaisMB() {
	}
	
	public List<Regionais> getRegionais() {
		
		if(regionais==null){
			regionais = regionalint.recuperar();
		}
		
		return regionais;
	}

	public void setRegionais(List<Regionais> regionais) {
		this.regionais = regionais;
	}


}
