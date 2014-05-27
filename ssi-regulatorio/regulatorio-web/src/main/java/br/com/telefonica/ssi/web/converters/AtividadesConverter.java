package br.com.telefonica.ssi.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Atividades;

@FacesConverter("atividadeConv")
public class AtividadesConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {		
		
		Atividades obj = new Atividades();
		try{
			if(value != "" && !value.equals("") && !value.equals("null")){
							
				Long id = Long.parseLong(value);
				
				obj.setId(id);
			
				return obj;
			}
		}
		catch(Exception e){
			obj = new Atividades();
			obj.setId(null);
			obj.setCnmatividade("");
			System.out.println(">>> Erro no AtividadesConverter getAsObject()");
		}
		
		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		Atividades objint = (Atividades)obj;
		
		try{
			return objint.getId().toString();
		}
		catch(Exception e){
			System.out.println(">>> Erro no AtividadesConverter getAsString()");
			return "";
		}
	}

}
