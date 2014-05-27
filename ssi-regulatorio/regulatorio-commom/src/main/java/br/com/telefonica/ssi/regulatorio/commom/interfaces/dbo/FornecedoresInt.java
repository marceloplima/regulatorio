package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Fornecedores;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.TiposFornecedor;

@Local
public interface FornecedoresInt {

	public List<Fornecedores> recuperar();
	public Fornecedores recuperarUnico(Fornecedores obj);
	public void alterar(Fornecedores obj);
	public boolean verificaExistente(Fornecedores obj) throws IndexOutOfBoundsException;
	public List<Fornecedores> recuperarFiltrado(Map<String, Object> filtros);
	public void incluir(Fornecedores obj);
	public List<TiposFornecedor> retornarTiposFornecedorFornecedor(Fornecedores fornecedor);
	public List<String> recuperarAutocomplete(String autobusca);
	Fornecedores recuperarPorNome(String cnmfornecedor);
	
}
