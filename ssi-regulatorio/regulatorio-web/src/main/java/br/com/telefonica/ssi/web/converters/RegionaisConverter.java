package br.com.telefonica.ssi.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Regionais;

@FacesConverter("regionalConverter")
public class RegionaisConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {

		if(valor == null){
			return null;
		}

		Regionais regional = new Regionais();
		regional.setId(null);

		if (valor.trim().equals("")) {
			return regional;
		} else {
			try {
				Long id = Long.parseLong(valor);
				regional.setId(id);
				return regional;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro no regionais converter");
				return regional;
			}
		}

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {

		if(obj == null){
			return "";
		}

		Regionais regional = (Regionais) obj;

		try {
			return regional.getId().toString();
		} catch (Exception e) {
			System.out.println("Erro no "+this.getClass().getName());
			throw new ConverterException(e);
		}
	}

}
