package br.com.telefonica.ssi.web.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Transient;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.NovaDemanda;
import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.NovoAnexo;
import br.com.telefonica.ssi.regulatorio.commom.domain.AreasRegionais;
import br.com.telefonica.ssi.regulatorio.commom.domain.Complexidade;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Execucao;
import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAcionamentoArea;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseOperacional;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseTecnica;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoConclusao;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoFollowUp;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoRevisaoPrazo;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoTecnico;
import br.com.telefonica.ssi.regulatorio.commom.domain.ResultadoAnalise;
import br.com.telefonica.ssi.regulatorio.commom.domain.UF;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.AreaRegionalService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ComplexidadeService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.DocumentoOrigemService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ExecucaoService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.LogService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoFacade;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ResultadoAnaliseService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.SendMailService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.StatusRegulatorioService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.TipoDemandaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.TipoRedeService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.AreasInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.GruposModulosInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.PessoasInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.UfsInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.facade.DemandaServiceFacade;
import br.com.telefonica.ssi.web.controller.IndexMB;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@Named
@SessionScoped
public class ClassificacaoBean extends AbstractManagedBean{

	@EJB
	private MovimentoFacade movimentoService;

	@EJB
	private SendMailService mensageria;

	private Integer idMovimento;

	private MovimentoAnaliseOperacional analiseOperacional;

	@EJB
	private GruposModulosInt gruposModulosService;

	@Transient
	private List<UF> ufs = new ArrayList<UF>();

	private DemandasRegulatorio demanda;

	private ResultadoAnalise analise;

	private AreasRegionais areaRegional;

	private Date novoPrazo;

	private List<Areas> areasOperacionais;

	private Complexidade complexidade;

	private Execucao execucao;

	@EJB
	private AreasInt areasService;

	@EJB
	private ExecucaoService execucaoService;

	@EJB
	private ComplexidadeService complexidadeService;

	@EJB
	private ResultadoAnaliseService resultadoAnaliseService;

	private String comentario;

	private Pessoas responsavel;

	@EJB
	private LogService logger;

	@EJB
	private StatusRegulatorioService statusService;

	@Inject
	private Event<DemandasRegulatorio> eventoDemanda;

	@EJB
	private DemandaServiceFacade facadeDemanda;


	/**
	 *
	 */
	private static final long serialVersionUID = -2439580032775024054L;

	@EJB
	private AreaRegionalService areaRegionalService;

	@EJB
	private TipoDemandaService assuntoService;

	@EJB
	private UfsInt ufService;

	@EJB
	private TipoRedeService tipoRedeService;

	@EJB
	private PessoasInt pessoaService;

	@EJB
	private DocumentoOrigemService documentoService;

	public AreaRegionalService getRegionaisService() {
		return areaRegionalService;
	}

	public TipoDemandaService getAssuntoService() {
		return assuntoService;
	}

	public UfsInt getUfService() {
		return ufService;
	}

	public TipoRedeService getTipoRedeService() {
		return tipoRedeService;
	}

	public DocumentoOrigemService getDocumentoService() {
		return documentoService;
	}


	public void escutaNovaDemanda(@Observes @NovaDemanda DemandasRegulatorio demanda){
		this.demanda = demanda;
	}

	public DemandasRegulatorio getDemanda() {
		return demanda;
	}

	public void setDemanda(DemandasRegulatorio demanda) {
		this.demanda = demanda;
	}


	public void salvarDemanda(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		if(demanda.getTipoDemanda() == null || demanda.getAreaRegional() == null || demanda.getTipoRede() == null){
			List<String> messages = new ArrayList<String>();
			messages.add("Assunto, area regional, tipo de rede, documento de origem ou UF da demanda não informado! ");
			index.setMsgspanel(messages);
			index.setPanelexibeerro(true);
			return;
		}
		else{
//			List<UF> teste = ufs;
//			demanda.setUfs(ufs);
			facadeDemanda.salvaDemanda(demanda);
			logger.salvarLog(demanda, RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado(), "Demanda salva, em "+demanda.getStatus().getDescricao());
			index.setPanelexibesucesso(true);
			index.setMsgpanel("Demanda salva com sucesso!");
			eventoDemanda.fire(demanda);
		}
	}

