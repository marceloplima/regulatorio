package br.com.telefonica.ssi.regulatorio.commom.interfaces.facade;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.CategoriaRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Procedencia;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Emails;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.CategoriaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.DemandaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ProcedenciaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.StatusRegulatorioService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.AreasInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.EmailsInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.PessoasInt;

@Local
public interface DemandaServiceFacade extends Serializable{

	DemandasRegulatorio criaNovaDemanda();

	DemandasRegulatorio novaDemanda();

	DemandasRegulatorio criaNovaDemanda(Pessoas autor);

	void salvaDemanda(DemandasRegulatorio demanda);

	DemandasRegulatorio recuperaDemanda(Integer id);

	String getEmailSolicitante(Pessoas pessoa);

	void setEmailSolicitante(Emails email);

	List<Areas> getAreasSolicitante(Pessoas pessoa);

	List<Procedencia> getProcedencias();

	List<Procedencia> getProcedenciasCategoriaDaDemanda(CategoriaRegulatorio categoria);

	List<Pessoas> pessoasAreaOrigem(Areas area);

	boolean solicitantePossuiEmail(Pessoas pessoa);

	DemandasRegulatorio alteraAreaDemandaPorIdArea(String id,DemandasRegulatorio demanda);

	void salvaNovoEmailSolicitante(Emails email);

	void salvarComoRascunho(DemandasRegulatorio demanda);

	DemandasRegulatorio encaminhar(DemandasRegulatorio demanda);

	public PessoasInt getPessoaService();

	public DemandaService getDemandaService();

	public EmailsInt getEmailService();

	public CategoriaService getCategoriaService();

	public ProcedenciaService getProcedenciaService();

	public AreasInt getAreasService();

	public StatusRegulatorioService getStatusService();

	List<DemandasRegulatorio> retornarPaginado(int firstRow, int numberOfRows, Map<String, Object> filtros, Pessoas pessoa);

	int getRowCount(Map<String, Object> filtros,Pessoas pessoa);

	DemandasRegulatorio getRowData(Object rowKey);
}
