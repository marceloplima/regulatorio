package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Dominios;

@Local
public interface DominiosInt {
	public List<Dominios> recuperar();
	public Dominios recuperarUnico(Long iddominio);
	public boolean remover();
}
