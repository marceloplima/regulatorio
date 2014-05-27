package br.com.telefonica.ssi.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.telefonica.ssi.regulatorio.commom.domain.AnexosRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.AreasInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.legado.PermissoesInt;
import br.com.telefonica.ssi.web.beans.DemandasBean;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;

@ManagedBean
@ViewScoped
public class PermissoesMB implements Serializable{	
	
	private static final long serialVersionUID = 8150454244775550747L;
	
//	@EJB
//	private PermissoesInt permissoesInt;
//	
//	@EJB
//	private AreasInt areasController;
//	
//	private DemandasRegulatorio demanda;
//	
//	private DemandasBean demandasmb = RecuperadorInstanciasBean.recuperarInstanciaDemandasBean();
//	
//	@PostConstruct
//	public void init() {
//		if(demanda == null){
//		//	demanda = demandasmb.getDemandasRedes();
//			if(demanda == null){
//				demanda = new DemandasRegulatorio();
//				//demanda.setIddemanda(StatusRedes.ID_DEMANDA_INICIAL);
//				//demanda.setAutor(demandasmb.recuperarPessoaLogada());
//			}
//		}
//	}
//	
//	public boolean verificarDemandaPersistida(){
//		/*if(demandasmb.getDemandas() != null || demandasmb.getDemandas().getIddemanda()>=1){
//			return true;
//		}*/
//		return false;
//	}
//	
//	public boolean verificarPermissoesEmissor(){
//		if(verificarDemandaPersistida()){
//			boolean permitido = false;
//			
//			List<Long>statuspossiveis = new ArrayList<Long>();
////			statuspossiveis.add(StatusRedes.ID_DEMANDA_INICIAL);
////			statuspossiveis.add(StatusRedes.ID_DEMANDA_RASCUNHO);
//			
//			//boolean papel = permissoesInt.verificarPermissaoEmissor(demandasmb.recuperarPessoaLogada());
//			
////			boolean statuspermite = (statuspossiveis.contains(demanda.getStatus().getIdstatus()));
//				
//			/*if(papel && statuspermite){
//				permitido = true;
//			}
//				
//			return permitido;*/
//		}
//		return true;
//	}
//	
//	public boolean verificarCancelarSSI(){
//		if(verificarDemandaPersistida()){
//			List<Long>statusNaoPossiveis = new ArrayList<Long>();
////			statusNaoPossiveis.add(StatusRedes.ID_DEMANDA_INICIAL);
////			statusNaoPossiveis.add(StatusRedes.ID_DEMANDA_CANCELADA);
////			statusNaoPossiveis.add(StatusRedes.ID_DEMANDA_CONCLUIDA);
//			
////			return !statusNaoPossiveis.contains(demanda.getStatus().getIdstatus());
//		}
//		return true;
//	}
//	
//
//	public boolean verificarCopiados(){
//		return verificarPermissoesEmissor();
//	}
//	
//	public boolean verificarEncaminharSSI(){
//		return verificarPermissoesEmissor();
//	}
//	
//	public boolean verificarAnaliseAreaRequisitada(){
//		
//		if(!verificarDemandaPersistida()){
//			return false;
//		}
//		
//		List<Long>statuspossiveis = new ArrayList<Long>();
////		statuspossiveis.add(StatusRedes.ID_DEMANDA_ANALISE_PELA_EXECUTORA);
//						
////		return statuspossiveis.contains(demanda.getStatus().getIdstatus()) 
////				&& isParticipanteDaAreaRequisitada();		
//		return true;
//		
//	}
//	
//	private boolean isParticipanteDaAreaRequisitada() {		
//		//return areasController.recuperarPessoasArea(demanda.getArearequisitada()).contains(demandasmb.recuperarPessoaLogada());
//		return true;
//	}
//
//	public boolean verificarConclusao(){
//		if(!verificarDemandaPersistida()){
//			return false;
//		}
//		
//		List<Long>statuspossiveis = new ArrayList<Long>();
////		statuspossiveis.add(StatusRedes.ID_DEMANDA_EM_EXECUCAO);
//						
////		return statuspossiveis.contains(demanda.getStatus().getIdstatus()) 
////				&& isParticipanteDaAreaRequisitada();
//		return true;
//	}
//	
//	public boolean verificarInformacoesComplementares(){
//		
//		List<Long>statuspossiveis = new ArrayList<Long>();
////		statuspossiveis.add(StatusRedes.ID_DEMANDA_REVISAO_PELO_EMISSOR);
//
//		/*return statuspossiveis.contains(demanda.getStatus().getIdstatus()) 
//				&& isEmissor(demandasmb.recuperarPessoaLogada());*/
//		return true;
//		
//	}
//	
//	public boolean editaCamposDadosDeAberturaDemanda(){
//		
//		List<Long>statuspossiveis = new ArrayList<Long>();
////		statuspossiveis.add(StatusRedes.ID_DEMANDA_REVISAO_PELO_EMISSOR);
////		statuspossiveis.add(StatusRedes.ID_DEMANDA_INICIAL);
////		statuspossiveis.add(StatusRedes.ID_DEMANDA_RASCUNHO);
//						
//		/*return statuspossiveis.contains(demanda.getStatus().getIdstatus()) 
//				&& isEmissor(demandasmb.recuperarPessoaLogada());*/
//		return true;
//		
//	}
//	
//	private boolean isEmissor(Pessoas pessoaLogada) {						
//		/*return demanda.getAutor().equals(pessoaLogada) 
//				&& permissoesInt.verificarPermissaoEmissor(demandasmb.recuperarPessoaLogada());*/
//		return true;
//	}
//	
//	public boolean verificarPodeRemoverAnexo(AnexosRegulatorio anexo){
//		/*if(anexo.getAutor().getIdpessoa() == demandasmb.recuperarPessoaLogada().getIdpessoa()){
//			return true;
//		}
//		
//		return false;*/
//		return true;
//	}

}