	public void assumirDemanda(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		if(demanda.getTipoDemanda() == null || demanda.getAreaRegional() == null || demanda.getTipoRede() == null ){
			List<String> messages = new ArrayList<String>();
			messages.add("Assunto, area regional, tipo de rede, documento de origem ou UF da demanda não informado! ");
			index.setMsgspanel(messages);
			index.setPanelexibeerro(true);
			return;
		}
		else{
			this.demanda.setEncarregado(RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado());
			this.demanda.setStatus(facadeDemanda.getStatusService().findByName("ANÁLISE TÉCNICA"));
			facadeDemanda.salvaDemanda(demanda);

			logger.salvarLog(demanda, RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado(), "Demanda assumida por "+demanda.getEncarregado().getCnmnome()+" e enviada para "+demanda.getStatus().getDescricao());

			index.setMsgpanel("Operacão realizada com sucesso!");
			index.setPanelexibesucesso(true);

			eventoDemanda.fire(demanda);

			mensageria.notificaSolicitante("Demanda assumida por "+demanda.getEncarregado().getCnmnome(), "Demanda sob nova responsabilidade.", demanda.getNumeroDemanda(), demanda);
		}
	}

	public void cancelarDemanda(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		this.demanda.setStatus(facadeDemanda.getStatusService().findByName("CANCELADA"));
		facadeDemanda.salvaDemanda(demanda);

		index.setMsgpanel("Demanda cancelada!");
		index.setPanelexibesucesso(true);

		logger.salvarLog(demanda, RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado(), "Demanda cancelada.");

		mensageria.notificaSolicitante("Demanda cancelada por "+demanda.getEncarregado().getCnmnome(), "Demanda cancelada!.", demanda.getNumeroDemanda(), demanda);
	}

	public List<Pessoas> getTecnicosResponsaveis(){
		List<Pessoas> result = new ArrayList<Pessoas>();

		for(Pessoas p : gruposModulosService.recuperaPessoasDoGrupo(gruposModulosService.recuperarPorNome(demanda.getCategoria().getDescricao()))){
			if(p.getCargo().getCnmcargo().equalsIgnoreCase("colaborador")){
				result.add(p);
			}
		}
		return result;
	}


	public void listenerDemanda(@Observes DemandasRegulatorio demanda){
		this.demanda = demanda;
		//this.ufs = demanda.getUfs();
	}

	public void listenerDemandaNovoAnexo(@Observes @NovoAnexo DemandasRegulatorio demanda){
		this.demanda = facadeDemanda.recuperaDemanda(demanda.getId());
		eventoDemanda.fire(demanda);
		//this.ufs = demanda.getUfs();
	}

	public AreasRegionais getAreaRegional() {
		return areaRegional;
	}

	public void setAreaRegional(AreasRegionais areaRegional) {
		this.areaRegional = areaRegional;
	}

	public Date getNovoPrazo() {
		return novoPrazo;
	}

