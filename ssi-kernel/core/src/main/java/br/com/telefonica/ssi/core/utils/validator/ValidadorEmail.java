package br.com.telefonica.ssi.core.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorEmail implements Validador<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3769717688939291947L;

	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";
 
	private Pattern pattern;
	private Matcher matcher;
 
	public ValidadorEmail(){
		  setPattern(Pattern.compile(EMAIL_PATTERN));
	}
	
	@Override
	public Boolean validar(String email){
 
		setMatcher(getPattern().matcher(email));
		if(!getMatcher().matches()){
			return false;
		}
		
		return true;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public Matcher getMatcher() {
		return matcher;
	}

	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}
}
