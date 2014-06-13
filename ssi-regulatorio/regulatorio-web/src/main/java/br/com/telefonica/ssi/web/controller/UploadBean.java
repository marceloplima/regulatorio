package br.com.telefonica.ssi.web.controller;

import java.io.File;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.NovoAnexo;
import br.com.telefonica.ssi.regulatorio.commom.domain.AnexosRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.TipoAnexo;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.AnexoService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.facade.DemandaServiceFacade;
import br.com.telefonica.ssi.web.beans.DemandasBean;
import br.com.telefonica.ssi.web.utils.ParametrosSistema;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@Named
@SessionScoped
public class UploadBean extends AbstractManagedBean{

	/**
	 *
	 */
	private static final long serialVersionUID = 1448161548423315410L;

	@EJB
	private AnexoService anexosint;

	private DemandasRegulatorio demanda;

	private DemandasBean demandasmb = RecuperadorInstanciasBean
			.recuperarInstanciaDemandasBean();
	@EJB
	private DemandaServiceFacade facadeDemanda;

	private File file;
	private ParametrosSistema parametrosistema = new ParametrosSistema();
	private boolean exibeupload;
	private String tipoupload;
	private String tabacionar;

	@Inject @NovoAnexo
	private Event<DemandasRegulatorio> eventodemanda;

	public void habilitaUpload(String tipoupload, String tabacionar) {
		exibeupload = true;
		this.setTipoupload(tipoupload);
		this.setTabacionar(tabacionar);
	}

	public void submit() {

		String sufixofile = "";

		if (file.getName().contains(".")) {
			sufixofile = file.getName().substring(
					file.getName().lastIndexOf('.'));
		}

		String novonome2 = file.getName().substring(0,
				file.getName().length() - 24);

		if (!sufixofile.equals("")) {
			novonome2 += sufixofile;
		}

		File newfile2 = new File(parametrosistema.recuperaCaminhoUploads()
				+ novonome2);
		file.renameTo(newfile2);

		incluirAnexo(novonome2, new TipoAnexo(), "NA");

	}

	public void incluirAnexo(String caminho, TipoAnexo tipoanexo,
			String versao) {


		AnexosRegulatorio anexoincluir = new AnexosRegulatorio();

		anexoincluir.setAutor(RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado());
		anexoincluir.setDemanda(demanda);
		anexoincluir.setCaminhoArquivo(caminho);

		anexoincluir.setAutor(RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado());

		anexosint.incluir(anexoincluir);

		demanda.getAnexos().add(anexoincluir);

		eventodemanda.fire(demanda);
		anexoincluir = new AnexosRegulatorio();
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

	public DemandasRegulatorio getDemanda() {
		return demanda;
	}

	public void setDemanda(DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}

	public DemandasBean getDemandasmb() {
		return demandasmb;
	}

	public void setDemandasmb(DemandasBean demandasmb) {
		this.demandasmb = demandasmb;
	}

	public boolean isExibeupload() {
		return exibeupload;
	}

	public void setExibeupload(boolean exibeupload) {
		this.exibeupload = exibeupload;
	}

	public String getTipoupload() {
		return tipoupload;
	}

	public void setTipoupload(String tipoupload) {
		this.tipoupload = tipoupload;
	}

	public String getTabacionar() {
		return tabacionar;
	}

	public void setTabacionar(String tabacionar) {
		this.tabacionar = tabacionar;
	}

	public void listenerDemanda(@Observes DemandasRegulatorio demanda){
		this.demanda = demanda;
	}
}