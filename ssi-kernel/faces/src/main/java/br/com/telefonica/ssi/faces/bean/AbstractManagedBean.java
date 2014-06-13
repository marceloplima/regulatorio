package br.com.telefonica.ssi.faces.bean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * Classe contendo métodos genéricos utilizados em Managed Beans
 *
 * @author <a href='mailto:marcelo.batista@druid.com.br'>Marcelo Batista</a>
 * @date 17/04/2013 10:06:11
 * @version $Id: AbstractManagedBean.java 11518 2013-09-03 12:35:13Z marcelo.batista $
 */
@SuppressWarnings("serial")
public abstract class AbstractManagedBean implements Serializable {

	protected static final String FACES_REDIRECT = "?faces-redirect=true";
	private String defaultOutCome;

	/**
	 * Cria uma instancia de AbstractManagedBean.
	 *
	 * @param defaultOutcome
	 *            O outCome default para o ManagedBean.
	 */
	public AbstractManagedBean(String defaultOutcome) {
		this.setDefaultOutCome(defaultOutcome);
	}

	public AbstractManagedBean() {
		super();
	}

	/**
	 * TODO Comentar metodo.
	 *
	 * @return
	 */
	public String acaoIniciar() {
		return getDefaultOutCome();
	}

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
	private void adicionarMensagem(final Severity severidade, final String mensagem, final String clientId) {
		final String bundleMsg = getBundleKey(mensagem, null);
		final FacesMessage facesMsg = new FacesMessage(severidade, bundleMsg, bundleMsg);
		getFacesContext().addMessage(clientId, facesMsg);
	}

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
	private void adicionarMensagem(final Severity severidade, final String mensagem, final Object[] paramValues, final String clientId) {
		final String bundleMsg = getBundleKey(mensagem, paramValues);
		final FacesMessage facesMsg = new FacesMessage(severidade, bundleMsg, bundleMsg);
		getFacesContext().addMessage(clientId, facesMsg);
	}

	/**
	 * Adiciona uma mensagem com severidade WARNING ao contexto JSF.
	 *
	 * @param mensagem
	 *            A mensagem.
	 * @param paramValues
	 *            Parametros {0}.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	protected void adicionarMensagemAviso(final String mensagem, Object[] paramValues, final String clienteId) {
		adicionarMensagem(FacesMessage.SEVERITY_WARN, mensagem, paramValues, clienteId);
	}

	/**
	 * Adiciona uma mensagem com severidade ERROR ao contexto JSF.
	 *
	 * @param mensagem
	 *            A mensagem.
	 * @param paramValues
	 *            Parametros {0}.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	protected void adicionarMensagemDeErro(final String mensagem, Object[] paramValues, final String clienteId) {
		adicionarMensagem(FacesMessage.SEVERITY_ERROR, mensagem, paramValues, clienteId);
	}

	/**
	 * Adiciona uma mensagem com severidade FATAL ao contexto JSF.
	 *
	 * @param mensagem
	 *            A mensagem.
	 * @param paramValues
	 *            Parametros {0}.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	protected void adicionarMensagemFatal(final String mensagem, Object[] paramValues, final String clienteId) {
		adicionarMensagem(FacesMessage.SEVERITY_FATAL, mensagem, paramValues, clienteId);
	}

	/**
	 * Adiciona uma mensagem com severidade INFO ao contexto JSF.
	 *
	 * @param mensagem
	 *            A mensagem.
	 * @param paramValues
	 *            Parametros {0}.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	protected void adicionarMensagemInformativa(final String mensagem, Object[] paramValues, final String clienteId) {
		adicionarMensagem(FacesMessage.SEVERITY_INFO, mensagem, paramValues, clienteId);
	}


	/**
	 * Adiciona uma mensagem com severidade WARNING ao contexto JSF.
	 *
	 * @param mensagem
	 *            A mensagem.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	protected void adicionarMensagemAviso(final String mensagem, final String clienteId) {
		adicionarMensagem(FacesMessage.SEVERITY_WARN, mensagem, clienteId);
	}

	/**
	 * Adiciona uma mensagem com severidade ERROR ao contexto JSF.
	 *
	 * @param mensagem
	 *            A mensagem.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	protected void adicionarMensagemDeErro(final String mensagem, final String clienteId) {
		adicionarMensagem(FacesMessage.SEVERITY_ERROR, mensagem, clienteId);
	}

	/**
	 * Adiciona uma série de mensagens com severidade ERROR ao contexto JSF.
	 *
	 * @param mensagens
	 *            Collection de mensagem.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	protected void adicionarMensagensDeErro(final ArrayList<String> mensagens, final String clienteId) {
		for (String mensagem : mensagens) {
			this.adicionarMensagemDeErro(mensagem, clienteId);
		}
	}

	/**
	 * Adiciona uma mensagem com severidade FATAL ao contexto JSF.
	 *
	 * @param mensagem
	 *            A mensagem.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	protected void adicionarMensagemFatal(final String mensagem, final String clienteId) {
		adicionarMensagem(FacesMessage.SEVERITY_FATAL, mensagem, clienteId);
	}

	/**
	 * Adiciona uma mensagem com severidade INFO ao contexto JSF.
	 *
	 * @param mensagem
	 *            A mensagem.
	 * @param clienteId
	 *            O id do componente refernete a mensagem ou <code>null</code>
	 */
	protected void adicionarMensagemInformativa(final String mensagem, final String clienteId) {
		adicionarMensagem(FacesMessage.SEVERITY_INFO, mensagem, clienteId);
	}

