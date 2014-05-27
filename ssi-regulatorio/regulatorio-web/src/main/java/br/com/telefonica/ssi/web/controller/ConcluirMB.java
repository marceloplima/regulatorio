package br.com.telefonica.ssi.web.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ConcluirMB implements Serializable {

	private static final long serialVersionUID = 8719583482839210489L;

	private static final String tituloLog = "Demanda concluída";
	private static final int statusconcluir = 6;
	
	/*@EJB(lookup = "java:global/ssi-ear/redes-service/SLSBDemanda!telefonica.ssi.kernel.redes.interfaces.DemandasRedesInt")
	private DemandasIntInt demandaInt;
	
	@EJB(lookup = "java:global/ssi-ear/redes-service/SLSBEventos!telefonica.ssi.kernel.redes.interfaces.EventosRedesInt")
	private EventosIntInt eventosint;
	
	@EJB(lookup = "java:global/ssi-ear/redes-service/SLSBParticipante!telefonica.ssi.kernel.redes.interfaces.ParticipantesRedesInt")
	private ParticipantesIntInt participanteInt;
	
	@EJB(lookup = "java:global/ssi-ear/redes-service/SLSBPareceres!telefonica.ssi.kernel.redes.interfaces.PareceresRedesInt")
	private PareceresRedesInt pareceresController;
	
	@EJB(lookup = "java:global/ssi-ear/redes-service/SLSBLogs!telefonica.ssi.kernel.redes.interfaces.LogsRedesInt")
	private LogsRedesInt logsInt;
	
	@EJB(lookup="global/modulos")
	private ModulosInt modulosint;
	
	@EJB(lookup="global/areas")
	private AreasInt areasController;
	
	private DemandasRedes demanda;
	private DemandasBean demandasmb = RecuperadorInstanciasBean.recuperarInstanciaDemandasBean();
	
	private EventosRedes evento;
		
	private List<PareceresRedes> pareceresRedes = new ArrayList<PareceresRedes>();
	
	private IndexMB indexmb = RecuperadorInstanciasBean.recuperarInstanciaIndexBean();
	
	private boolean mostrarConcluirSSI = false;
	private boolean mostrarConfirmaConcluirSSI = false;
	
	private List<Pessoas> responsaveisTecnicos;

	@PostConstruct
	public void init(){
		
		if(demanda ==null){
		//	demanda = demandasmb.getDemandas();
		}		
		
		if(evento == null){
			evento = new EventosRedes(statusconcluir);
		}
		
		pareceresRedes = pareceresController.recuperarPorStatus(new StatusRedes(StatusRedes.ID_DEMANDA_EM_EXECUCAO));
		responsaveisTecnicos = areasController.recuperarPessoasArea(demanda.getArearequisitada());
	}
	
	public void preConcluir(){
		if(validaDadosConcluirSSI()){
			mostrarTelaConfirmarSSI();	
		}
				
	}
	
	public void mostrarTelaConfirmarSSI(){
		mostrarConfirmaConcluirSSI=true;
	}
	
	public void fecharTelaConfirmarConclusaoSSI(){
		mostrarConfirmaConcluirSSI=false;
	}
	
	public void efetuarConclusao(){
		concluirSSI();
		enviarEmail();
		
	}
	
	private void concluirSSI(){

		demanda.setPessoacomquemesta(null);
		demanda.setStatusanterior(demanda.getStatus());
		
		demanda.setStatus(new StatusRedes(StatusRedes.ID_DEMANDA_CONCLUIDA));
		
		incluirLog();
		salvaEventoConcluir();
		
		demanda = demandaInt.alterar(demanda);	
		
		demanda.setLogs(new LinkedHashSet<LogsRedes>(logsInt.recuperarLogsDemanda(demanda)));
		
		//demandasmb.setDemandas(demandaInt.recuperarDemandaEspecifica(demanda.getIddemanda()));		
				
		indexmb.setMsgpanel("Conclu�do com sucesso");
		indexmb.setPanelexibesucesso(true);
		
		fecharTelaConfirmarConclusaoSSI();
		fecharConcluirSSI();
	}
	
	private void incluirLog() {
	
		//LogsRedes novoLog = demandasmb.criaLog(TiposLogRedes.ID_DEMANDA,demandasmb.recuperarPessoaLogada(), tituloLog,demanda);
		
		//demanda.setLogs(demandasmb.adicionaLogDemandas(demanda,novoLog));		
		
	}
	
	private void enviarEmail() {
		
		demanda = demandaInt.recuperarDemandaEspecifica(demanda.getIddemanda());
		
		String complemento = "";
		String strexecsucesso = "";
		
		List<Pessoas> pessoasEnvio = new ArrayList<Pessoas>();
		pessoasEnvio = responsaveisTecnicos;		
		pessoasEnvio.add(demanda.getAutor());
		
		SimpleDateFormat sdfH = new SimpleDateFormat("dd/M/yyyy");
		Date d = new Date();
		String dataformat = sdfH.format(d);
		
		switch (evento.getParecer().getIdparecer().intValue())	
		{
			case (int) PareceresRedes.ID_EXECUCAO_TOTAL:
				complemento = "execução Total";
				strexecsucesso = "SIM";
			break;
			
			case (int) PareceresRedes.ID_EXECUCAO_PARCIAL:
				complemento = "execução Parcial";
				strexecsucesso = "SIM";
			break;
			
			case (int) PareceresRedes.ID_SEM_SUCESSO:
				complemento = "execução Sem Sucesso";
				strexecsucesso = "NÃO";
			break;
		}
		
		pessoasEnvio.addAll(demanda.getCopiados());
		
		ParametrosSistema param = new ParametrosSistema();
		
		String regionaisdemanda = "";
		Iterator<Regionais> regionais = demandaInt.recuperarRegionais(demanda).iterator();
		while(regionais.hasNext()){
			Regionais r = regionais.next();
			regionaisdemanda+=" "+r.getCnmregional();
		}
		
		String assunto = "Conclusão da SSI "+demanda.getCnmnumero()+" em "+dataformat+" "+demanda.getPrioridade().getCnmprioridade()+" -"+regionaisdemanda;
		
		String strmensagem = "A seguinte SSI número: "+demanda.getCnmnumero()+" teve sua "+complemento+"</br></br>";
		
		strmensagem+="Área solicitante: "+demanda.getAreasolicitante().getCnmsiglaarea()+"<br/>";
		strmensagem+="Área requisitada: "+demanda.getArearequisitada().getCnmsiglaarea()+"<br/>";
		strmensagem+="Título da SSI: "+demanda.getAtividade().getCnmatividade()+"<br/>";
		strmensagem+="<P>Execução com sucesso: "+strexecsucesso+"</P>";
		
		strmensagem+="<P>http://"+param.recuperaEnderecoIp()+"/REDES/?iddemanda="+demanda.getIddemanda()+"</p>";
		
		strmensagem+="<P>Esta é uma mensagem gerada automaticamente pelo sistema, portanto não deve ser respondida.<br>";
		
		//demandasmb.envioEMail(pessoasEnvio, assunto, strmensagem);
						
	}

	private void salvaEventoConcluir() {
		
		//evento.setAutor(demandasmb.recuperarPessoaLogada());
		evento.setDemanda(demanda);
		evento.setStatus(new StatusRedes(StatusRedes.ID_DEMANDA_CONCLUIDA));
		evento.getErc().setEventorc(evento); // Relaciona inversamente
		
		eventosint.registrar(evento);
						
	}	

	private boolean validaDadosConcluirSSI() {	
			
		if(evento.getParecer() == null){
			//demandasmb.devolveErroParaTela("formconcluir:parecerconclusao","O parecer deve ser preenchido");
			return false;
		}
		
		if(evento.getParecer().getIdparecer().equals(PareceresRedes.ID_EXECUCAO_PARCIAL)){
			if(StringUtils.isBlank(evento.getCnmcomentario())){
				//demandasmb.devolveErroParaTela("formconcluir:comentarioconclusao","O campo comentário é obrigatório");
				return false;
			}	
		}
				
		return true;
	}
	
	
	public void mostrarConcluirSSI(){
		mostrarConcluirSSI = true;
	}
	
	public void fecharConcluirSSI(){
		mostrarConcluirSSI = false;
	}
	
	/*Getter e Setter*/		
	
	
	
//	public boolean isMostrarConfirmaConcluirSSI() {
//		return mostrarConfirmaConcluirSSI;
//	}
//
//	public EventosRedes getEvento() {
//		return evento;
//	}
//
//	public void setEvento(EventosRedes evento) {
//		this.evento = evento;
//	}
//
//	public List<PareceresRedes> getPareceresRedes() {
//		return pareceresRedes;
//	}
//
//	public void setPareceresRedes(List<PareceresRedes> pareceresRedes) {
//		this.pareceresRedes = pareceresRedes;
//	}
//
//	public boolean isMostrarConcluirSSI() {
//		return mostrarConcluirSSI;
//	}
//
//	public void setMostrarConcluirSSI(boolean mostrarConcluirSSI) {
//		this.mostrarConcluirSSI = mostrarConcluirSSI;
//	}
//
//	public void setMostrarConfirmaConcluirSSI(boolean mostrarConfirmaConcluirSSI) {
//		this.mostrarConfirmaConcluirSSI = mostrarConfirmaConcluirSSI;
//	}
//	
//	public HttpServletRequest recuperarRequest(){
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		return request;
//	}
//
//	public static int getStatusconcluir() {
//		return statusconcluir;
//	}
//	*/
}