	public void setNovoPrazo(Date novoPrazo) {
		this.novoPrazo = novoPrazo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void revisarPrazo(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		if(demanda.getTipoDemanda() == null || demanda.getTipoRede() == null){
			index.setMsgpanel("Assunto, area regional, tipo de rede, documento de origem ou UF da demanda não informado! ");
			index.setPanelexibeerro(true);
		}
		else{
			Movimento movimento = new Movimento();
			MovimentoRevisaoPrazo revisao = new MovimentoRevisaoPrazo();

			movimento.setAutor(RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado());
			movimento.setComentario(comentario);
			movimento.setDataHora(new Date());
			movimento.setDemanda(demanda);

			movimentoService.salvaMovimento(movimento);

			revisao.setMovimento(movimento);
			revisao.setPrazo(novoPrazo);

			movimentoService.salvaMovimentoRevisaoPrazo(revisao);

			demanda.setEncarregado(RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado());
			if(demanda.getStatus().getDescricao().equalsIgnoreCase("REVISÃO DE PRAZO")){
				demanda.setStatus(statusService.findByName("ANÁLISE TÉCNICA"));
				mensageria.notificarResponsavel("Prazo revisto e enviado para analise tecnica.", "Prazo revisto e enviado para analise tecnica.", demanda.getNumeroDemanda(), demanda);
				logger.salvarLog(demanda, RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado(), "Prazo revisto e enviado para analise tecnica.");
			}
			else{
				demanda.setStatus(statusService.findByName("REVISÃO DE PRAZO"));
				logger.salvarLog(demanda, RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado(), "Solicitada revisão de prazo para a demanda.");
				mensageria.notificaSolicitante("Solicitada revisão de prazo por "+demanda.getEncarregado().getCnmnome(), "Solicitada revisão de prazo para a demanda.", demanda.getNumeroDemanda(), demanda);
			}

			facadeDemanda.salvaDemanda(demanda);

			eventoDemanda.fire(demanda);

			index.setMsgpanel("Operacão realizada com sucesso!");
			index.setPanelexibesucesso(true);
			this.comentario = null;
			this.novoPrazo = null;
		}
	}

	public Pessoas getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoas responsavel) {
		this.responsavel = responsavel;
	}

	public void definirTecnico(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		if(demanda.getTipoDemanda() == null || demanda.getAreaRegional() == null || demanda.getTipoRede() == null){
			index.setMsgpanel("Assunto, area regional, tipo de rede, documento de origem ou UF da demanda não informado! ");
			index.setPanelexibeerro(true);
		}
		else{
			demanda.setEncarregado(this.responsavel);
			demanda.setStatus(statusService.findByName("ANÁLISE TÉCNICA"));

			facadeDemanda.salvaDemanda(demanda);

			Movimento movimento = new Movimento();
			MovimentoTecnico movimentoTecnico = new MovimentoTecnico();

			movimento.setAutor(RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado());
			movimento.setComentario(comentario);
			movimento.setDataHora(new Date());
			movimento.setDemanda(demanda);
			movimentoService.salvaMovimento(movimento);

			movimentoTecnico.setEncarregado(responsavel);
			movimentoTecnico.setMovimento(movimento);
			movimentoService.salvaMovimentoTecnico(movimentoTecnico);

			logger.salvarLog(demanda, RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado(), "Definido técnico técnico "+demanda.getEncarregado().getCnmnome()+" para a demanda e enviada para "
					+ demanda.getStatus().getDescricao());

			mensageria.notificaSolicitante("Definido técnico "+demanda.getEncarregado().getCnmnome()+" para a demanda.", "Definição de encarregado técnico", demanda.getNumeroDemanda(), demanda);

			mensageria.notificaTecnicoEncarregado("Definido técnico "+demanda.getEncarregado().getCnmnome()+" para a demanda.", "Definição de encarregado técnico", demanda.getNumeroDemanda(), demanda);

			eventoDemanda.fire(demanda);

			index.setMsgpanel("Operacão realizada com sucesso!");
			index.setPanelexibesucesso(true);

			this.responsavel = null;

			this.comentario = null;
		}
	}

