package br.com.telefonica.ssi.web.utils;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.FuncionalidadesInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.PessoasInt;

@WebFilter("/index.xhtml")
public class LoginFilter implements Filter {
	
	@EJB
	private FuncionalidadesInt funcionalidadesproxy;
	
	@EJB
	private PessoasInt pessoasint;
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest req = (HttpServletRequest) request;
        
        HttpSession sessao = req.getSession(true);
        Pessoas p = (Pessoas) sessao.getAttribute("sessao");
        
        String iddemanda = "";
        
        if(req.getSession().getAttribute("iddemanda") == null){
        	iddemanda = (String) request.getParameter("iddemanda");
	        sessao.setAttribute("iddemanda", iddemanda);
        }
        
        if(p!=null && p.getId()!=null && p.getId()>=1) {
        	
        	p = pessoasint.recuperarUnico(p.getId());
        	
        	if(funcionalidadesproxy.verificarPermissaoFuncionalidadePessoaModulo(338L, p)){
        		HttpServletResponse res = (HttpServletResponse) response;
        		if(iddemanda!=null && !iddemanda.equals("")){
        			res.sendRedirect(req.getContextPath() + "/interno/cadssi.xhtml?iddemanda="+iddemanda);
        		}
        		else{
        			res.sendRedirect(req.getContextPath() + "/index_sistema.xhtml");
        		}
        	}
        	else{
        		chain.doFilter(request, response);
        	}
        } else {
        	chain.doFilter(request, response);
        }
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}