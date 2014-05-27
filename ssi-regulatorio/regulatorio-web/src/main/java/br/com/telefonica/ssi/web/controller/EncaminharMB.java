package br.com.telefonica.ssi.web.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.EventosRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Regionais;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.AreasInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.ModulosInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.legado.DemandasInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.legado.EventosInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.legado.ParticipantesInt;
import br.com.telefonica.ssi.web.beans.DemandasBean;
import br.com.telefonica.ssi.web.utils.ParametrosSistema;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@ManagedBean
@ViewScoped
public class EncaminharMB implements Serializable {

	private static final long serialVersionUID = 8607376112801472472L;

//	private static final String tituloLog = "Demanda encaminhada";
//	
//	@EJB
//	private DemandasInt demandaint;
//	
//	@EJB
//	private EventosInt eventosint;
//	
//	@EJB
//	private ParticipantesInt participanteint;
//	
//	@EJB
//	private AreasInt areasController;
//		
//	@EJB
//	private ModulosInt modulosint;
//	
//	private DemandasRegulatorio demanda;
//	private DemandasBean demandasmb = RecuperadorInstanciasBean.recuperarInstanciaDemandasBean();
//	
//	private EventosRegulatorio evento;
//	
//	private boolean mostrarConfirmaEncaminhar = false;
//	
//	// Mensagens
//	private static final String mensagemSSIEncaminhadaSucesso = "SSI encaminhada com sucesso";
//	
//	@PostConstruct
//	public void init(){
//		
//		if(demanda ==null){
//			//demanda = demandasmb.getDemandas();
//		}
//		else{
//			//demandasmb.setCnmtelefonesolicitante(demanda.getCnmtelefone());
//		}
//		
//		if(evento == null){
//			evento = new EventosRegulatorio();
//		}
//		
//	}
//	
//	public void preEncaminhar(){
//		
//		/*demanda.setRegionaisdemandas(new HashSet<Regionais>(demandasmb.getRegionaischecked()));
//		demanda.setCnmtelefone(demandasmb.getCnmtelefonesolicitante());
//		
//		if(demandasmb.validarCamposObrigatorios()){
//			mostrarTelaConfirmaEncaminharSSI();
//		}
//			*/					
//	}
//	
//	public void mostrarTelaConfirmaEncaminharSSI(){
//		mostrarConfirmaEncaminhar=true;
//	}
//	
//	public void fecharTelaConfirmaEncaminharSSI(){
//		mostrarConfirmaEncaminhar=false;
//	}
//	
//	public void encaminharSSI(){
		
		//System.out.println(">>encaminharSSI()<<");
		
//		List<Pessoas> copiados = new ArrayList<Pessoas>(demanda.getCopiados());
					
		/*if(demanda.getStatus().getId().equals(StatusRedes.ID_DEMANDA_RASCUNHO)){
			
			//System.out.println(">>ID_DEMANDA_RASCUNHO OK()<<");
			
			Calendar cal = Calendar.getInstance();
			 
			 DemandasRegulatorio ultimaDemandaComNumero = demandaint.recuperaUltimaDemandaComNumero();
			
			if(ultimaDemandaComNumero == null){
				ultimaDemandaComNumero = new DemandasRegulatorio();
//				ultimaDemandaComNumero.setNnrultimonumero(1);
			}*/
			
			/*demanda.setCnmnumero(StringUtils.leftPad(ultimaDemandaComNumero.getNnrultimonumero().toString(), 6, '0')
					+ "/"
					+ StringUtils.leftPad(Integer.toString(cal.get(Calendar.MONTH)+1), 2, '0')
					+ StringUtils.right(Integer.toString(cal.get(Calendar.YEAR)), 2));

			demanda.setNnrultimonumero(ultimaDemandaComNumero.getNnrultimonumero() + 1);
			
		}
		
		//demanda.setCnmtelefone(demandasmb.getCnmtelefonesolicitante());
		
		incluiLog();
		
		demanda.setStatus(new StatusRedes(StatusRedes.ID_DEMANDA_ANALISE_PELA_EXECUTORA));
		if(demanda.getIddemanda()==null){
			demanda = demandaint.persistir(demanda);	
		}else{
			demanda = demandaint.alterar(demanda);
		}
				
		//criaEventoEncaminhar();
		
		/*if(demanda.getIddemanda()!=null && demanda.getIddemanda()>=1)
			demandasmb.setDemandas(demandaint.recuperarDemandaEspecifica(demanda.getIddemanda()));*/
		
//		enviaEMailEncaminhar(copiados);
//		
//		
//				
//		fecharTelaConfirmaEncaminharSSI();*/
		
