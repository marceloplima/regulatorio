package br.com.telefonica.ssi.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Ufs;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.UfsInt;

@ManagedBean
@ViewScoped
public class UfsMB implements Serializable {

	private static final long serialVersionUID = 5613637750871746280L;
	
	@EJB
	private UfsInt ufsint;
	
	private List<Ufs> ufs;

	public List<Ufs> getUfs() {
		
		if(ufs==null){
			ufs = ufsint.recuperar();
		}
		return ufs;
	}

	public void setUfs(List<Ufs> ufs) {
		this.ufs = ufs;
	}
	
	
	
	

}
