package br.com.telefonica.ssi.core.utils.validator;

import java.io.Serializable;

public interface Validador<T> extends Serializable{
	Boolean validar(T param);
}
