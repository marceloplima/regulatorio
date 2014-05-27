package br.com.telefonica.ssi.web.converters;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.com.telefonica.ssi.regulatorio.commom.domain.AreasRegionais;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.AreaRegionalService;

@Named
@ApplicationScoped
public class AreaRegionalConverter implements Converter{

	@EJB
	private AreaRegionalService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		AreasRegionais obj = new AreasRegionais();
		try{
			if(value != null && !value.equals("")){

				Long id = Long.parseLong(value);
				obj = service.findById(id);
//				obj = new AreasRegionais();
//				obj.setId(id);
				return obj;
			}
		}
		catch(Exception e){
			System.out.println(">> Erro no AreasRegionais getAsObject()<<");
			return null;
		}

		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if(obj==null){
			return "";
		}

		AreasRegionais objint = (AreasRegionais)obj;

		try{
			return objint.getId().toString();
		}
		catch(Exception e){
			System.out.println(">>> Erro no AreasRegionais Converter getAsString()");
			return "";
		}
	}

}
