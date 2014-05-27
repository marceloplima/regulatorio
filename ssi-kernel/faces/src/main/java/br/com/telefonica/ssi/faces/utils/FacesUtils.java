package br.com.telefonica.ssi.faces.utils;

import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe utilit��ria para manipula����o da api JSF.
 * 
 * @author <a href='mailto:felipe.santos@druid.com.br'>Felipe Santos</a>
 * 
 */
@Deprecated
public final class FacesUtils {
	
	/**
	 * Adiciona uma mensagem ao contexto JSF.
	 * 
	 * @param severidade
	 *            A severidade da mensagem.
	 * @param mensagem
	 *            A mensagem.
	 * @param clientId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	private static void adicionarMensagem(final Severity severidade, final String mensagem, final String clientId) {
		final String bundleMsg = FacesUtils.getBundleKey(mensagem);
		final FacesMessage facesMsg = new FacesMessage(severidade, bundleMsg, bundleMsg);
		FacesUtils.getFacesContext().addMessage(clientId, facesMsg);
	}
	
	/**
	 * Adiciona uma mensagem com severidade WARNING ao contexto JSF.
	 * 
	 * @param mensagem
	 *            A mensagem.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	public static void adicionarMensagemAviso(final String mensagem, final String clienteId) {
		FacesUtils.adicionarMensagem(FacesMessage.SEVERITY_WARN, mensagem, clienteId);
	}
	
	/**
	 * Adiciona uma mensagem com severidade ERROR ao contexto JSF.
	 * 
	 * @param mensagem
	 *            A mensagem.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	public static void adicionarMensagemDeErro(final String mensagem, final String clienteId) {
		FacesUtils.adicionarMensagem(FacesMessage.SEVERITY_ERROR, mensagem, clienteId);
	}
	
	/**
	 * Adiciona uma mensagem com severidade FATAL ao contexto JSF.
	 * 
	 * @param mensagem
	 *            A mensagem.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	public static void adicionarMensagemFatal(final String mensagem, final String clienteId) {
		FacesUtils.adicionarMensagem(FacesMessage.SEVERITY_FATAL, mensagem, clienteId);
	}
	
	/**
	 * Adiciona uma mensagem com severidade INFO ao contexto JSF.
	 * 
	 * @param mensagem
	 *            A mensagem.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	public static void adicionarMensagemInformativa(final String mensagem, final String clienteId) {
		FacesUtils.adicionarMensagem(FacesMessage.SEVERITY_INFO, mensagem, clienteId);
	}
	
	/**
	 * Retorna a aplica����o do contexto JSF.
	 * 
	 * @return A inst��ncia de <code>Application</code>.
	 */
	public static Application getApplication() {
		return FacesUtils.getFacesContext().getApplication();
	}
	
	/**
	 * Retorna uma mensagem do MessageBundle do contexto JSF.
	 * 
	 * @param key
	 *            A chave da mensagem.
	 * @return A mensagem recuperada.
	 */
	public static String getBundleKey(final String key) {
		return ResourceBundle.getBundle(FacesUtils.getApplication().getMessageBundle()).getString(key);
	}
	
	/**
	 * Retorna o ELContext do contexto JSF.
	 * 
	 * @return A inst��ncia de <code>ELContext</code>.
	 */
	public static ELContext getELContext() {
		return FacesUtils.getFacesContext().getELContext();
	}
	
	/**
	 * 
	 * TODO Comentar m��todo FacesUtils.getExternalContext.
	 * 
	 * @return
	 */
	public static ExternalContext getExternalContext() {
		return FacesUtils.getFacesContext().getExternalContext();
	}
	
	/**
	 * 
	 * TODO Comentar m��todo FacesUtils.getFacesContext.
	 * 
	 * @return
	 */
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * 
	 * TODO Comentar m��todo FacesUtils.getHttpRequest.
	 * 
	 * @return
	 */
	public static HttpServletRequest getHttpRequest() {
		return (HttpServletRequest) FacesUtils.getExternalContext().getRequest();
	}
	
	/**
	 * 
	 * TODO Comentar m��todo FacesUtils.getHttpResponse.
	 * 
	 * @return
	 */
	public static HttpServletResponse getHttpResponse() {
		return (HttpServletResponse) FacesUtils.getExternalContext().getResponse();
	}
	
	/**
	 * 
	 * TODO Comentar m��todo FacesUtils.getHttpSession.
	 * 
	 * @param criarSessao
	 * @return
	 */
	public static HttpSession getHttpSession(final boolean criarSessao) {
		return (HttpSession) FacesUtils.getExternalContext().getSession(criarSessao);
	}
}
