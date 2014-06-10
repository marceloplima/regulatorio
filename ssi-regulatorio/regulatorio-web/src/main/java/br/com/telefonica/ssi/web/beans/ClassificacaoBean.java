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
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Transient;

import br.com.telefonica.ssi.faces.bean.AbstractManagedBean;
import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.NovaDemanda;
import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.NovoAnexo;
import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.PopupDemanda;
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

	@Inject @PopupDemanda
	private Event<DemandasRegulatorio> eventoPopup;

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
		if(validaDemanda()){
			demanda.setUfs(ufs);
			facadeDemanda.salvaDemanda(demanda);
			logger.salvarLog(demanda, getLogado(), "Demanda salva, em "+demanda.getStatus().getDescricao());
			exibeMensagemSucesso("Demanda salva com sucesso!");
			eventoDemanda.fire(demanda);
		}
	}

	public void assumirDemanda(){
		this.demanda.setUfs(ufs);
		if(validaDemanda()){
			this.demanda.setUfs(this.ufs);
			this.demanda.setEncarregado(getLogado());
			this.demanda.setStatus(facadeDemanda.getStatusService().findByName("ANÁLISE TÉCNICA"));
			facadeDemanda.salvaDemanda(demanda);

			logger.salvarLog(demanda, getLogado(), "Demanda assumida por "+demanda.getEncarregado().getCnmnome()+" e enviada para "+demanda.getStatus().getDescricao());

			exibeMensagemSucesso("Operacão realizada com sucesso!");

			eventoDemanda.fire(demanda);

			mensageria.notificaSolicitante("Demanda assumida por "+demanda.getEncarregado().getCnmnome(), "Demanda sob nova responsabilidade.", demanda.getNumeroDemanda(), demanda);
		}
	}

	public void cancelarDemanda(){
		this.demanda.setStatus(facadeDemanda.getStatusService().findByName("CANCELADA"));
		facadeDemanda.salvaDemanda(demanda);

		exibeMensagemSucesso("Demanda cancelada!");

		logger.salvarLog(demanda, getLogado(), "Demanda cancelada.");

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
		if(demanda.getUfs()!=null && !demanda.getUfs().isEmpty()){
			this.ufs = new ArrayList<UF>(demanda.getUfs());
		}
		else{
			this.ufs = new ArrayList<UF>();
		}
	}

	public void listenerDemandaNovoAnexo(@Observes @NovoAnexo DemandasRegulatorio demanda){
		this.demanda = facadeDemanda.recuperaDemanda(demanda.getId());
		if(demanda.getUfs()!=null && !demanda.getUfs().isEmpty()){
			this.ufs = new ArrayList<UF>(demanda.getUfs());
		}
		else{
			this.ufs = new ArrayList<UF>();
		}
		eventoDemanda.fire(demanda);
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
		MovimentoRevisaoPrazo ultimaRevisao = movimentoService
				.getUtimaRevisaoPrazo(demanda);

		Movimento movimento = new Movimento();
		MovimentoRevisaoPrazo revisao = new MovimentoRevisaoPrazo();

		movimento.setAutor(getLogado());
		movimento.setComentario(comentario);
		movimento.setDataHora(new Date());
		movimento.setDemanda(demanda);

		movimentoService.salvaMovimento(movimento);

		revisao.setMovimento(movimento);
		revisao.setPrazo(novoPrazo);
		revisao.setStatus(demanda.getStatus());

		movimentoService.salvaMovimentoRevisaoPrazo(revisao);

		if (demanda.getUfs() == null || demanda.getUfs().isEmpty()) {
			demanda.setUfs(this.ufs);
		}

		if (demanda.getStatus().getDescricao()
				.equalsIgnoreCase("REVISÃO DE PRAZO")) {
			if (ultimaRevisao.getMovimento() == null) {
				demanda.setStatus(statusService.findByName("ANÁLISE TÉCNICA"));
				demanda.setEncarregado(demanda.getAutor());
				demanda.setPrazo(novoPrazo);
			} else {
				demanda.setPrazo(novoPrazo);
				demanda.setEncarregado(ultimaRevisao.getMovimento().getAutor());
				demanda.setStatus(ultimaRevisao.getStatus());
			}
			mensageria.notificarResponsavel(
					"Prazo revisto e enviado para analise tecnica.",
					"Prazo revisto e enviado para analise tecnica.",
					demanda.getNumeroDemanda(), demanda);
			logger.salvarLog(demanda, getLogado(),
					"Prazo revisto e enviado para analise tecnica.");
		} else {
			demanda.setEncarregado(demanda.getSolicitante());
			demanda.setStatus(statusService.findByName("REVISÃO DE PRAZO"));
			logger.salvarLog(demanda, getLogado(),
					"Solicitada revisão de prazo para a demanda.");
			demanda.setPrazo(novoPrazo);
			mensageria.notificaSolicitante("Solicitada revisão de prazo por "
					+ demanda.getEncarregado().getCnmnome(),
					"Solicitada revisão de prazo para a demanda.",
					demanda.getNumeroDemanda(), demanda);
		}

		facadeDemanda.salvaDemanda(demanda);

		eventoDemanda.fire(demanda);

		exibeMensagemSucesso("Operação realizada com sucesso!");

		this.comentario = null;
		this.novoPrazo = null;
	}

	public Pessoas getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoas responsavel) {
		this.responsavel = responsavel;
	}

	public void definirTecnico(){
		demanda.setUfs(ufs);
		if(validaDemanda()){
			demanda.setEncarregado(pessoaService.recuperarUnico(this.responsavel.getId()));
			demanda.setStatus(statusService.findByName("ANÁLISE TÉCNICA"));

			facadeDemanda.salvaDemanda(demanda);

			Movimento movimento = new Movimento();
			MovimentoTecnico movimentoTecnico = new MovimentoTecnico();

			movimento.setAutor(getLogado());
			movimento.setComentario(comentario);
			movimento.setDataHora(new Date());
			movimento.setDemanda(demanda);
			movimentoService.salvaMovimento(movimento);

			movimentoTecnico.setEncarregado(responsavel);
			movimentoTecnico.setMovimento(movimento);
			movimentoService.salvaMovimentoTecnico(movimentoTecnico);

			logger.salvarLog(demanda, getLogado(), "Definido técnico técnico "+demanda.getEncarregado().getCnmnome()+" para a demanda e enviada para "
					+ demanda.getStatus().getDescricao());

			mensageria.notificaSolicitante("Definido técnico "+demanda.getEncarregado().getCnmnome()+" para a demanda.", "Definição de encarregado técnico", demanda.getNumeroDemanda(), demanda);

			mensageria.notificaTecnicoEncarregado("Definido técnico "+demanda.getEncarregado().getCnmnome()+" para a demanda.", "Definição de encarregado técnico", demanda.getNumeroDemanda(), demanda);

			eventoDemanda.fire(demanda);

			exibeMensagemSucesso("Operacão realizada com sucesso!");

			this.responsavel = null;

			this.comentario = null;
		}
	}

	public void realizarAnaliseTecnica() {
		if(demanda.getUfs()== null || demanda.getUfs().isEmpty()){
			demanda.setUfs(ufs);
		}
		if (validaDemanda()) {
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

			if (analiseTecnica.getResultadoAnalise() == null
					|| analiseTecnica.getComplexidade() == null
					|| analiseTecnica.getPrevisao() == null
					|| analiseTecnica.getExecucao() == null
					|| analiseTecnica.getEsclarecimentos() == null) {
				if (analiseTecnica.getResultadoAnalise() == null) {
					exibeMensagemDeErro("Resultado da análise é um campo obrigatório!");
					return;
				}
				if (analiseTecnica.getComplexidade() == null) {
					exibeMensagemDeErro("Complexidade é um campo obrigatório!");
					return;
				}
				if (analiseTecnica.getPrevisao() == null) {
					exibeMensagemDeErro("Previsão é um campo obrigatório!");
					return;
				}
				if (analiseTecnica.getPrevisao() == null
						&& !analiseTecnica.getResultadoAnalise().getDescricao()
								.equalsIgnoreCase("Em Atendimento")) {
					exibeMensagemDeErro("O campo previsão é obrigatório!");
					return;
				}
				if (analiseTecnica.getExecucao() == null
						&& !analiseTecnica.getResultadoAnalise().getDescricao()
								.equalsIgnoreCase("Atendimento Concluído")) {
					exibeMensagemDeErro("O campo execução é obrigatório!");
					return;
				}
				if (!analiseTecnica.getResultadoAnalise().getDescricao()
						.equalsIgnoreCase("Atendimento Concluído")
						&& !analiseTecnica.getResultadoAnalise().getDescricao()
								.equalsIgnoreCase("Em Atendimento")) {
					exibeMensagemDeErro("O campo comentário é obrigatório!");
					return;
				}
			} else {
				demanda.setEncarregado(getLogado());

				if (analise.getDescricao().equalsIgnoreCase(
						"Atendimento Concluído")) {
					demanda.setStatus(statusService.findByName("CONCLUÍDA"));
					logger.salvarLog(demanda, getLogado(), "Demanda concluída.");
					mensageria.notificaSolicitante("Demanda concluida.",
							"Demanda concluida", demanda.getNumeroDemanda(),
							demanda);
					mensageria.notificarResponsavel("Demanda concluida",
							"Demanda concluida", demanda.getNumeroDemanda(),
							demanda);
				}
				if (analise.getDescricao().equalsIgnoreCase("Em Atendimento")) {
					demanda.setStatus(statusService
							.findByName("EM ATENDIMENTO"));
					logger.salvarLog(demanda, getLogado(),
							"Demanda em atendimento.");
					mensageria.notificaSolicitante("Demanda em atendimento.",
							"Demanda em atendimento",
							demanda.getNumeroDemanda(), demanda);
					mensageria.notificarResponsavel("Demanda em atendimento",
							"Demanda em atendimento",
							demanda.getNumeroDemanda(), demanda);
				}
				if (analise.getDescricao().equalsIgnoreCase("Necessita Dados")) {
					demanda.setStatus(statusService
							.findByName("PENDENTE DE DADOS"));
					logger.salvarLog(demanda, getLogado(),
							"Demanda pendente de dados.");
					mensageria.notificaSolicitante(
							"Demanda pendente de dados.",
							"Demanda pendente de dados.",
							demanda.getNumeroDemanda(), demanda);
					mensageria.notificarResponsavel(
							"Demanda pendente de dados.",
							"Demanda pendente de dados.",
							demanda.getNumeroDemanda(), demanda);
				}
				if (analise.getDescricao().equalsIgnoreCase(
						"Necessita Esclarecimentos")) {
					demanda.setStatus(statusService
							.findByName("PENDENTE DE ESCLARECIMENTOS"));
					logger.salvarLog(demanda, getLogado(),
							"Demanda pendente de esclarecimentos.");
					mensageria.notificaSolicitante(
							"Demanda pendente de esclarecimentos.",
							"Demanda pendente de esclarecimentos.",
							demanda.getNumeroDemanda(), demanda);
					mensageria.notificarResponsavel(
							"Demanda pendente de esclarecimentos.",
							"Demanda pendente de esclarecimentos.",
							demanda.getNumeroDemanda(), demanda);
				}
				if (analise.getDescricao().equalsIgnoreCase("Revisão do Prazo")) {

					Movimento movimentoRevisao = new Movimento();
					movimentoRevisao.setAutor(getLogado());
					movimentoRevisao.setDataHora(new Date());
					movimentoRevisao.setDemanda(demanda);
					movimentoRevisao.setComentario("Solicitação de revisão de prazo realizada por "+getLogado().getCnmnome());

					MovimentoRevisaoPrazo revisao = new MovimentoRevisaoPrazo();
					revisao.setStatus(demanda.getStatus());
					revisao.setPrazo(demanda.getPrazo());

					demanda.setStatus(statusService
							.findByName("REVISÃO DE PRAZO"));
					demanda.setEncarregado(demanda.getSolicitante());

					movimentoService.salvaMovimento(movimentoRevisao);
					revisao.setMovimento(movimentoRevisao);
					movimentoService.salvaMovimentoRevisaoPrazo(revisao);

					logger.salvarLog(demanda, getLogado(),
							"Solicitada a revisão de prazo para a demanda.");
					mensageria.notificaSolicitante(
							"Solicitada a revisão de prazo para a demanda.",
							"Solicitada a revisão de prazo para a demanda.",
							demanda.getNumeroDemanda(), demanda);
					mensageria.notificarResponsavel(
							"Solicitada a revisão de prazo para a demanda.",
							"Solicitada a revisão de prazo para a demanda.",
							demanda.getNumeroDemanda(), demanda);
				}

				movimentoService.salvaMovimento(movimento);
				movimentoService.salvaMovimentoAnaliseTecnica(analiseTecnica);
				facadeDemanda.salvaDemanda(demanda);

				eventoDemanda.fire(demanda);

				exibeMensagemSucesso("Operacão realizada com sucesso!");

				this.comentario = null;
				this.complexidade = null;
				this.novoPrazo = null;
				this.analise = null;
				this.execucao = null;
			}
		}
	}

	public void registraFollowUp(){
		if(validaDemanda()){
			Movimento movimento = new Movimento();
			MovimentoFollowUp movimentoFollowUp = new MovimentoFollowUp();

			movimento.setAutor(getLogado());
			movimento.setComentario(comentario);
			movimento.setDataHora(new Date());
			movimento.setDemanda(demanda);
			movimentoService.salvaMovimento(movimento);

			movimentoFollowUp.setMovimento(movimento);
			movimentoService.salvarFollowUp(movimentoFollowUp);

			logger.salvarLog(demanda, getLogado(), "Registrado follow up para a demanda");

			mensageria.notificaSolicitante(movimento.getComentario(), "Registrado follow up", demanda.getNumeroDemanda(), demanda);

			eventoDemanda.fire(demanda);

			exibeMensagemSucesso("Operacão realizada com sucesso!");
			this.comentario = null;
		}
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
		if(complexidade == null){
			exibeMensagemDeErro("Campo complexidade é obrigatório!");
			return;
		}
		if(execucao == null){
			exibeMensagemDeErro("Campo execução é obrigatório!");
			return;
		}

		Movimento movimento = new Movimento();
		MovimentoConclusao conclusao = new MovimentoConclusao();

		movimento.setAutor(getLogado());
		movimento.setComentario(comentario);
		movimento.setDataHora(new Date());
		movimento.setDemanda(demanda);

		conclusao.setComplexidade(complexidade);
		conclusao.setExecucao(execucao);
		conclusao.setMovimento(movimento);

		demanda.setStatus(statusService.findByName("CONCLUÍDA"));
		logger.salvarLog(demanda, getLogado(), "Demanda concluída.");

		mensageria.notificaSolicitante("Demanda concluida.", "Demanda concluida", demanda.getNumeroDemanda(), demanda);

		mensageria.notificaTecnicoEncarregado("Demanda concluida.", "Demanda concluida", demanda.getNumeroDemanda(), demanda);

		Collection<Areas> areas = null;

		MovimentoAcionamentoArea acionamento = movimentoService.retornaUltimoAcionamentoAreaOperacional(demanda);

		areas = acionamento.getAreasOperacionais();

		for(Areas a:areas){
			for(Pessoas p:a.getAreaspessoas()){
				if(p.getCargo()!=null && p.getCargo().getCnmcargo().equalsIgnoreCase("colaborador")){
					Map<String, String> emails = new HashMap<String, String>();
					if (p.getPessoaemails() != null
							&& !p.getPessoaemails().isEmpty()) {
						emails.put(p.getCnmnome(),
								mensageria.getEmailPessoa(p));
						mensageria.enviarMensagem(
								"Demanda concluida: "
										+ demanda.getNumeroDemanda(),
										"Demanda concluida: "
										+ demanda.getNumeroDemanda(),
								emails, demanda.getNumeroDemanda());
					}
				}
			}
		}

		movimentoService.salvaMovimento(movimento);
		movimentoService.salvaMovimentoConclusao(conclusao);
		facadeDemanda.salvaDemanda(demanda);

		eventoDemanda.fire(demanda);

		exibeMensagemSucesso("Demanda concluída com sucesso!");

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
		Movimento movimento = new Movimento();
		MovimentoAcionamentoArea acionamentoOperacional = new MovimentoAcionamentoArea();

		if(areasOperacionais == null || areasOperacionais.size()<1){
			exibeMensagemDeErro("O campo áreas operacionais é obrigatório!");
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
					if(p.getCargo()!=null && p.getCargo().getCnmcargo().equalsIgnoreCase("colaborador")){
						Map<String, String> emails = new HashMap<String, String>();
						if (p.getPessoaemails() != null
								&& !p.getPessoaemails().isEmpty()) {
							emails.put(p.getCnmnome(),
									mensageria.getEmailPessoa(p));
							mensageria.enviarMensagem(
									"Acionamento de área operacional para a demanda "
											+ demanda.getNumeroDemanda(),
									"Acionamento de área operacional para a demanda "
											+ demanda.getNumeroDemanda(),
									emails, demanda.getNumeroDemanda());
						}
					}
				}
			}
			this.areasOperacionais = null;
			this.comentario=null;

			eventoDemanda.fire(demanda);
			exibeMensagemSucesso("Operação realizada com sucesso!");
		}
	}

	public void realizarAnaliseOperacional(){
		Movimento movimento = new Movimento();
		MovimentoAnaliseOperacional analise = new MovimentoAnaliseOperacional();

		if(comentario == null || comentario.equals("")){
			exibeMensagemDeErro("Campo comentário é obrigtório!");
			return;
		}
		if(getLogado().getArea()==null){
			exibeMensagemDeErro("Usuário não cadastrado em área operacional acionada nesta demanda!");
			return;
		}
		else{
			// === Verificar se esta é a última área operacional acionada

			MovimentoAcionamentoArea movimentoAcionamento = movimentoService.retornaUltimoAcionamentoAreaOperacional(demanda);
			if(movimentoAcionamento!=null){
				List<Areas> areas = new ArrayList<Areas>(movimentoAcionamento.getAreasOperacionais());
				if(areas.contains(getLogado().getArea())){
					int acionamentosDaArea = movimentoService.analisesOperacionaisPorArea(getLogado().getArea(), demanda);
					if(acionamentosDaArea == 0){
						movimento.setAutor(getLogado());
						movimento.setDataHora(new Date());
						movimento.setComentario(comentario);
						movimento.setDemanda(demanda);

						analise.setMovimento(movimento);
						analise.setAreaOperacional(getLogado().getArea());

						logger.salvarLog(demanda, getLogado(), "Nova análise operacional realizada.");

						movimentoService.salvaMovimento(movimento);
						movimentoService.salvaMovimentoAnaliseOperacional(analise);

						int qtdAcionamentos = movimentoService.analisesOperacionaisPorDemanda(demanda).size();

						if(qtdAcionamentos >= areas.size()){
							demanda.setStatus(statusService.findByName("ANÁLISE FINAL"));
							logger.salvarLog(demanda, getLogado(), "Última area operacional acionada efetuou análise. Demanda enviada para "
									+demanda.getStatus().getDescricao());
						}

						facadeDemanda.salvaDemanda(demanda);

						mensageria.notificarResponsaveisTecnicos("Análise operacional da demanda "+demanda.getNumeroDemanda()+": \n "+comentario, "Análise operacional da demanda "+demanda.getNumeroDemanda(), demanda.getNumeroDemanda(), demanda);
						this.comentario = null;
					}
					else{
						exibeMensagemDeErro("Área acionada já efetuou análise operacional");
						this.comentario = null;
						return;
					}
				}
				else{
					exibeMensagemDeErro("A área do usuário "+getLogado().getCnmnome()+" não foi acionada nesta demanda.");
					this.comentario = null;
					return;
				}
			}

			exibeMensagemSucesso("Operação realizada com sucesso!");
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

	public void trocaAreaRegional(ValueChangeEvent evt){
		AreasRegionais area = (AreasRegionais)evt.getNewValue();
		if(area!=null && area.getDescricao().equalsIgnoreCase("Brasil")){
			this.ufs = new ArrayList<UF>(area.getUfs());
		}
		else{
			if(demanda.getUfs()!=null && demanda.getUfs().size()>0){
				this.ufs = new ArrayList<UF>(demanda.getUfs());
			}
			else{
				this.ufs = new ArrayList<UF>();
			}
		}
	}

	public void exibeMensagemDeErro(String msg){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		List<String> messages = new ArrayList<String>();
		messages.add(msg);
		index.setMsgspanel(messages);
		index.setPanelexibeerro(true);
	}
	public void exibeMensagemSucesso(String msg){
		IndexMB index = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
		index.setMsgpanel(msg);
		index.setPanelexibesucesso(true);
	}

	public void solicitarRevisaoPrazo(){
		demanda.setUfs(ufs);
		if(validaDemanda()){
			Movimento movimento = new Movimento();
			movimento.setAutor(getLogado());
			movimento.setDataHora(new Date());
			movimento.setDemanda(demanda);
			movimento.setComentario("Solicitação de revisão de prazo realizada por "+getLogado().getCnmnome());

			MovimentoRevisaoPrazo revisao = new MovimentoRevisaoPrazo();
			revisao.setMovimento(movimento);
			revisao.setStatus(demanda.getStatus());
			revisao.setPrazo(demanda.getPrazo());

			demanda.setStatus(statusService.findByName("REVISÃO DE PRAZO"));
			demanda.setEncarregado(demanda.getSolicitante());

			facadeDemanda.salvaDemanda(demanda);

			movimentoService.salvaMovimento(movimento);
			movimentoService.salvaMovimentoRevisaoPrazo(revisao);

			mensageria.notificaSolicitante("Solicitada revisão de prazo por "+demanda.getEncarregado().getCnmnome(), "Solicitada revisão de prazo para a demanda.", demanda.getNumeroDemanda(), demanda);

			logger.salvarLog(demanda, getLogado(), "Solicitada revisão de prazo para a demanda.");

			eventoDemanda.fire(demanda);

			exibeMensagemSucesso("Operação realizada com sucesso!");
		}
	}

	public boolean validaDemanda(){
		if(demanda.getTipoDemanda() == null){
			exibeMensagemDeErro("Assunto não informado! ");
			return false;
		}
		if(demanda.getAreaRegional() == null){
			exibeMensagemDeErro("Área regional não informada! ");
			return false;
		}
		if(demanda.getDocumentoOrigem() == null){
 			exibeMensagemDeErro("Documento de origem não informado! ");
			return false;
		}
		if(demanda.getTipoRede() == null){
			exibeMensagemDeErro("Tipo de rede não informada! ");
			return false;
		}
		if(demanda.getUfs()==null || demanda.getUfs().isEmpty()){
			exibeMensagemDeErro("UFs não informadas! ");
			return false;
		}
		else{
			return true;
		}
	}
}
