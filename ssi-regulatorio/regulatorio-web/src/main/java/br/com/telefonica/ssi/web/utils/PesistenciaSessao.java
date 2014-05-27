package br.com.telefonica.ssi.web.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Dominios;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Usuarios;
import br.com.telefonica.ssi.web.legado.utils.ParametrosGenericos;

public class PesistenciaSessao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5624223926780008269L;
	static ParametrosSistema parametros = new ParametrosSistema();
	
	/**
	 * Gera um cookie criptografado para reautenticar o usuário
	 * 
	 * @param login Login criptografado a ser armazenado
	 */
	public static void gerarCookie(String login, String iddominio, Integer maxage){
		ExternalContext ec  = FacesContext.getCurrentInstance().getExternalContext();
		
		Map<String,Object> cookieProps = new HashMap<String,Object>();
		cookieProps.put("maxAge", maxage); // Define o tempo ativo do cookie
		cookieProps.put("path", "/");
		
		try {
			ec.addResponseCookie(ParametrosGenericos.LBL_COOKIE_LOGIN, URLEncoder.encode(login,"UTF-8"), cookieProps);
			//ec.addResponseCookie(ParametrosGenericos.LBL_COOKIE_SENHA, URLEncoder.encode(EncriptadorAES.encrypt(senha),"UTF-8"), cookieProps);
			ec.addResponseCookie(ParametrosGenericos.LBL_COOKIE_DOMINIO, URLEncoder.encode(iddominio,"UTF-8"), cookieProps);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Map<String,Object> retornarMapaCookies(){
		Map<String,Object> cookies = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
		return cookies;
	}
	
	/**
	 * Retorna um usuário através do cookie armazenado
	 * 
	 * @return usuário já autenticado
	 */
	public static Usuarios recuperarUsuarioCookie(){
		
		Usuarios usuario = new Usuarios();
		Dominios dominio = new Dominios();
		
		Map<String,Object> cookies = retornarMapaCookies();
		
		Cookie logincookie = (Cookie)cookies.get(ParametrosGenericos.LBL_COOKIE_LOGIN);
		if(logincookie !=null && logincookie.getValue()!=null && !logincookie.getValue().equals("")){
			String cnmlogincookiecrypt;
			try {
				cnmlogincookiecrypt = URLDecoder.decode(logincookie.getValue(),"UTF-8");
				//String logindescriptografado = EncriptadorAES.decrypt(EncriptadorAES.rawtxt, cnmlogincookiecrypt);
				
				if(cnmlogincookiecrypt!=null){
					
//					Cookie senhacookie = (Cookie)cookies.get(ParametrosGenericos.LBL_COOKIE_SENHA);
//					String cnmsenhacookiecrypt = URLDecoder.decode(senhacookie.getValue(),"UTF-8");
//					
//					String senhadescriptografada = EncriptadorAES.decrypt(EncriptadorAES.rawtxt, cnmsenhacookiecrypt);
//					
					Cookie iddominiocookie = (Cookie)cookies.get(ParametrosGenericos.LBL_COOKIE_DOMINIO);
					String cnmiddominiocookiecrypt = URLDecoder.decode(iddominiocookie.getValue(),"UTF-8");
//					
//					String dominiodescriptografado = EncriptadorAES.decrypt(EncriptadorAES.rawtxt, cnmiddominiocookiecrypt);
					
					usuario.setCnmlogin(cnmlogincookiecrypt);
					//usuario.setCnmsenha(senhadescriptografada);
					
					dominio.setId(new Integer(cnmiddominiocookiecrypt));
					
					usuario.setDominio(dominio);
					
					return usuario;
				}
				else{
					System.out.println(parametros.recuperaModuloSistema()+":: cookie está vazio");
				}
				
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		return null;
	}
	
	public static void eliminarCookies(HttpServletRequest request, HttpServletResponse response){
		try{
			Cookie[] cookies = request.getCookies();
	        for (Cookie cookie : cookies) {
	            cookie.setMaxAge(0);
	            cookie.setValue(null);
	            cookie.setPath("/");
	            response.addCookie(cookie);
	        }
	        
		}
		catch(NullPointerException nullpointer){
			System.out.println(parametros.recuperaModuloSistema()+": cookie nulo em PersistenciaSessao.eliminarCookies - ignorando.");
		}
	}
}
