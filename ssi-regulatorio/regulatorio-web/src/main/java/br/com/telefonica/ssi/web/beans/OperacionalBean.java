package br.com.telefonica.ssi.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.bean.ManagedBean;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseOperacional;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoFacade;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@ManagedBean
@SessionScoped
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

	private boolean mostrarTelaVisualizaAnaliseOperacional = false;
	private MovimentoAnaliseOperacional movimentoAnaliseOperacionalSelecionada = new MovimentoAnaliseOperacional();

	private List<MovimentoAnaliseOperacional> movimentosAnaliseOperacional = new ArrayList<MovimentoAnaliseOperacional>();

	private DemandasBean demandasmb;

	@PostConstruct
	public void init(){

		demandasmb = RecuperadorInstanciasBean.recuperarInstanciaDemandasBean();

		if(demanda == null){
			demanda = demandasmb.getDemanda();
		}

		if(this.demanda!=null && this.demanda.getId() != null){
			atualizaMovimentosAnaliseOperacional();
		}
	}

	public void atualizaMovimentosAnaliseOperacional() {
		if(demanda.getId()!=null){
			movimentosAnaliseOperacional = movimentoService.analisesOperacionaisPorDemanda(demanda);
		}
	}

	public void visualizaAnaliseOperacional(MovimentoAnaliseOperacional movimentoAnaliseOperacional){
		movimentoAnaliseOperacionalSelecionada = movimentoAnaliseOperacional;
		mostrarTelaVisualizaAnaliseOperacional = true;
	}

	public void fecharTelaVisualizaAnaliseOperacional(){
		mostrarTelaVisualizaAnaliseOperacional = false;
	}

	public void listenerDemanda(@Observes DemandasRegulatorio demanda){
		this.demanda = demanda;
		atualizaMovimentosAnaliseOperacional();
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

	public boolean isMostrarTelaVisualizaAnaliseOperacional() {
		return mostrarTelaVisualizaAnaliseOperacional;
	}

	public void setMostrarTelaVisualizaAnaliseOperacional(
			boolean mostrarTelaVisualizaAnaliseOperacional) {
		this.mostrarTelaVisualizaAnaliseOperacional = mostrarTelaVisualizaAnaliseOperacional;
	}

	public MovimentoAnaliseOperacional getMovimentoAnaliseOperacionalSelecionada() {
		return movimentoAnaliseOperacionalSelecionada;
	}

	public void setMovimentoAnaliseOperacionalSelecionada(
			MovimentoAnaliseOperacional movimentoAnaliseOperacionalSelecionada) {
		this.movimentoAnaliseOperacionalSelecionada = movimentoAnaliseOperacionalSelecionada;
	}

	public List<MovimentoAnaliseOperacional> getMovimentosAnaliseOperacional() {
		return movimentosAnaliseOperacional;
	}

	public void setMovimentosAnaliseOperacional(
			List<MovimentoAnaliseOperacional> movimentosAnaliseOperacional) {
		this.movimentosAnaliseOperacional = movimentosAnaliseOperacional;
	}



}
