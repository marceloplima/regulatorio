package br.com.telefonica.ssi.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Ufs;


@FacesConverter("ufConverter")
public class UfConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		//System.out.println("getAsObject");
		Ufs uf = new Ufs();
		uf.setId(null);;

		if (valor.trim().equals("")) {
			//System.out.println("UF ZERADO");
			return uf;
		} else {

			try {
				//System.out.println("TENTANDO UF");
				Long id = Long.parseLong(valor);
				uf.setId(id);

				return uf;

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro no uf converter");
				return uf;
			}
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		//System.out.println("ENTREI: getAsString");
		if(obj==null){
			//System.out.println("getAsString NULO");
			return "";
		}

		Ufs uf = (Ufs) obj;

		try {
			//System.out.println("TENTANDO getAsString");
			return uf.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro no uf converter: getAsString");
			throw new ConverterException(e);
		}
	}

}
