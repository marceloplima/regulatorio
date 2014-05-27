package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.TiposFornecedor;

@Local
public interface TiposFornecedorInt {
	public List<TiposFornecedor> recuperar();
	public TiposFornecedor recuperarUnico(TiposFornecedor tipofornecedor);
	public boolean verificaExistente(TiposFornecedor obj) throws IndexOutOfBoundsException;
	public List<TiposFornecedor> recuperarFiltrado(Map<String, Object> filtros);
	public void incluir(TiposFornecedor obj);
	public void alterar(TiposFornecedor obj);
}
