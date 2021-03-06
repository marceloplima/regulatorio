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

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.FuncionalidadesInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.PessoasInt;


@WebFilter(urlPatterns = {"/index_sistema.xhtml", "/minhasdemandas.xhtml", "/minhaspendencias.xhtml", "/interno/*"})
public class LogadoFilter implements Filter {
	
	@EJB
	private FuncionalidadesInt funcionalidadesproxy;
	
	@EJB
	private PessoasInt pessoasint;
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest req = (HttpServletRequest) request;
        
        Pessoas p = (Pessoas) req.getSession().getAttribute("sessao");
        
        if(p!=null && p.getId()!=null && p.getId()>=1){
        	
        	p = pessoasint.recuperarUnico(p.getId());
        	
        	if(funcionalidadesproxy.verificarPermissaoFuncionalidadePessoaModulo(338L, p)){
	        	try{
	        		chain.doFilter(request, response);
	        	}
	        	catch(Exception ex){}
        	}
        	else{
        		HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect(req.getContextPath() + "/index.xhtml");
        	}
        }
        else{
        	HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(req.getContextPath() + "/index.xhtml");
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