	public void realizarAnaliseTecnica(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		Movimento movimento = new Movimento();
		MovimentoAnaliseTecnica analiseTecnica = new MovimentoAnaliseTecnica();

		movimento.setAutor(getLogado());
		movimento.setComentario(comentario);
		movimento.setDataHora(new Date());
		movimento.setDemanda(demanda);

		analiseTecnica.setComplexidade(complexidade);
		analiseTecnica.setEsclarecimentos(comentario);
		analiseTecnica.setMovimento(movimento);
		analiseTecnica.setPrevisao(novoPrazo);
		analiseTecnica.setExecucao(execucao);
		analiseTecnica.setResultadoAnalise(analise);

		if(analiseTecnica.getResultadoAnalise()==null || analiseTecnica.getComplexidade()==null || analiseTecnica.getPrevisao()==null || analiseTecnica.getExecucao()==null || analiseTecnica.getEsclarecimentos()==null){
			List<String> messages = new ArrayList<String>();
			if(analiseTecnica.getResultadoAnalise()==null){
				messages.add("Resultado da análise é um campo obrigatório!");
				index.setMsgspanel(messages);
				index.setPanelexibeerro(true);
				return;
			}
			if(analiseTecnica.getComplexidade()==null){
				messages.add("Complexidade é um campo obrigatório!");
				messages.add("Resultado da análise é um campo obrigatório!");
				index.setMsgspanel(messages);
				index.setPanelexibeerro(true);
				return;
			}
			if(analiseTecnica.getPrevisao()==null){
				messages.add("Previsão é um campo obrigatório!");
				messages.add("Resultado da análise é um campo obrigatório!");
				index.setMsgspanel(messages);
				index.setPanelexibeerro(true);
				return;
			}
			if(analiseTecnica.getPrevisao()==null && !analiseTecnica.getResultadoAnalise().getDescricao().equalsIgnoreCase("Em Atendimento")){
				messages.add("O campo previsão é obrigatório!");
				messages.add("Resultado da análise é um campo obrigatório!");
				index.setMsgspanel(messages);
				index.setPanelexibeerro(true);
				return;
			}
			if(analiseTecnica.getExecucao()==null && !analiseTecnica.getResultadoAnalise().getDescricao().equalsIgnoreCase("Atendimento Concluído")){
				messages.add("O campo execução é obrigatório!");
				messages.add("Resultado da análise é um campo obrigatório!");
				index.setMsgspanel(messages);
				index.setPanelexibeerro(true);
				return;
			}
			if(!analiseTecnica.getResultadoAnalise().getDescricao().equalsIgnoreCase("Atendimento Concluído") && !analiseTecnica.getResultadoAnalise().getDescricao().equalsIgnoreCase("Em Atendimento")){
				messages.add("O campo comentério é obrigatório!");
				messages.add("Resultado da análise é um campo obrigatório!");
				index.setMsgspanel(messages);
				index.setPanelexibeerro(true);
				return;
			}
		}
		else{
			demanda.setEncarregado(getLogado());

			if(analise.getDescricao().equalsIgnoreCase("Atendimento Concluído")){
				demanda.setStatus(statusService.findByName("CONCLUÍDA"));
				logger.salvarLog(demanda, getLogado(), "Demanda concluída.");
				mensageria.notificaSolicitante("Demanda concluida.", "Demanda concluida", demanda.getNumeroDemanda(), demanda);
				mensageria.notificarResponsavel("Demanda concluida", "Demanda concluida", demanda.getNumeroDemanda(), demanda);
			}
			if(analise.getDescricao().equalsIgnoreCase("Em Atendimento")){
				demanda.setStatus(statusService.findByName("EM ATENDIMENTO"));
				logger.salvarLog(demanda, getLogado(), "Demanda em atendimento.");
				mensageria.notificaSolicitante("Demanda em atendimento.", "Demanda em atendimento", demanda.getNumeroDemanda(), demanda);
				mensageria.notificarResponsavel("Demanda em atendimento", "Demanda em atendimento", demanda.getNumeroDemanda(), demanda);
			}
			if(analise.getDescricao().equalsIgnoreCase("Necessita Dados")){
				demanda.setStatus(statusService.findByName("PENDENTE DE DADOS"));
				logger.salvarLog(demanda, getLogado(), "Demanda pendente de dados.");
				mensageria.notificaSolicitante("Demanda pendente de dados.", "Demanda pendente de dados.", demanda.getNumeroDemanda(), demanda);
				mensageria.notificarResponsavel("Demanda pendente de dados.", "Demanda pendente de dados.", demanda.getNumeroDemanda(), demanda);
			}
			if(analise.getDescricao().equalsIgnoreCase("Necessita Esclarecimentos")){
				demanda.setStatus(statusService.findByName("PENDENTE DE ESCLARECIMENTOS"));
				logger.salvarLog(demanda, getLogado(), "Demanda pendente de esclarecimentos.");
				mensageria.notificaSolicitante("Demanda pendente de esclarecimentos.", "Demanda pendente de esclarecimentos.", demanda.getNumeroDemanda(), demanda);
				mensageria.notificarResponsavel("Demanda pendente de esclarecimentos.", "Demanda pendente de esclarecimentos.", demanda.getNumeroDemanda(), demanda);
			}
			if(analise.getDescricao().equalsIgnoreCase("Revisão do Prazo")){
				demanda.setStatus(statusService.findByName("REVISÃO DE PRAZO"));
				logger.salvarLog(demanda, getLogado(), "Solicitada a revisão de prazo para a demanda.");
				mensageria.notificaSolicitante("Solicitada a revisão de prazo para a demanda.", "Solicitada a revisão de prazo para a demanda.", demanda.getNumeroDemanda(), demanda);
				mensageria.notificarResponsavel("Solicitada a revisão de prazo para a demanda.", "Solicitada a revisão de prazo para a demanda.", demanda.getNumeroDemanda(), demanda);
			}

			movimentoService.salvaMovimento(movimento);
			movimentoService.salvaMovimentoAnaliseTecnica(analiseTecnica);
			facadeDemanda.salvaDemanda(demanda);

			eventoDemanda.fire(demanda);

			index.setMsgpanel("Operacão realizada com sucesso!");
			index.setPanelexibesucesso(true);


			this.comentario = null;
			this.complexidade = null;
			this.analise = null;
			this.execucao = null;
		}
	}

