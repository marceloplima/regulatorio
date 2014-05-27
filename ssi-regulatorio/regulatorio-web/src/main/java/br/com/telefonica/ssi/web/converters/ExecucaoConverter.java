package br.com.telefonica.ssi.web.converters;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.com.telefonica.ssi.regulatorio.commom.domain.Execucao;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ExecucaoService;

@Named
@ApplicationScoped
public class ExecucaoConverter implements Converter{

	@EJB
	private ExecucaoService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Execucao obj = new Execucao();

		try{
			if(value != null && !value.equals("")){
				obj = service.findByName(value);
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
		Execucao objint = (Execucao)obj;

		try{
			return objint.getDescricao();
		}
		catch(Exception e){
			System.out.println("Exception em : Execucao");
			return "";
		}
	}
}
