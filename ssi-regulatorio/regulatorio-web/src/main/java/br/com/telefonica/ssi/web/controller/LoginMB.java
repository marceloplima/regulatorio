package br.com.telefonica.ssi.web.controller;


import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import br.com.telefonica.ssi.core.crypto.MD5.MD5Hashing;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Dominios;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.LogAcesso;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Usuarios;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.AutenticadorInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.LogAcessoInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.PessoasInt;
import br.com.telefonica.ssi.web.legado.utils.ParametrosGenericos;
import br.com.telefonica.ssi.web.utils.PesistenciaSessao;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -1181470029084845230L;
	private Dominios dominio;
	private Usuarios usuario = new Usuarios();
	private Boolean flagerroautenticacao;

	@EJB
	private AutenticadorInt autenticadorproxy;

	@EJB
	private PessoasInt pessoasint;

	@EJB
	private LogAcessoInt logacessoint;

	private String unidexterno;

	@PostConstruct
	public void init(){

		Map<String,Object> sessao = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();


		String iddemanda = "";
		iddemanda = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("iddemanda");

		if(sessao.get("iddemanda")==null){
			if(iddemanda!=""){
				sessao.put("iddemanda", iddemanda);
			}
		}

		// Início da construção do objeto por um parâmetro passado para deslogar
		String deslogar = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("deslogar");

		if(deslogar !=null && deslogar.equals("true")){
			System.out.println(">> MÓDULO REGULATORIO : vai destruir sessão..");
			destroisessao();
		}

		// Se houver sessão, não verifica cookie (usa a sessão)

		Pessoas p = (Pessoas) sessao.get("sessao");

		if (p == null || !(p.getId()>=1)) {

			Usuarios logado = PesistenciaSessao.recuperarUsuarioCookie();

			if(logado != null){

				setUsuario(logado);
				setDominio(logado.getDominio());

				logar(false);

				HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
				try {
					if(iddemanda == null || iddemanda.equals("")){
						response.sendRedirect("/REGULATORIO/index_sistema.xhtml");
					}
					else{
						response.sendRedirect("/REGULATORIO/interno/cadssi.xhtml?iddemanda="+iddemanda);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void destroisessao(){
		usuario = null;
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		sessao.invalidate();
		sessao = null;
		System.out.println(">> MODULO REGULATORIO : SESSÃO DE USUÁRIO ELIMINADA <<");
	}

	public Dominios getDominio() {
		return dominio;
	}
	public void setDominio(Dominios dominio) {
		this.dominio = dominio;
	}
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Boolean getFlagerroautenticacao() {
		return flagerroautenticacao;
	}
	public void setFlagerroautenticacao(Boolean flagerroautenticacao) {
		this.flagerroautenticacao = flagerroautenticacao;
	}

	public Pessoas recuperarPessoaLogado(){
		Map<String,Object>sessaoAtiva = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Pessoas pessoa = (Pessoas)sessaoAtiva.get("sessao");
		return pessoa;
	}

	public String recuperarUsuarioLogado(){
		Map<String,Object>sessaoAtiva = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Pessoas pessoa = (Pessoas)sessaoAtiva.get("sessao");
		if(pessoa!=null)
			return pessoa.getUsuario().getCnmlogin();
		else
			return "";
	}

	public String recuperarNomeLogado(){
		Map<String,Object>sessaoAtiva = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Pessoas pessoa = (Pessoas)sessaoAtiva.get("sessao");
		if(pessoa!=null)
			return pessoa.getUsuario().getPessoa().getCnmnome();
		else
			return "";
	}

	public String recuperarDataAcesso(){
		Map<String,Object>sessaoAtiva = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		String dataacesso = (String)sessaoAtiva.get("dataacesso");
		return dataacesso;
	}

	public String logar(boolean redireciona){

		autenticadorproxy.setDominio(getDominio());

		if(redireciona){
			if(getDominio().getId() == 2){
				getUsuario().setCnmsenha(MD5Hashing.criptografar(getUsuario().getCnmsenha())); // Criptografar a senha
			}
		}else{ // Se redireciona for false, significa que o método foi chamado para reautenticação por cookie
			Pessoas pessoaautenticada = pessoasint.recuperarPorUsuario(getUsuario());
			setUsuario(pessoaautenticada.getUsuario());
		}

		autenticadorproxy.setUsuario(getUsuario());

		if(autenticadorproxy.autenticar()){

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
			Date date = new Date();
			System.out.println();

			Map<String,Object> sessao = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			sessao.put("sessao", pessoasint.recuperarPorUsuario(getUsuario()));
			sessao.put("dominio", getDominio());
			sessao.put("dataacesso", dateFormat.format(date));
			setFlagerroautenticacao(false);

			// Persiste a sessão
			PesistenciaSessao.gerarCookie(getUsuario().getCnmlogin(), getDominio().getId().toString(),ParametrosGenericos.LBL_COOKIE_MAXAGE);

			// Loga o acesso
			LogAcesso logacesso = new LogAcesso();
			logacesso.setCnmlogin(getUsuario().getCnmlogin());
			logacesso.setCnmmodulo("REDES");
			logacessoint.incluir(logacesso);

			String universalId = (String)sessao.get("universalId");
			String numerossi = (String)sessao.get("numerossi");
			String iddemanda = (String)sessao.get("iddemanda");

			if(redireciona){
				if(!StringUtils.isEmpty(universalId) || !StringUtils.isEmpty(numerossi) || !StringUtils.isEmpty(iddemanda)){
					return "/interno/cadssi.xhtml?faces-redirect=true";
				}else{
					return "index_sistema?faces-redirect=true";
				}
			}
		}
		else{
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

			PesistenciaSessao.eliminarCookies(request, response);
			flagerroautenticacao = true;
		}

		return "index";
	}

	public boolean deslogar(){
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		try {

			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

			PesistenciaSessao.eliminarCookies(request, response);
			destroisessao();

			response.sendRedirect("http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/index.xhtml?deslogar=true");
		} catch (IOException e) {
			e.printStackTrace();
		}
		response = null;
		System.gc();
		return true;
	}

	public String getUnidexterno() {
		return unidexterno;
	}

	public void setUnidexterno(String unidexterno) {
		this.unidexterno = unidexterno;
	}

}
