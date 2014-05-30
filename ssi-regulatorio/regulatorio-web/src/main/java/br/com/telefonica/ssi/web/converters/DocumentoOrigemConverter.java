package br.com.telefonica.ssi.web.converters;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.com.telefonica.ssi.regulatorio.commom.domain.DocumentoOrigem;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.DocumentoOrigemService;

@Named
@ApplicationScoped
public class DocumentoOrigemConverter implements Converter{

	@EJB
	private DocumentoOrigemService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		DocumentoOrigem obj = new DocumentoOrigem();
		try{
			if(value != null && !value.equals("")){

				Integer id = Integer.parseInt(value);
				obj = service.findById(id);
//				obj = new DocumentoOrigem();
//				obj.setId(id);
				return obj;
			}
		}
		catch(Exception e){
			System.out.println(">> Erro no DocumentoOrigemConverter getAsObject()<<");
			obj =  null;
		}

		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {

		if(obj==null){
			return "";
		}

		DocumentoOrigem objint = (DocumentoOrigem)obj;

		try{
			return objint.getId().toString();
		}
		catch(Exception e){
			System.out.println(">>> Erro no DocumentoOrigemConverter getAsString()");
			return "";
		}
	}

}
