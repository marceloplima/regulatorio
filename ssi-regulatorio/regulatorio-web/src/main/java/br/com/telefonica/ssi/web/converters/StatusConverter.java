package br.com.telefonica.ssi.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.telefonica.ssi.regulatorio.commom.domain.StatusRegulatorio;

@FacesConverter("statusConv")
public class StatusConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		
		StatusRegulatorio obj = new StatusRegulatorio();
		obj.setId(null);
		
		if(valor.trim().equals("")){
			return obj;
		}
		else{
			try{
				Integer id = Integer.parseInt(valor);
				obj.setId(id);
				return obj;
			}
			catch(Exception ex){
				ex.printStackTrace();
				System.out.println("Erro no status converter");
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) {
			return "";
		}
		StatusRegulatorio objint = (StatusRegulatorio)obj;
		
		try {
			return objint.getId().toString();
		} catch (Exception e) {
			System.out.println("Erro no "+this.getClass().getName());
			throw new ConverterException(e);
		}
	}

}