		//demandasmb.exibeMensagemSucesso(mensagemSSIEncaminhadaSucesso);
		
		//demandasmb.redirecionaParaNotes();
		
//	}
//	
//	private void incluiLog() {
//		//LogsRedes log = demandasmb.criaLog(1L, demandasmb.recuperarPessoaLogada(), tituloLog,demanda);
//		//log.setDemanda(demanda);
//		//demanda.setLogs(demandasmb.adicionaLogDemandas(demanda,log));
//	}
//	
//	private void enviaEMailEncaminhar(List<Pessoas> copiados) {
//		
//		if(demanda.getId()!=null && demanda.getId()>=1)
//			demanda = demandaint.recuperarDemandaEspecifica(demanda.getId());
//		
//		//System.out.println(">>> enviaEMailEncaminhar() <<<");
//		
//		//TODO Envio de email incompleto		
//		List<Pessoas> pessoasEnvio = new ArrayList<Pessoas>();
//		
//		//Pessoa logada
//		//pessoasEnvio.add(demandasmb.recuperarPessoaLogada());
//		
//		//Solicitante
//		pessoasEnvio.add(demanda.getAutor());
//		
//		//Envia para Faturamento, somente se tiver rota
////		if(demanda.getRotas() != null && demanda.getRotas().size()>=1){
////			pessoasEnvio.addAll(participanteint.recuperarFaturamento());
////		}
//		
//		//Envia para Copiados		
//		pessoasEnvio.addAll(copiados);
//		
//		//Envia para área requisitada
//		pessoasEnvio.addAll(areasController.recuperarPessoasArea(demanda.getOrigem()));
//		
//		//System.out.println(">>>>> lista: "+pessoasEnvio);
//		for(Pessoas p:pessoasEnvio){
//			System.out.println("Enviará email para: "+p.getCnmnome());
//		}
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
//		String prazoexec = sdf.format(demanda.getPrazo().getTime());
//		
//		ParametrosSistema param = new ParametrosSistema();
//		
//		String regionaisdemanda = "";
//		Iterator<Regionais> regionais = demandaint.recuperarRegionais(demanda).iterator();
//		while(regionais.hasNext()){
//			Regionais r = regionais.next();
//			regionaisdemanda+=" "+r.getCnmregional();
//		}
//				
//		String assunto = "Abertura de SSI Instrução "+demanda.getNumeroDemanda()()+" - "+demanda.getPrioridade().getCnmprioridade()+" -"+regionaisdemanda;
		
//		String strmensagem = "Encontra-se pendente de análise técnica pela área "+demanda.getArearequisitada().getCnmsiglaarea()+" a seguinte SSI de número: "+demanda.getCnmnumero()+"<P>";
		
//		strmensagem+="Área solicitante: "+demanda.getSolicitante().getArea().getCnmsiglaarea()+"<br>";
//		strmensagem+="Área requisitada: "+demanda.getSolicitante().getArea().getCnmsiglaarea()+"<br>";
//		strmensagem+="Prazo para Execução: "+prazoexec+"<br>";
////		strmensagem+="Título: "+demanda.getAtividade().getCnmatividade();
//		strmensagem+="<P>http://"+param.recuperaEnderecoIp()+"/REDES/?iddemanda="+demanda.getId()+"</p>";
//		strmensagem+="<P>Esta é uma mensagem gerada automaticamente pelo sistema, portanto não deve ser respondida.<br>";
		
		//System.out.println(strmensagem);
		
		//demandasmb.envioEMail(pessoasEnvio,assunto,strmensagem);				
		
//	}

//	private void criaEventoEncaminhar() {
//		
//		EventosRedes evento = new EventosRedes();
//		
//		evento.setAutor(demandasmb.recuperarPessoaLogada());
//		evento.setDemanda(demanda);
//		eventosint.registrar(evento);
//						
//	}

//	public boolean isMostrarConfirmaEncaminhar() {
//		return mostrarConfirmaEncaminhar;
//	}
//
//	public void setMostrarConfirmaEncaminhar(boolean mostrarConfirmaEncaminhar) {
//		this.mostrarConfirmaEncaminhar = mostrarConfirmaEncaminhar;
//	}	
//
//	public HttpServletRequest recuperarRequest(){
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		return request;
//	}

}
