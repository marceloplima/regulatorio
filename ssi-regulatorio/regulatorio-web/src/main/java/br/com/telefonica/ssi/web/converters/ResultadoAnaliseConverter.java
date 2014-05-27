package br.com.telefonica.ssi.web.converters;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.com.telefonica.ssi.regulatorio.commom.domain.ResultadoAnalise;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ResultadoAnaliseService;

@Named
@ApplicationScoped
public class ResultadoAnaliseConverter implements Converter{

	@EJB
	private ResultadoAnaliseService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		ResultadoAnalise obj = new ResultadoAnalise();

		try{
			if(value != null && !value.equals("")){
				Integer id = Integer.parseInt(value);
				obj = service.findById(id);
				return obj;
			}
		}
		catch(Exception e){
			System.out.println("Exception em : ResultadoAnalise");
			return null;
		}
		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {

		if(obj==null){
			return null;
		}
		ResultadoAnalise objint = (ResultadoAnalise)obj;

		try{
			return objint.getId().toString();
		}
		catch(Exception e){
			System.out.println("Exception em : ResultadoAnalise");
			return "";
		}
	}

}