	public void registraFollowUp(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		Movimento movimento = new Movimento();
		MovimentoFollowUp movimentoFollowUp = new MovimentoFollowUp();

		movimento.setAutor(RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado());
		movimento.setComentario(comentario);
		movimento.setDataHora(new Date());
		movimento.setDemanda(demanda);
		movimentoService.salvaMovimento(movimento);

		movimentoFollowUp.setMovimento(movimento);
		movimentoService.salvarFollowUp(movimentoFollowUp);

		logger.salvarLog(demanda, RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado(), "Registrado follow up para a demanda");

		mensageria.notificaSolicitante(movimento.getComentario(), "Registrado follow up", demanda.getNumeroDemanda(), demanda);

		eventoDemanda.fire(demanda);

		index.setMsgpanel("Operacão realizada com sucesso!");
		index.setPanelexibesucesso(true);

		this.comentario = null;
	}

	public ResultadoAnalise getAnalise() {
		return analise;
	}

	public void setAnalise(ResultadoAnalise analise) {
		this.analise = analise;
	}

	public Complexidade getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(Complexidade complexidade) {
		this.complexidade = complexidade;
	}

	public List<UF> getUfs() {
		return ufs;
	}

	public void setUfs(List<UF> ufs) {
		this.ufs = ufs;
	}

	public Pessoas getLogado(){
		return RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado();
	}

	public ResultadoAnaliseService getResultadoAnaliseService() {
		return resultadoAnaliseService;
	}

	public void setResultadoAnaliseService(
			ResultadoAnaliseService resultadoAnaliseService) {
		this.resultadoAnaliseService = resultadoAnaliseService;
	}

	public ComplexidadeService getComplexidadeService() {
		return complexidadeService;
	}

