package br.com.telefonica.ssi.regulatorio.service.mail;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.ConfigSMTP;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Emails;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.GruposModulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Modulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.SendMailService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.GruposModulosInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.ModulosInt;

@Stateless
public class Mensageria implements SendMailService {

	/**
	 *
	 */
	private static final long serialVersionUID = 7321044911370029104L;

	@PersistenceContext
	private EntityManager em;

	@EJB
	private ModulosInt moduloService;

	private Modulos modulo;

	private ConfigSMTP config;

	@EJB
	private GruposModulosInt gruposModulosService;

	@Override
	public void enviarMensagem(String strmensagem, String assunto,
			Map<String, String> listaemails, String ssi) {

		send(strmensagem, assunto, listaemails, getConfig().getCnmipsmtp(), getModulo().getCnmmodulo(), ssi);
	}

	@Override
	public void notificarResponsavel(String strmensagem, String assunto,
			String ssi, DemandasRegulatorio demanda) {
		GruposModulos gmodulos = gruposModulosService.recuperarPorNome(demanda.getCategoria().getDescricao());

		Pessoas responsavel = null;

		List<Pessoas> pessoas = gmodulos.getGruposmodulospessoas();

		Iterator<Pessoas> it = pessoas.iterator();

		while(it.hasNext()){
			Pessoas p = it.next();
			if(p.getCargo().getCnmcargo().equalsIgnoreCase("gestor")){
				responsavel = p;
				break;
			}
		}
		if(responsavel ==null){
			return;
		}

		Map<String, String> emails = new HashMap<String, String>();

		emails.put(responsavel.getCnmnome(), getEmailPessoa(responsavel));

		send(strmensagem, assunto, emails, getConfig().getCnmipsmtp(), getModulo().getCnmmodulo(), demanda.getNumeroDemanda());
	}

	@Override
	public void notificaSolicitante(String strmensagem, String assunto,
			String ssi, DemandasRegulatorio demanda) {

		Map<String, String> emails = new HashMap<String, String>();

		emails.put(demanda.getSolicitante().getCnmnome(), getEmailPessoa(demanda.getSolicitante()));

		send(strmensagem, assunto, emails, getConfig().getCnmipsmtp(), getModulo().getCnmmodulo(), demanda.getNumeroDemanda());

	}

	@Override
	public void notificaTecnicoEncarregado(String strmensagem, String assunto,
			String ssi, DemandasRegulatorio demanda) {
		Map<String, String> emails = new HashMap<String, String>();

		emails.put(demanda.getEncarregado().getCnmnome(), getEmailPessoa(demanda.getEncarregado()));

		send(strmensagem, assunto, emails, getConfig().getCnmipsmtp(), getModulo().getCnmmodulo(), demanda.getNumeroDemanda());
	}

	@Override
	public void notificaEncarregadosOperacionais(String strmensagem,
			String assunto, String ssi, DemandasRegulatorio demanda) {



	}

	@Override
	public void notificarResponsaveisTecnicos(String strmensagem,
			String assunto, String ssi, DemandasRegulatorio demanda) {

		GruposModulos gmodulos = gruposModulosService.recuperarPorNome(demanda.getCategoria().getDescricao());

		Map<String, String> emails = new HashMap<String, String>();

		List<Pessoas> pessoas = gmodulos.getGruposmodulospessoas();

		Iterator<Pessoas> it = pessoas.iterator();

		while(it.hasNext()){
			Pessoas p = it.next();
			if(p.getCargo().getCnmcargo().equalsIgnoreCase("colaborador")){
				emails.put(p.getCnmnome(), getEmailPessoa(p));
			}
		}

		send(strmensagem, assunto, emails, getConfig().getCnmipsmtp(), getModulo().getCnmmodulo(), demanda.getNumeroDemanda());
	}

	public void send(String strmensagem, String assunto,
			Map<String, String> listaemails, String smtpserver, String modulo,
			String ssi) {

		if (ssi == null || ssi.isEmpty() || ssi.equals("")) {
			ssi = "SEM NÚMERO";
		}

		if (listaemails.size() >= 1) {
			for (Map.Entry<String, String> mapa : listaemails.entrySet()) {

				InitialContext ic;
				try {
					ic = new InitialContext();

					ConnectionFactory cf = (ConnectionFactory) ic
							.lookup("/ConnectionFactory");
					Queue q = (Queue) ic.lookup("/queue/QueueSSI");

					javax.jms.Connection jmsconnection = cf.createConnection();
					Session session = jmsconnection.createSession(false,
							Session.AUTO_ACKNOWLEDGE);
					MessageProducer producer = session.createProducer(q);
					jmsconnection.start();

					TextMessage message = session
							.createTextMessage(strmensagem);
					message.setStringProperty("emaildest", mapa.getValue());
					message.setStringProperty("nomedest", mapa.getKey());
					message.setStringProperty("emailremet",
							"ssi@jms.mailer.com");
					message.setStringProperty("nomeremet", "SSI");
					message.setStringProperty("assunto", assunto);
					message.setStringProperty("smtpserver", smtpserver);
					message.setStringProperty("modulo", modulo);
					message.setStringProperty("ssi", ssi);


					producer.send(message);

					jmsconnection.close();

					ic = null;
					cf = null;
					q = null;
					jmsconnection = null;
					session = null;
					producer = null;
					message = null;
					mapa = null;

				} catch (NamingException e) {
					e.printStackTrace();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}

			listaemails = null;
			assunto = null;
			strmensagem = null;
		}
	}

	private ConfigSMTP getConfig(){
		if(config == null){
			config = getModulo().getConfigsmtp();
		}
		return config;
	}

	private Modulos getModulo(){
		if(modulo == null){
			modulo = moduloService.recuperarUnico(338L);
		}
		return modulo;
	}

	@Override
	public String getEmailPessoa(Pessoas p){
		List<Emails> emails = p.getPessoaemails();

		if(emails != null && emails.size()>0){
			return emails.get(0).getCnmemail();
		}

		else{
			return null;
		}
	}

	@Override
	public String getAssunto(DemandasRegulatorio demanda) {
		return demanda.getStatus().getDescricao() + " da SSI " + demanda.getNumeroDemanda() + " - " + demanda.getProcedencia() + " - PRAZO: " + demanda.getPrazo();
	}

	@Override
	public String getCorpo(DemandasRegulatorio demanda) {

		StringBuilder sb = new StringBuilder();

		sb.append("Encontra-se disponível parecer técnico da área " + demanda.getAreaRegional() + " sobre a a seguinte SSI de número: " + demanda.getNumeroDemanda());
		sb.append("<br>");
		sb.append("Emissor: " + demanda.getAutor().getCnmnome());
		sb.append("<br>");
		sb.append("Área de Origem: " + demanda.getAreaRegional());
		sb.append("<br>");
		sb.append("Título da SSI: " + demanda.getQuestao());
		sb.append("<br>");
		sb.append("Previsão de conclusão: " + demanda.getPrazo());
		sb.append("<br>");
		//TODO Falta colocar o link, para isso falta implementar o acesso por iddemanda=zzz ex: http://ssi.vivo.com.br/REGULATORIO/?iddemanda=115

		return sb.toString();

	}

	@Override
	public void notificaAutor(String strmensagem, String assunto, String ssi,DemandasRegulatorio demanda) {

		Map<String, String> emails = new HashMap<String, String>();

		emails.put(demanda.getAutor().getCnmnome(), getEmailPessoa(demanda.getAutor()));

		send(strmensagem, assunto, emails, getConfig().getCnmipsmtp(), getModulo().getCnmmodulo(), demanda.getNumeroDemanda());

	}
}