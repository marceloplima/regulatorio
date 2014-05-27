package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Funcionalidades;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Grupos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Modulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Local
public interface FuncionalidadesInt {
	public List<Funcionalidades> recuperar(Long idmodulo, Pessoas pessoa);
	public List<Funcionalidades> recuperarPorModulo(Long idmodulo);
	public Funcionalidades recuperarUnico(Long idfuncionalidade);
	public Funcionalidades recuperarPai(Funcionalidades funcionalidade);
	public List<Funcionalidades> recuperarFilhos();
	public List<Funcionalidades> recuperarPossiveisPais(Funcionalidades funcionalidade);
	public Funcionalidades incluir(Funcionalidades funcionalidade);
	public void alterar(Funcionalidades funcionalidade);
	public void remover(Funcionalidades funcionalidades);
	public void verificaExistente(Long idmodulo, String cnmfuncionalidade);
	public Integer getRowCount(Map<String,Object>filtros);
	public Funcionalidades getRowData(Object rowKey);
	public List<Funcionalidades> retornarPaginado(int firstRow, int numberOfRows, Map<String,Object>filtros);
	public List<Funcionalidades> retornarFuncionalidadesGrupos(Grupos grupo);
	public List<Funcionalidades> recuperarDiferentes(Modulos modulo, Grupos grupo);
	public List<Funcionalidades> retornarFuncionalidadesPessoas(Pessoas pessoa);
	public List<Funcionalidades> recuperarDiferentesPessoas(Modulos modulo, Pessoas pessoa);
	public boolean verificarPermissaoFuncionalidadePessoa(Funcionalidades func, Pessoas pessoa);
	public boolean verificarPermissaoFuncionalidadeGrupo(Funcionalidades func, Grupos grupo);
	boolean verificarPermissaoFuncionalidadePessoaModulo(Long idmodulo,
			Pessoas pessoa);
}
