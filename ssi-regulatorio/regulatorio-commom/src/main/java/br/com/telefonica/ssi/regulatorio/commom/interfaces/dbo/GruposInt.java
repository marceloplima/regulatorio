package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Funcionalidades;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Grupos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Local
public interface GruposInt {

	public List<Grupos> recuperar();
	public Grupos recuperarUnico(Grupos grupo);
	public boolean verificaExistente(Grupos obj) throws IndexOutOfBoundsException;
	public List<Funcionalidades> retornarGruposFuncionalidades(Grupos obj);
	public List<Grupos> recuperarFiltrado(Map<String, Object> filtros);
	public void incluir(Grupos obj);
	public void alterar(Grupos obj);
	public Grupos recuperarPorNome(String nome);
	public Pessoas recuperarResponsavel(Grupos grupo);
	public List<Pessoas> recuperarResponsaveisTecnicos(Grupos grupo);
}
