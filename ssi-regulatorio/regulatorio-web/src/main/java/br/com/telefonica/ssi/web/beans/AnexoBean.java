package br.com.telefonica.ssi.web.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import org.richfaces.event.FileUploadEvent;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.domain.AnexosRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.AnexoService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.DemandaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.legado.DemandasServiceInt;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@Named
@SessionScoped
public class AnexoBean extends AbstractManagedBean{

	/**
	 *
	 */
	private static final long serialVersionUID = -2655127495967420529L;

	private DemandasRegulatorio demanda;

	//private Event<DemandasRegulatorio> eventoDemanda;

	@EJB
	private AnexoService anexoService;

	@EJB
	private DemandaService demandaService;

	public void listenerDemanda(@Observes DemandasRegulatorio demanda){
		if(demanda.getId()!=null){
			this.demanda = demandaService.findById(demanda.getId());
		}
		else{
			this.demanda = demanda;
		}
	}

	public DemandasRegulatorio getDemanda() {
		return demanda;
	}

	public void setDemanda(DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}

	public void fileListener(FileUploadEvent evt) throws Exception{
		//UploadedFile upload = evt.getUploadedFile();
		//ParametrosSistema param = new ParametrosSistema();

		//String filename = param.recuperaCaminhoUploads()+File.separator+upload.getName();

		//File file = new File(filename);


		AnexosRegulatorio anexo = new AnexosRegulatorio();
		anexo.setAutor(RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado());
	}

	List<AnexosRegulatorio> buscarAnexos(){
		return anexoService.buscarAnexos(demanda);
	}

}
