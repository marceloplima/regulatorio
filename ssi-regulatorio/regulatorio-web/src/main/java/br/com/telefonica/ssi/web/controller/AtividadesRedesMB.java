package br.com.telefonica.ssi.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Atividades;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.AtividadesInt;
import br.com.telefonica.ssi.web.beans.DemandasBean;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@ManagedBean
@ViewScoped
public class AtividadesRedesMB implements java.io.Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8790098483452479011L;

	@EJB
	private AtividadesInt interfaceint;
	
	private Atividades obj = new Atividades();
	private List<Atividades> atividades = new ArrayList<Atividades>();
	private Areas areasolicitanteselecionada;
	private DemandasBean demandasmb;
	//private DemandasCopiadosMB copiadosmb = RecuperadorInstanciasBean.recuperarInstanciaCopiadosBean();
	
	@PostConstruct
    public void init() {
		setDemandasmb(RecuperadorInstanciasBean.recuperarInstanciaDemandasBean());
	/*	if(demandasmb.getDemandas()!=null){
			setAreasolicitanteselecionada(demandasmb.getDemandas().getAreasolicitante());
		}*/
	}

	public void setaArea(ValueChangeEvent event){
		Areas area = (Areas) event.getNewValue();
		areasolicitanteselecionada = area;
		//copiadosmb.setarPessoasGrupoCopiados(area);
	}
	
	public List<Atividades> recuperarporarea(){
		atividades = interfaceint.recuperarporarea(areasolicitanteselecionada);
		return atividades;
	}
	
	public Atividades getObj() {
		return obj;
	}

	public void setObj(Atividades obj) {
		this.obj = obj;
	}

	public List<Atividades> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividades> atividades) {
		this.atividades = atividades;
	}

	public Areas getAreasolicitanteselecionada() {
		return areasolicitanteselecionada;
	}

	public void setAreasolicitanteselecionada(Areas areasolicitanteselecionada) {
		this.areasolicitanteselecionada = areasolicitanteselecionada;
	}

	public DemandasBean getDemandasmb() {
		return demandasmb;
	}

	public void setDemandasmb(DemandasBean demandasmb) {
		this.demandasmb = demandasmb;
	}

}
