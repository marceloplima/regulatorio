package br.com.telefonica.ssi.web.converters;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.com.telefonica.ssi.regulatorio.commom.domain.TipoRede;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.TipoRedeService;

@Named
@ApplicationScoped
public class TipoRedeConverter implements Converter{

	@EJB
	private TipoRedeService service;


	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		TipoRede obj = new TipoRede();
		try{
			if(value != null && !value.equals("")){
				Integer id = Integer.parseInt(value);

				obj = service.findById(id);

//				obj = new TipoRede();
//				obj.setId(id);
				return obj;
			}
		}
		catch(Exception e){
			System.out.println(">> Erro no TipoRedeConverter getAsObject()<<");
			return null;
		}

		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if(obj==null){
			return null;
		}

		TipoRede objint = (TipoRede)obj;

		try{
			return objint.getId().toString();
		}
		catch(Exception e){
			System.out.println(">>> Erro no TipoRedeConverter getAsString()");
			return "";
		}
	}

}
