package br.com.telefonica.ssi.regulatorio.commom.interfaces.legado;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Local
public interface PermissoesInt{
	public boolean verificarPermissaoEmissor(Pessoas pessoa);
}
