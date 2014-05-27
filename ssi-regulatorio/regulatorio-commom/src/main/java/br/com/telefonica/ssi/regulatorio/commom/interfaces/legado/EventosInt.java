package br.com.telefonica.ssi.regulatorio.commom.interfaces.legado;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.EventosRegulatorio;

@Local
public interface EventosInt {
	public List<EventosRegulatorio> recuperarEventos();
	public void registrar(EventosRegulatorio obj);
	public List<EventosRegulatorio> retornarPaginado(int firstRow, int numberOfRows, DemandasRegulatorio demanda);
	public EventosRegulatorio getRowData(Object rowKey);
	public Integer getRowCount(DemandasRegulatorio demanda);
	public EventosRegulatorio recuperarUltimoEventoDemanda(Long iddemanda);
}
