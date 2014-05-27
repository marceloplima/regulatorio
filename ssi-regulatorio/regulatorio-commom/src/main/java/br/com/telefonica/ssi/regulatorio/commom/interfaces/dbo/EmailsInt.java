package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Emails;

@Local
public interface EmailsInt {

	public boolean verificaExistente(Emails email) throws IndexOutOfBoundsException;
	public Emails incluir(Emails email);
	public void alterar(Emails email);
	public void remover(Emails email);
	
}