	public List<Areas> getAreasOperacionais(){
		return areasService.retornarAreasOperacao();
	}

	public void setComplexidadeService(ComplexidadeService complexidadeService) {
		this.complexidadeService = complexidadeService;
	}

	public Execucao getExecucao() {
		return execucao;
	}

	public void setExecucao(Execucao execucao) {
		this.execucao = execucao;
	}

	public ExecucaoService getExecucaoService() {
		return execucaoService;
	}

	public void setExecucaoService(ExecucaoService execucaoService) {
		this.execucaoService = execucaoService;
	}

	public void concluirDemanda(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		Movimento movimento = new Movimento();
		MovimentoConclusao conclusao = new MovimentoConclusao();

		movimento.setAutor(getLogado());
		movimento.setComentario(comentario);
		movimento.setDataHora(new Date());
		movimento.setDemanda(demanda);

		conclusao.setComplexidade(complexidade);
		conclusao.setExecucao(execucao);
		conclusao.setMovimento(movimento);

		demanda.setEncarregado(getLogado());

		demanda.setStatus(statusService.findByName("CONCLUÍDA"));
		logger.salvarLog(demanda, getLogado(), "Demanda concluída.");
		mensageria.notificaSolicitante("Demanda concluida.", "Demanda concluida", demanda.getNumeroDemanda(), demanda);

		movimentoService.salvaMovimento(movimento);
		movimentoService.salvaMovimentoConclusao(conclusao);
		facadeDemanda.salvaDemanda(demanda);

		eventoDemanda.fire(demanda);

		index.setMsgpanel("Operacão realizada com sucesso!");
		index.setPanelexibesucesso(true);


		this.comentario = null;
		this.complexidade = null;
		this.analise = null;
		this.execucao = null;
	}

	public List<Areas> getAreaOperacional() {
		return areasOperacionais;
	}

	public void setAreaOperacional(List<Areas> areasOperacionais) {
		this.areasOperacionais = areasOperacionais;
	}

	public void acionarAreasOperacionais(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		Movimento movimento = new Movimento();
		MovimentoAcionamentoArea acionamentoOperacional = new MovimentoAcionamentoArea();

		if(areasOperacionais == null || areasOperacionais.size()<1){
			List<String> messages = new ArrayList<String>();
			messages.add("O campo áreas operacionais é obrigatório!");
			index.setMsgspanel(messages);
			index.setPanelexibeerro(true);
			return;
		}
		else {
			movimento.setAutor(getLogado());
			movimento.setComentario(comentario);
			movimento.setDataHora(new Date());
			movimento.setDemanda(demanda);

			movimentoService.salvaMovimento(movimento);

			acionamentoOperacional.setMovimento(movimento);

			Collection<Areas> areas = new ArrayList<Areas>();
			for(Areas a:areasOperacionais){
				Areas area = areasService.recuperarUnico(a);
				areas.add(area);
			}

			acionamentoOperacional.setAreasOperacionais(areas);

			movimentoService.salvaMovimentoAcionamentoArea(acionamentoOperacional);

			demanda.setEncarregado(getLogado());
			demanda.setStatus(statusService.findByName("ANÁLISE OPERACIONAL"));
			facadeDemanda.salvaDemanda(demanda);

			// --- NOTIFICAÇÔES ---
			logger.salvarLog(demanda, getLogado(), "Áreas operacionais acionadas. Demanda enviada para "+demanda.getStatus().getDescricao());

			for(Areas a:areas){
				for(Pessoas p:a.getAreaspessoas()){
					if(p.getCargo()!=null && p.getCargo().getCnmcargo().equalsIgnoreCase("gestor")){
						Map<String, String> emails = new HashMap<String, String>();
						emails.put(p.getCnmnome(), mensageria.getEmailPessoa(responsavel));
						mensageria.enviarMensagem("Acionamento de área operacional para a demanda "
								+ demanda.getNumeroDemanda(), "Acionamento de área operacional para a demanda "+demanda.getNumeroDemanda(),
								emails, demanda.getNumeroDemanda());
					}
				}
			}
			this.areasOperacionais = null;
			this.comentario=null;

			eventoDemanda.fire(demanda);

			index.setMsgpanel("Operação realizada com sucesso!");
			index.setPanelexibesucesso(true);
		}
	}

