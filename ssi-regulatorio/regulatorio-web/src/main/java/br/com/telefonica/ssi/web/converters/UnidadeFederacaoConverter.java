package br.com.telefonica.ssi.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.telefonica.ssi.regulatorio.commom.domain.UF;

@FacesConverter("ufedConverter")
public class UnidadeFederacaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		UF obj = new UF();
		try {
			if (valor != "" && !valor.equals("") && !valor.equals("null")) {

				obj = new UF();

				obj.setDescricao(valor);

				return obj;
			}
		} catch (Exception e) {
			obj = new UF();
			obj.setId(null);
			obj.setDescricao("");
			;
			System.out.println(">> Erro no UnidadeFederacaoConverter getAsObject()<<");
		}

		return obj;
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
