package br.com.telefonica.ssi.regulatorio.commom.interfaces.legado;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.GruposModulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Local
public interface ParticipantesInt {
	List<Pessoas> recuperarEmissores();
	List<Pessoas> recuperarRequisitados();
	List<Pessoas> recuperarCopiados();
	List<GruposModulos> recuperarGrupoEmissores(boolean ativo);
	List<GruposModulos> recuperarGrupoRequisitados(boolean ativo);
	List<GruposModulos> recuperarGrupoCopiados(boolean ativo);
	List<String> recuperarAutocompleteRespTecnico(String autobusca);
	List<Pessoas> recuperarResponsaveisTecnicos();
	List<Pessoas> recuperarPessoasGruposCopiados(boolean ativo);
	List<Pessoas> recuperarFaturamento();	
}