	public void realizarAnaliseOperacional(){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();

		Movimento movimento = new Movimento();
		MovimentoAnaliseOperacional analise = new MovimentoAnaliseOperacional();

		if(comentario == null || comentario.equals("")){
			List<String> mensagens = new ArrayList<String>();
			mensagens.add("Campo comentário é obrigtório!");
			index.setMsgspanel(mensagens);
			index.setPanelexibeerro(true);
			return;
		}
		if(getLogado().getArea()==null){
			List<String> mensagens = new ArrayList<String>();
			mensagens.add("Usuário não cadastrado em área operacional acionada nesta demanda!");
			index.setMsgspanel(mensagens);
			index.setPanelexibeerro(true);
			return;
		}
		else{
			movimento.setAutor(getLogado());
			movimento.setDataHora(new Date());
			movimento.setComentario(comentario);
			movimento.setDemanda(demanda);

			movimentoService.salvaMovimento(movimento);

			analise.setMovimento(movimento);
			analise.setAreaOperacional(getLogado().getArea());

			movimentoService.salvaMovimentoAnaliseOperacional(analise);

			// === Verificar se esta é a última área operacional acionada

			List<MovimentoAcionamentoArea> movimentosAcionamentos = movimentoService.movimentosAcionamentosPorDemanda(demanda);
			if(movimentosAcionamentos!=null && movimentosAcionamentos.size()>1){
				List<Areas> areas = new ArrayList<Areas>(movimentosAcionamentos.get(0).getAreasOperacionais());
				if(areas.contains(getLogado().getArea())){
					List<MovimentoAnaliseOperacional> analises = movimentoService.analisesOperacionaisPorDemanda(demanda);
					if(analises!=null){
						if(analises.size()<1){
							demanda.setStatus(statusService.findByName("ANÁLISE FINAL"));
						}
						else{
							boolean areaFound = false;
							for(MovimentoAnaliseOperacional m:analises){
								if(m.getAreaOperacional().getId() == getLogado().getId()){
									areaFound = true;
								}
							}
							if(!areaFound){
								demanda.setStatus(statusService.findByName("ANÁLISE FINAL"));
							}
						}
					}
				}
			}
			logger.salvarLog(demanda, getLogado(), "Nova análise operacional realizada.");
			facadeDemanda.salvaDemanda(demanda);

			mensageria.notificarResponsaveisTecnicos("Análise operacional da demanda "+demanda.getNumeroDemanda()+": \n "+comentario, "Análise operacional da demanda "+demanda.getNumeroDemanda(), demanda.getNumeroDemanda(), demanda);

			index.setMsgpanel("Operação realizada com sucesso!");
			index.setPanelexibesucesso(true);
			this.comentario = null;
			eventoDemanda.fire(demanda);
		}
	}

	public String getIdMovimento() {
		return idMovimento.toString();
	}

	public void setIdMovimento(String idMovimento) {
		this.idMovimento = new Integer(idMovimento);
	}

	public MovimentoAnaliseOperacional getAnaliseOperacional() {
		return analiseOperacional;
	}

	public void setAnaliseOperacional(MovimentoAnaliseOperacional analiseOperacional) {
		this.analiseOperacional = analiseOperacional;
	}

	public void buscarAnaliseOperacional(){
		if(getIdMovimento()!=null && !getIdMovimento().equals("")){
			this.analiseOperacional = movimentoService.getAnaliseOperacionalPorId(new Integer(getIdMovimento()));
		}
	}
}
