package br.com.telefonica.ssi.web.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("DataDemanda")
public class RevisaoPrazoDateValidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		Date date = (Date) arg2;
		if(date!=null){
			Date dataDemanda = (Date) arg1.getAttributes().get("dataDemanda");
			if(dataDemanda!=null){
				if(date.compareTo(dataDemanda)<0){
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Data anterior ao prazo atual","Data anterior ao prazo atual"));
				}
			}
		}
	}
}
