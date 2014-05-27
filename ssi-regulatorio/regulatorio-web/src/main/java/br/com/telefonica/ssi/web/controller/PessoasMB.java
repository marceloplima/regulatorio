package br.com.telefonica.ssi.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.PessoasInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.legado.DemandasInt;
import br.com.telefonica.ssi.web.beans.DemandasBean;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@ManagedBean
@ViewScoped
public class PessoasMB implements Serializable {

	private static final long serialVersionUID = -4973839265386772445L;
	
	@EJB
	private PessoasInt pessoaint;
	
	//@EJB
	//private DemandasInt demandaInt;
	
	private List<DemandasRegulatorio> demandasMinhas = new ArrayList<DemandasRegulatorio>();
	private List<DemandasRegulatorio> demandasComigo = new ArrayList<DemandasRegulatorio>();
	
	private DemandasBean demandasmb = RecuperadorInstanciasBean.recuperarInstanciaDemandasBean();
	
	public PessoasMB() {}
	
	@PostConstruct
	public void init() {
		//atualizarDemandasMinhas(demandasmb.recuperarPessoaLogada());
		//atualizarDemandasComigo(demandasmb.recuperarPessoaLogada());
	}
	
	public List<String> searchbyName(String autobusca){
		return pessoaint.recuperarAutocomplete(autobusca.trim());
	}
	
	public void atualizarDemandasMinhas(Pessoas pessoaLogada){
		System.out.println("ATUALIZA MINHAS DEMANDAS");
		demandasMinhas = new ArrayList<DemandasRegulatorio>();
		//demandasMinhas.addAll(demandaInt.recuperarDemandasCriadasPeloAutor(pessoaLogada));
	}
	
	public void atualizarDemandasComigo(Pessoas pessoaLogada){
		System.out.println("ATUALIZA DEMANDAS COMIGO");
		demandasComigo = new ArrayList<DemandasRegulatorio>();
		//demandasComigo.addAll(demandaInt.recuperarDemandasEstaoComPessoaLogada(pessoaLogada));
	}
		
//	//Setters e Getters
	public List<DemandasRegulatorio> getDemandasMinhas() {
		return demandasMinhas;
	}

	public void setDemandasMinhas(List<DemandasRegulatorio> demandasMinhas) {
		this.demandasMinhas = demandasMinhas;
	}

	public List<DemandasRegulatorio> getDemandasComigo() {
		return demandasComigo;
	}

	public void setDemandasComigo(List<DemandasRegulatorio> demandasComigo) {
		this.demandasComigo = demandasComigo;
	}
	

	
}
