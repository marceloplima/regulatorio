package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.GruposModulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Local
public interface GruposModulosInt {

	public List<GruposModulos> recuperar();
	public GruposModulos recuperarUnico(GruposModulos grupo);
	public void alterar(GruposModulos gm);
	public boolean verificaExistente(GruposModulos grupomodulo) throws IndexOutOfBoundsException;
	public List<GruposModulos> recuperarFiltrado(Map<String, Object> filtros);
	public GruposModulos incluir(GruposModulos gm);
	public List<Pessoas> recuperaPessoasDoGrupo(GruposModulos grupoModulo);
	public GruposModulos recuperarPorNome(String nome);
}
