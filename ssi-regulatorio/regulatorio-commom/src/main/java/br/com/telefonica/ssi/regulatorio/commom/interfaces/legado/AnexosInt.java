package br.com.telefonica.ssi.regulatorio.commom.interfaces.legado;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.AnexosRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;

@Local
public interface AnexosInt {

	public void incluir(AnexosRegulatorio anexo);
	public AnexosRegulatorio getRowData(Object rowKey);
	public Integer getRowCount(Map<String,Object> filtros);
	public List<AnexosRegulatorio> retornarPaginado(int firstRow, int numberOfRows, Map<String,Object> filtros);
	public AnexosRegulatorio retornarUltimaMinutaDemanda(DemandasRegulatorio demanda);
	public void remover(AnexosRegulatorio anexo);
	
}
