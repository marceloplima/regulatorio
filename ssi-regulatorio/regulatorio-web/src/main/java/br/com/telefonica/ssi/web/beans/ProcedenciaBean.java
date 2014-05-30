
package br.com.telefonica.ssi.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.CategoriaRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Procedencia;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.CategoriaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ProcedenciaService;

@ManagedBean
@ViewScoped
public class ProcedenciaBean extends AbstractManagedBean {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProcedenciaService procedenciaService;

	@EJB
	private CategoriaService categoriaService;

	private List<Procedencia> procedencias = new ArrayList<Procedencia>();
	private Procedencia procedenciaSelecionada = new Procedencia();
	private List<CategoriaRegulatorio> categorias = new ArrayList<CategoriaRegulatorio>();
	private boolean mostrarTelaProcedencia = false;
	private boolean mostrarTelaConfirmacaoProcedencia = false;
	private boolean mostrarModalConfirmacaoDesativacao = false;

	@PostConstruct
	public void init(){
		recuperarProcedencias();
	}

	public void novaProcedencia(){
		recuperarCategorias();
		procedenciaSelecionada = new Procedencia();
		mostrarTelaProcedencia = true;
	}

	public void setaCategoria(){
		System.out.println(procedenciaSelecionada.getCategoria());
	}

	private void recuperarProcedencias(){
		procedencias = procedenciaService.findAll("descricao");
	}

	private void recuperarCategorias(){
		categorias = categoriaService.findAll("descricao");
	}

	public void visualizarProcedencia(Procedencia procedencia){
		recuperarCategorias();

		procedenciaSelecionada = procedencia;
		mostrarTelaProcedencia = true;
	}

	public void desativar(){
		procedenciaSelecionada.setAtivo(false);
		salvar();
		fecharTelaConfirmacaoDaDesativacao();
	}

	public void fecharTelaProcedencia(){
		mostrarTelaProcedencia = false;
	}

	public void salvar(){
		procedenciaService.save(procedenciaSelecionada);
		fecharTelaProcedencia();
	}

	public void mostrarTelaConfirmacaoDaDesativacao(Procedencia procedencia){
		procedenciaSelecionada = procedencia;
		mostrarModalConfirmacaoDesativacao = true;
	}

	public void fecharTelaConfirmacaoDaDesativacao(){
		mostrarModalConfirmacaoDesativacao = false;
	}

	/*Getter e Setter*/
	public List<Procedencia> getProcedencias() {
		return procedencias;
	}

	public boolean isMostrarTelaProcedencia() {
		return mostrarTelaProcedencia;
	}

	public void setMostrarTelaProcedencia(boolean mostrarTelaProcedencia) {
		this.mostrarTelaProcedencia = mostrarTelaProcedencia;
	}

	public boolean isMostrarTelaConfirmacaoProcedencia() {
		return mostrarTelaConfirmacaoProcedencia;
	}

	public void setMostrarTelaConfirmacaoProcedencia(
			boolean mostrarTelaConfirmacaoProcedencia) {
		this.mostrarTelaConfirmacaoProcedencia = mostrarTelaConfirmacaoProcedencia;
	}

	public void setProcedencias(List<Procedencia> procedencias) {
		this.procedencias = procedencias;
	}

	public Procedencia getProcedenciaSelecionada() {
		return procedenciaSelecionada;
	}

	public void setProcedenciaSelecionada(Procedencia procedenciaSelecionada) {
		this.procedenciaSelecionada = procedenciaSelecionada;
	}

	public List<CategoriaRegulatorio> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaRegulatorio> categorias) {
		this.categorias = categorias;
	}

	public boolean isMostrarModalConfirmacaoDesativacao() {
		return mostrarModalConfirmacaoDesativacao;
	}

	public void setMostrarModalConfirmacaoDesativacao(
			boolean mostrarModalConfirmacaoDesativacao) {
		this.mostrarModalConfirmacaoDesativacao = mostrarModalConfirmacaoDesativacao;
	}

}