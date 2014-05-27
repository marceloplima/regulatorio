package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface DemandaService extends CrudService<DemandasRegulatorio, Integer>{

	public String getNumeroNovaDemanda();

	public List<DemandasRegulatorio> getMinhasDemandas(Pessoas autor);

	public List<DemandasRegulatorio> getDemandasEncarregado(Pessoas encarregado);

	public List<DemandasRegulatorio> retornarPaginado(int firstRow, int numberOfRows, Map<String,Object> filtros);

	List<DemandasRegulatorio> recuperarDemandasCriadasPeloAutor(Pessoas pessoa);

	List<DemandasRegulatorio> recuperarDemandasEstaoComPessoaLogada(Pessoas pessoa);

	public DemandasRegulatorio getRowData(Object rowKey);

	public Integer getRowCount(Map<String,Object> filtros);

	List<DemandasRegulatorio> getDemandasAVerComigo(Pessoas pessoa);
}
