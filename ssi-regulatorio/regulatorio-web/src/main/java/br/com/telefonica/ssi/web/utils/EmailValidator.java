package br.com.telefonica.ssi.web.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.telefonica.ssi.core.utils.validator.Validador;
import br.com.telefonica.ssi.core.utils.validator.ValidadorEmail;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator{

	@SuppressWarnings("all")
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {

		Validador validadorEmail = new ValidadorEmail();

		if(!validadorEmail.validar((String)arg2)){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha na validação de email", "Formato de email inválido!");
			throw new ValidatorException(msg);
		}
	}
}
