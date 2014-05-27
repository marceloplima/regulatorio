package br.com.telefonica.ssi.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Modulos;

@FacesConverter("moduloConv")
public class ModulosConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {		
		
		try{
			if(value == "" || value.equals("")){
				value = "0";
			}
			Long idmodulo = Long.parseLong(value);
			Modulos modulo = (new Modulos()).getByid(idmodulo);
			
			return modulo;
		}
		catch(Exception e){
			System.out.println("Exception em : ModulosConverter");
			throw new ConverterException(e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		Modulos modulo = (Modulos)obj;
		
		try{
			if(modulo!=null)
				return modulo.getId().toString();
			else
				return "";
		}
		catch(Exception e){
			System.out.println("Exception em : ModulosConverter");
			throw new ConverterException(e);
		}
	}

}
