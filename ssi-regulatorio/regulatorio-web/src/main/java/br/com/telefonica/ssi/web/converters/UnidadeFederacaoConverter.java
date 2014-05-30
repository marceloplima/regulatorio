package br.com.telefonica.ssi.web.converters;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.com.telefonica.ssi.regulatorio.commom.domain.UF;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.UFService;

@Named
@ApplicationScoped
public class UnidadeFederacaoConverter implements Converter {

	@EJB
	private UFService ufService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if(valor!=null && !valor.equals("")){
			UF uf = ufService.findByName(valor);
			return uf;
		}
		else{
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		UF objint = (UF) obj;

		try {
			return objint.getDescricao();
		} catch (Exception e) {
			System.out.println(">>> Erro no UnidadeFederacaoConverter getAsString()");
			return "";
		}
	}

}
