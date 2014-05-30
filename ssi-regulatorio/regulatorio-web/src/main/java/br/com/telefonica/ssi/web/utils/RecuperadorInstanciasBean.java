package br.com.telefonica.ssi.web.utils;

import javax.faces.context.FacesContext;

import br.com.telefonica.ssi.web.beans.DemandasBean;
import br.com.telefonica.ssi.web.controller.IndexMB;
import br.com.telefonica.ssi.web.controller.LoginMB;
import br.com.telefonica.ssi.web.controller.RegionaisMB;

public class RecuperadorInstanciasBean {

	// Recupera a instancia do bean representado pela classe IndexMB.java
	// Usado principalmente para acionar os alertas de valida��o
	public static IndexMB recuperarInstanciaIndexBean(){
		FacesContext facescontext = FacesContext.getCurrentInstance();
		javax.el.ValueExpression ve = facescontext.getApplication().getExpressionFactory().createValueExpression(facescontext.getELContext(),"#{indexMB}",IndexMB.class);
		IndexMB mb = (IndexMB)ve.getValue(facescontext.getELContext());

		ve = null;
		facescontext = null;

		// Reseta os pain�is de aviso do JSF/RF
		ResetadorPaineisPadrao.resetarPaineis(mb);

		return mb;
	}

	public static LoginMB recuperarInstanciaLoginBean(){
		FacesContext facescontext = FacesContext.getCurrentInstance();
		javax.el.ValueExpression ve = facescontext.getApplication().getExpressionFactory().createValueExpression(facescontext.getELContext(),"#{loginMB}",LoginMB.class);
		LoginMB mb = (LoginMB)ve.getValue(facescontext.getELContext());

		ve = null;
		facescontext = null;

		return mb;
	}

	public static DemandasBean recuperarInstanciaDemandasBean(){
		FacesContext facescontext = FacesContext.getCurrentInstance();
		javax.el.ValueExpression ve = facescontext.getApplication().getExpressionFactory().createValueExpression(facescontext.getELContext(),"#{demandasBean}",DemandasBean.class);
		DemandasBean mb = (DemandasBean)ve.getValue(facescontext.getELContext());

		ve = null;
		facescontext = null;

		return mb;
	}

	public static RegionaisMB recuperarInstanciaRegionaisBean(){
		FacesContext facescontext = FacesContext.getCurrentInstance();
		javax.el.ValueExpression ve = facescontext.getApplication().getExpressionFactory().createValueExpression(facescontext.getELContext(),"#{regionaisMB}",RegionaisMB.class);
		RegionaisMB mb = (RegionaisMB)ve.getValue(facescontext.getELContext());

		ve = null;
		facescontext = null;

		return mb;
	}

	/*public static DemandasCopiadosMB recuperarInstanciaCopiadosBean(){
		FacesContext facescontext = FacesContext.getCurrentInstance();
		javax.el.ValueExpression ve = facescontext.getApplication().getExpressionFactory().createValueExpression(facescontext.getELContext(),"#{demandasCopiadosMB}",DemandasCopiadosMB.class);
		DemandasCopiadosMB mb = (DemandasCopiadosMB)ve.getValue(facescontext.getELContext());

		ve = null;
		facescontext = null;

		return mb;
	}*/

	/*public static RotasMB recuperarInstanciaRotasBean(){
		FacesContext facescontext = FacesContext.getCurrentInstance();
		javax.el.ValueExpression ve = facescontext.getApplication().getExpressionFactory().createValueExpression(facescontext.getELContext(),"#{rotasMB}",RotasMB.class);
		RotasMB mb = (RotasMB)ve.getValue(facescontext.getELContext());

		ve = null;
		facescontext = null;

		return mb;
	}*/

}
