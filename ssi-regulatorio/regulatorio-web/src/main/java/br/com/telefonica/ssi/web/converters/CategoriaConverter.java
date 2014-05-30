package br.com.telefonica.ssi.web.converters;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.com.telefonica.ssi.regulatorio.commom.domain.CategoriaRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.CategoriaService;

@Named
@ApplicationScoped
public class CategoriaConverter implements Converter{

	@EJB
	private CategoriaService categoriaService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		CategoriaRegulatorio obj = new CategoriaRegulatorio();

		try{
			if(value != "" && !value.equals("") && !value.equals("null")){
				Integer id = Integer.parseInt(value);

				obj = categoriaService.findById(id);

				//obj.setId(id);

				return obj;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			obj = new CategoriaRegulatorio();
			obj.setId(null);
			obj.setDescricao("");;
			System.out.println(">>caiu na exception CategoriaConverter getAsObject()<<");
		}
		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {

		CategoriaRegulatorio objint = (CategoriaRegulatorio)obj;

		try{
			if(objint!=null){
				//return objint.getDescricao();
				return objint.getId().toString();
			}else{
				return "";
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(">>caiu na exception CategoriaConverter getAsString()<<");
			return "";
		}
	}

}