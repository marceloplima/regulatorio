package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Local
public interface AreasInt {

	public List<Areas> recuperar();
	public Areas recuperarUnico(Areas area);
	public boolean verificaExistente(Areas obj) throws IndexOutOfBoundsException;
	public List<Areas> recuperarFiltrado(Map<String, Object> filtros);
	public void incluir(Areas obj);
	public void alterar(Areas obj);
	Areas recuperaAreaPorSigla(String sigla) throws IndexOutOfBoundsException;
	public List<Pessoas> recuperarPessoasArea(Areas area);
	public List<Areas> retornarAreasOperacao();
		
}
