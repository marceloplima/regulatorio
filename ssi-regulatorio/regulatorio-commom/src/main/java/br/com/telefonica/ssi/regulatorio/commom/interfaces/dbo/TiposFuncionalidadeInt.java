package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.TiposFuncionalidade;

@Local
public interface TiposFuncionalidadeInt {

	public List<TiposFuncionalidade> recuperar();
	public TiposFuncionalidade recuperarUnico(Long idtipofuncionalidade);
	public boolean verificaExistente(TiposFuncionalidade obj) throws IndexOutOfBoundsException;
	public List<TiposFuncionalidade> recuperarFiltrado(Map<String, Object> filtros);
	public void incluir(TiposFuncionalidade obj);
	public void alterar(TiposFuncionalidade obj);
	public boolean remover();
	
}
