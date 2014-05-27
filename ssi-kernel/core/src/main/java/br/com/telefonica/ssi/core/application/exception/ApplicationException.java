package br.com.telefonica.ssi.core.application.exception;

/**
 * 
 * Exceções da camada de aplicação.
 *
 * @author <a href='mailto:marcelo.batista@druid.com.br'>Marcelo Batista</a>
 * @date 17/04/2013 10:02:28
 * @version $Id: ApplicationException.java 13404 2013-11-01 14:10:30Z marcelo.batista $
 */
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 3287945184529567476L;

	/**
	 * Cria uma nova instância de ApplicationException.
	 */
	public ApplicationException() {
		super();
	}

	/**
	 * Cria uma nova instância de ApplicationException.
	 * 
	 * @param message
	 * @param cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Cria uma nova instância de ApplicationException.
	 * 
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * Cria uma nova instância de ApplicationException.
	 * 
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}

}