package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Telefones;

@Local
public interface TelefonesInt {
	public boolean verificaExistente(Telefones telefone) throws IndexOutOfBoundsException;
	public Telefones incluir(Telefones telefone);
	public void alterar(Telefones telefone);
	public void remover(Telefones telefone);
}