	/**
	 * Retorna a aplicação do contexto JSF.
	 *
	 * @return A instância de <code>Application</code>.
	 */
	protected Application getApplication() {
		return getFacesContext().getApplication();
	}

	/**
	 * Retorna uma mensagem do MessageBundle do contexto JSF.
	 *
	 * @param key
	 *            A chave da mensagem.
	 * @param paramValues
	 *            Parametros da mensagem. {0}
	 * @return A mensagem recuperada.
	 */
	protected String getBundleKey(final String key, Object[] paramValues) {
		String msgValue = ResourceBundle.getBundle(getApplication().getMessageBundle()).getString(key);
		MessageFormat   messageFormat = new MessageFormat(msgValue);
	    return messageFormat.format(paramValues);
	}

	/**
	 * Retorna o valor de defaultOutCome.
	 *
	 * @return o valor de defaultOutCome
	 */
	public String getDefaultOutCome() {
		return defaultOutCome;
	}

	/**
	 * Retorna o ELContext do contexto JSF.
	 *
	 * @return A instância de <code>ELContext</code>.
	 */
	protected ELContext getELContext() {
		return getFacesContext().getELContext();
	}

	/**
	 *
	 * TODO Comentar método getExternalContext.
	 *
	 * @return
	 */
	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	/**
	 *
	 * TODO Comentar método getFacesContext.
	 *
	 * @return
	 */
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}


	/**
	 * Executa um forward
	 * @param forward
	 */
	protected void handleNavigation(String forward) {
		final NavigationHandler nav = getFacesContext().getApplication().getNavigationHandler();
		nav.handleNavigation(getFacesContext(), null, forward);
	}

	/**
	 *
	 * TODO Comentar método getHttpRequest.
	 *
	 * @return
	 */
	protected HttpServletRequest getHttpRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	/**
	 *
	 * TODO Comentar método getHttpResponse.
	 *
	 * @return
	 */
	protected HttpServletResponse getHttpResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	/**
	 *
	 * TODO Comentar método getHttpSession.
	 *
	 * @param criarSessao
	 * @return
	 */
	protected HttpSession getHttpSession(final boolean criarSessao) {
		return (HttpSession) getExternalContext().getSession(criarSessao);
	}

	/**
	 * Altera o valor de defaultOutCome.
	 *
	 * @param defaultOutCome
	 *            o novo valor de defaultOutCome
	 */
	public void setDefaultOutCome(String defaultOutCome) {
		this.defaultOutCome = defaultOutCome;
	}

	/**
	 * Recupera um parametro passado por queryString
	 * @param parameterName
	 * @return
	 */
	public String getUrlParameter(String parameterName){
		FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        return paramMap.get(parameterName);
	}

	public void setHttpSessionAttribute(String name, Object value){
		HttpSession httpSession = this.getHttpSession(true);
		httpSession.setAttribute(name, value);
	}

	public Object getHttpSessionAttribute(String name){
		HttpSession httpSession = this.getHttpSession(true);
		return httpSession.getAttribute(name);
	}


	public String getContextUrl(){
		HttpServletRequest request = this.getHttpRequest();
        return request.getRequestURL().toString().replace(request.getRequestURI().substring(0), "") + request.getContextPath();
	}

	public void devolveErroParaTela(String id, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(id,new FacesMessage(mensagem));
	}
}