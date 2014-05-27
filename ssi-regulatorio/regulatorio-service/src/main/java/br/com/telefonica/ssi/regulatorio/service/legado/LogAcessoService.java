package br.com.telefonica.ssi.regulatorio.service.legado;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.LogAcesso;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.LogAcessoInt;

@Stateless
public class LogAcessoService implements LogAcessoInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	public LogAcessoService(){}
	
	
	@Override
	public void incluir(LogAcesso obj) {
		em.merge(obj);
		em.flush();
	}
	
}
