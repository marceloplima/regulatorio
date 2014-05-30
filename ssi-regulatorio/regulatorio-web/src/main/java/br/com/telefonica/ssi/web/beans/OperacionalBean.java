package br.com.telefonica.ssi.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Model;
import javax.faces.bean.ViewScoped;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseOperacional;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoFacade;

@Model
@ViewScoped
public class OperacionalBean extends AbstractManagedBean{

	/**
	 *
	 */
	private static final long serialVersionUID = 7761049828568071366L;

	@EJB
	private MovimentoFacade movimentoService;

	private Integer idMovimento;

	private DemandasRegulatorio demanda;

	private MovimentoAnaliseOperacional analise;


	public List<MovimentoAnaliseOperacional> getAnalisesOperacionais(){
		if(this.demanda!=null){
			return movimentoService.analisesOperacionaisPorDemanda(demanda);
		}
		else{
			return new ArrayList<MovimentoAnaliseOperacional>();
		}
	}

	public void listenerDemanda(@Observes DemandasRegulatorio demanda){
		this.demanda = demanda;
	}

	public Integer getIdMovimento() {
		return idMovimento;
	}

	public void setIdMovimento(Integer idMovimento) {
		this.idMovimento = idMovimento;
	}

	public String getAnaliseMovimento(){
		if(this.idMovimento!=null){
			this.analise = movimentoService.getAnaliseOperacionalPorId(this.idMovimento);
		}
		return "";
	}

	public MovimentoAnaliseOperacional getAnalise() {
		return analise;
	}

	public void setAnalise(MovimentoAnaliseOperacional analise) {
		this.analise = analise;
	}
}
