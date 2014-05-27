package br.com.telefonica.ssi.regulatorio.commom.interfaces.legado;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Regionais;

@Local
public interface DemandasInt {
	
	DemandasRegulatorio recuperaUltimaDemandaComNumero();
	List<DemandasRegulatorio> recuperar();	
	public DemandasRegulatorio getRowData(Object rowKey);
	public Integer getRowCount(Map<String,Object> filtros);
	public List<DemandasRegulatorio> retornarPaginado(int firstRow, int numberOfRows, Map<String,Object> filtros);
	public DemandasRegulatorio recuperarDemandaEspecifica(Integer iddemandaeditar);
	List<DemandasRegulatorio> recuperarDemandasCriadasPeloAutor(Pessoas pessoa);
	List<DemandasRegulatorio> recuperarDemandasEstaoComPessoaLogada(Pessoas pessoa);
	DemandasRegulatorio persistir(DemandasRegulatorio demanda);
	DemandasRegulatorio alterar(DemandasRegulatorio demanda);
	void incluir(DemandasRegulatorio demanda);
	List<Regionais> recuperarRegionais(DemandasRegulatorio demandas);
	DemandasRegulatorio recuperaRegistroDaAreaDeTransferencia(String universalId);
	List<DemandasRegulatorio> recuperaDemandaPeloUniversalId(String universalId);
	DemandasRegulatorio recuperaDemandaPeloNumeroSSINotes(String numeroSSI);
	
}
