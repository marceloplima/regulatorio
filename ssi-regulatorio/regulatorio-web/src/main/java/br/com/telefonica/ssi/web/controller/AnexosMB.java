package br.com.telefonica.ssi.web.controller;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.NovoAnexo;
import br.com.telefonica.ssi.regulatorio.commom.domain.AnexosRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.TipoAnexo;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.legado.AnexosInt;
import br.com.telefonica.ssi.web.beans.DemandasBean;
import br.com.telefonica.ssi.web.utils.ParametrosSistema;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@ManagedBean
@ViewScoped
public class AnexosMB implements Serializable{

	private static final long serialVersionUID = -2633283464281442362L;

	@EJB
	private AnexosInt anexoint;

	@Inject @NovoAnexo
	Event<DemandasRegulatorio> eventoDemanda;

	/*@EJB
	private TiposAnexosInt tipoanexoint;*/

	private DemandasBean demandasmb = RecuperadorInstanciasBean.recuperarInstanciaDemandasBean();

	private File file;
    private ParametrosSistema parametrosistema = new ParametrosSistema();

    private AnexosRegulatorio anexo;
    private boolean exibeconfirmacaoapagar;

	// Usado na busca
	private Map<String,Object>filtros = new LinkedHashMap<String,Object>();

	public List<TipoAnexo> recuperarTiposAnexos(){
		//return tipoanexoint.recuperarTiposAnexos();
		return new ArrayList<TipoAnexo>();
	}

	public void fecharPopupConfirmacaoRemoverAnexo(){
		exibeconfirmacaoapagar = false;
	}

	public void preRemover(AnexosRegulatorio anexo){
		this.anexo = anexo;
		exibeconfirmacaoapagar = true;
	}

	public void removerAnexo(){
		try{
			File newfile = new File(parametrosistema.recuperaCaminhoUploads()+"/"+anexo.getCaminhoArquivo());
			newfile.delete();
			DemandasRegulatorio demandasRegulatorio = anexo.getDemanda();
			demandasRegulatorio.getAnexos().remove(anexo);
			anexoint.remover(anexo);
			eventoDemanda.fire(demandasRegulatorio);
			anexo = new AnexosRegulatorio();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		fecharPopupConfirmacaoRemoverAnexo();

	}

	public AnexosDataModel getDataModel(){
		Map<String,Object> filtros = new LinkedHashMap<String,Object>();
		//filtros.put("demanda", demandasmb.getDemandas());
		this.filtros = filtros;
		return new AnexosDataModel(anexoint, filtros);
	}

	public Map<String,Object> getFiltros() {
		return filtros;
	}

	public void setFiltros(Map<String,Object> filtros) {
		this.filtros = filtros;
	}

	public DemandasBean getDemandasmb() {
		return demandasmb;
	}

	public void setDemandasmb(DemandasBean demandasmb) {
		this.demandasmb = demandasmb;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ParametrosSistema getParametrosistema() {
		return parametrosistema;
	}

	public void setParametrosistema(ParametrosSistema parametrosistema) {
		this.parametrosistema = parametrosistema;
	}

	public AnexosRegulatorio getAnexo() {
		return anexo;
	}

	public void setAnexo(AnexosRegulatorio anexo) {
		this.anexo = anexo;
	}

	public boolean isExibeconfirmacaoapagar() {
		return exibeconfirmacaoapagar;
	}

	public void setExibeconfirmacaoapagar(boolean exibeconfirmacaoapagar) {
		this.exibeconfirmacaoapagar = exibeconfirmacaoapagar;
	}

}
