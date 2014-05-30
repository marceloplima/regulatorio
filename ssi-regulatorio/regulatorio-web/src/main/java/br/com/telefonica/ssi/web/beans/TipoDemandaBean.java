
package br.com.telefonica.ssi.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.TipoDemanda;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.TipoDemandaService;

@ManagedBean
@ViewScoped
public class TipoDemandaBean extends AbstractManagedBean {

	private static final long serialVersionUID = 1L;

	@EJB
	private TipoDemandaService tipoDemandaService;

	private TipoDemanda tipoDemandaSelecionada = new TipoDemanda();
	private List<TipoDemanda> tiposDemandas = new ArrayList<TipoDemanda>();
	private boolean mostrarTelaTipoDemanda = false;
	private boolean mostrarTelaConfirmacaoTipoDemanda = false;
	private boolean mostrarModalConfirmacaoDesativacao = false;

	@PostConstruct
	public void init(){
		recuperarTiposDemandas();
	}

	public void novaProcedencia(){
		tipoDemandaSelecionada = new TipoDemanda();
		mostrarTelaTipoDemanda = true;
	}

	private void recuperarTiposDemandas(){
		tiposDemandas = tipoDemandaService.findAll("descricao");
	}

	public void visualizarTipoDemanda(TipoDemanda tipoDemanda){

		tipoDemandaSelecionada = tipoDemanda;
		mostrarTelaTipoDemanda = true;
	}

	public void desativar(){
		tipoDemandaSelecionada.setAtivo(false);
		salvar();
		//fecharTelaConfirmacaoDaDesativacao();
	}

	public void fecharTelaProcedencia(){
		mostrarTelaTipoDemanda = false;
	}

	public void salvar(){
		tipoDemandaService.save(tipoDemandaSelecionada);
		fecharTelaProcedencia();
	}

	public void mostrarTelaConfirmacaoDaDesativacao(TipoDemanda tipoDemanda){
		tipoDemandaSelecionada = tipoDemanda;
		mostrarModalConfirmacaoDesativacao = true;
	}

	public void fecharTelaConfirmacaoDaDesativacao(){
		//mostrarModalConfirmacaoDesativacao = false;
	}

	/*Getter e Setter*/
	public List<TipoDemanda> getProcedencias() {
		return tiposDemandas;
	}

	public boolean isMostrarTelaProcedencia() {
		return mostrarTelaTipoDemanda;
	}

	public void setMostrarTelaProcedencia(boolean mostrarTelaProcedencia) {
		this.mostrarTelaTipoDemanda = mostrarTelaProcedencia;
	}

	public boolean isMostrarTelaConfirmacaoProcedencia() {
		return mostrarTelaConfirmacaoTipoDemanda;
	}

	public void setMostrarTelaConfirmacaoProcedencia(
			boolean mostrarTelaConfirmacaoProcedencia) {
		this.mostrarTelaConfirmacaoTipoDemanda = mostrarTelaConfirmacaoProcedencia;
	}

	/*
	public void setProcedencias(List<Procedencia> procedencias) {
		this.tiposDemandas = procedencias;
	}

	public Procedencia getProcedenciaSelecionada() {
		return tipoDemandaSelecionada;
	}

	public void setProcedenciaSelecionada(Procedencia procedenciaSelecionada) {
		this.tipoDemandaSelecionada = procedenciaSelecionada;
	}

	public List<CategoriaRegulatorio> getCategorias() {
		return tiposDemandas;
	}

	public void setCategorias(List<CategoriaRegulatorio> categorias) {
		this.tiposDemandas = categorias;
	}

	public boolean isMostrarModalConfirmacaoDesativacao() {
		return mostrarModalConfirmacaoDesativacao;
	}

	public void setMostrarModalConfirmacaoDesativacao(
			boolean mostrarModalConfirmacaoDesativacao) {
		this.mostrarModalConfirmacaoDesativacao = mostrarModalConfirmacaoDesativacao;
	}

*/

}