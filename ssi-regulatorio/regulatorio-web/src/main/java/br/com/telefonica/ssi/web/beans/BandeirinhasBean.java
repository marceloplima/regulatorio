
package br.com.telefonica.ssi.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.DemandaService;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@SuppressWarnings("all")
@ManagedBean
@ViewScoped
public class BandeirinhasBean{

	private static final long serialVersionUID = 1L;

	@EJB
	private DemandaService demandaService;

	List<DemandasRegulatorio> demandasMinhas;
	List<DemandasRegulatorio> demandasComigo;
	Pessoas pessoaLogada;


	@PostConstruct
	public void init(){
		pessoaLogada = RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado();
		atualizarBandeirinhas();
	}

	private void atualizarBandeirinhas(){
		demandasMinhas = demandaService.getMinhasDemandas(pessoaLogada);
		demandasComigo = demandaService.getDemandasEncarregado(pessoaLogada);
	}

	public void listenerDemanda(@Observes DemandasRegulatorio demanda){
		atualizarBandeirinhas();
	}

	public int getQtdDemandasCriadasPelaPessoaLogada(){
		if(demandasMinhas == null){
			return 0;
		}

		return demandasMinhas.size();
	}

	public int getQtdDemandasEncarregado(){
		if(demandasComigo == null){
			return 0;
		}

		return demandasComigo.size();
	}

	public List<DemandasRegulatorio> getDemandasEncarregado(){

		if(demandasComigo == null){
			return new ArrayList<DemandasRegulatorio>();
		}

		return demandasComigo;

	}

	public List<DemandasRegulatorio> getDemandasCriadasPelaPessoaLogada(){

		if(demandasMinhas == null){
			return new ArrayList<DemandasRegulatorio>();
		}

		return demandasMinhas;

	}

}