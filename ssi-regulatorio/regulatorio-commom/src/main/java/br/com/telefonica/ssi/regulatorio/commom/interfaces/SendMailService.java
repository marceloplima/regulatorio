package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Local
public interface SendMailService extends Serializable{

	public void enviarMensagem(String strmensagem, String assunto,Map<String, String> listaemails,String ssi);

	public void notificarResponsavel(String strmensagem, String assunto,String ssi, DemandasRegulatorio demanda);

	public void notificaSolicitante(String strmensagem, String assunto,String ssi, DemandasRegulatorio demanda);

	public void notificaTecnicoEncarregado(String strmensagem, String assunto,String ssi, DemandasRegulatorio demanda);

	public void notificaEncarregadosOperacionais(String strmensagem, String assunto,String ssi, DemandasRegulatorio demanda);

	public void notificarResponsaveisTecnicos(String strmensagem, String assunto,String ssi, DemandasRegulatorio demanda);

	public String getEmailPessoa(Pessoas p);
}
