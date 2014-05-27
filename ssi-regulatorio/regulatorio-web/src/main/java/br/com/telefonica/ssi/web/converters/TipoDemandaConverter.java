package br.com.telefonica.ssi.web.converters;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.com.telefonica.ssi.regulatorio.commom.domain.TipoDemanda;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.TipoDemandaService;

@Named
@ApplicationScoped
public class TipoDemandaConverter implements Converter{

	@EJB
	private TipoDemandaService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		TipoDemanda obj = new TipoDemanda();

		try{
			if(value != null && !value.equals("")){
				Integer id = Integer.parseInt(value);
				obj = service.findById(id);
//				obj = new TipoDemanda();
//				obj.setIdTipoDemanda(id);
				return obj;
			}
		}
		catch(Exception e){
			System.out.println("Exception em : TipoDemanda");
			return null;
		}
		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {

		if(obj==null){
			return null;
		}
		TipoDemanda objint = (TipoDemanda)obj;

		try{
			return objint.getId().toString();
		}
		catch(Exception e){
			System.out.println("Exception em : TipoDemandaModulos");
			return "";
		}
	}

}
