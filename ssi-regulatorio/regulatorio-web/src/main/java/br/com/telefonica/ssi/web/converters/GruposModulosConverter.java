package br.com.telefonica.ssi.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.GruposModulos;

@FacesConverter("grupomoduloConv")
public class GruposModulosConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {		
		
		GruposModulos obj = new GruposModulos();
		
		try{
			if(value != "" && !value.equals("") && !value.equals("null")){
				Long id = Long.parseLong(value);
				obj = (new GruposModulos()).getByid(id);
			
				return obj;
			}
		}
		catch(Exception e){
			obj = new GruposModulos();
			obj.setId(null);
			obj.setCnmgrupomodulo("");
			System.out.println("Exception em : GruposModulos");
		}
		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		GruposModulos objint = (GruposModulos)obj;
		
		try{
			return objint.getId().toString();
		}
		catch(Exception e){
			System.out.println("Exception em : GruposModulos");
			return "";
		}
	}

}
