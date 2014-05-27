package br.com.telefonica.ssi.web.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ParticipantesMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -80406186434346219L;

	/*@EJB
	private ParticipantesInt participanteInt;	
	
	private List<Pessoas> responsaveisTecnicos;
	
	public ParticipantesMB() {}
	
	@PostConstruct
    public void init() {	
    }

	public List<String> autocompletarRespTecnico(String autobusca){
		return participanteInt.recuperarAutocompleteRespTecnico(autobusca.trim());	
	}
	
	public List<Pessoas> getResponsaveisTecnicos() {
		
		if(responsaveisTecnicos==null){			
			responsaveisTecnicos = participanteInt.recuperarResponsaveisTecnicos();			
		}
		
		return responsaveisTecnicos;
	}


	public void setResponsaveisTecnicos(List<Pessoas> responsaveisTecnicos) {
		this.responsaveisTecnicos = responsaveisTecnicos;
	}
	*/
}
