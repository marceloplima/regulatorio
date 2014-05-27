package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.LogAcesso;

@Local
public interface LogAcessoInt {
	public void incluir(LogAcesso obj);
}
