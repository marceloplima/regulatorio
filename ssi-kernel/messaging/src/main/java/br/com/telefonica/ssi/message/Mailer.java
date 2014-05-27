package br.com.telefonica.ssi.message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.telefonica.ssi.entity.mail.MaquinaEmails;
import br.com.telefonica.ssi.message.interfaces.EmailService;


@MessageDriven(
		activationConfig = {
				@ActivationConfigProperty(
					propertyName="messagingType",
					propertyValue="javax.jms.MessageListener"),
				@ActivationConfigProperty(
					propertyName="destination",
					propertyValue="/queue/QueueSSI"),
				@ActivationConfigProperty(
					propertyName = "destinationType", 
					propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(
					propertyName = "acknowledgeMode",
					propertyValue = "Auto-acknowledge") 
		})
public class Mailer implements MessageListener {

	@EJB
	private EmailService maquinaemailsint;
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	 try {
         	TextMessage textMsg = (TextMessage)message;
 			
         	String emaildest  = textMsg.getStringProperty("emaildest");
         	String nomedest   = textMsg.getStringProperty("nomedest");
         	String emailremet = textMsg.getStringProperty("emailremet");
         	String nomeremet  = textMsg.getStringProperty("nomeremet");
         	String assunto    = textMsg.getStringProperty("assunto");
         	String smtpserver = textMsg.getStringProperty("smtpserver").trim();
         	String modulo	  = textMsg.getStringProperty("modulo");
         	String ssi		  = textMsg.getStringProperty("ssi");
         	
         	String msg = textMsg.getText();
 			
         	System.out.println(this.getClass()+" :: SMTP: '"+smtpserver+"'");
         	System.out.println(this.getClass()+" :: emaildest: "+emaildest);
         	System.out.println(this.getClass()+" :: nomedest: "+nomedest);
         	System.out.println(this.getClass()+" :: emailremet: "+emailremet);
         	System.out.println(this.getClass()+" :: nomeremet: "+nomeremet);
         	System.out.println(this.getClass()+" :: modulo: "+modulo);
         	System.out.println(this.getClass()+" :: ssi: "+ssi);
 			System.out.println(this.getClass()+" :: MENSAGEM RECEBIDA: "+msg);
 			
 			
 			Sendmail sendmail = new Sendmail();
 			
 			// Se conseguiu enviar, grava no banco o registro de que enviou
			MaquinaEmails obj = new MaquinaEmails();
			obj.setCnmassunto(assunto);
			obj.setCnmdestinatario(nomedest);
			obj.setCnmemaildestinatario(emaildest);
			obj.setCnmremetente(nomeremet);
			obj.setCnmemailremetente(emailremet);
			obj.setCnmipsmtp(smtpserver);
			obj.setCnmmensagem(msg);
			obj.setCnmmodulo(modulo);
			obj.setCnmssi(ssi);
			
 			try{
 				sendmail.enviarEmail(assunto,nomedest,emaildest,msg,nomeremet,emailremet,smtpserver,modulo);
 				
 				// Se conseguiu enviar, grava no banco o registro de que enviou
 				obj.setFlagenviado(true);
 			}
 			catch(Exception ex){
 				
 				// Se conseguiu enviar, grava no banco o registro de que enviou
 				obj.setFlagenviado(false);
 				ex.printStackTrace();
 			}
 			
 			// Grava no banco o registro da opera��o de email (com sucesso ou sem sucesso de acordo com o flag)
 			maquinaemailsint.incluir(obj);
 			
 			msg 	   = null;
 			emaildest  = null;
 			nomedest   = null;
 			emailremet = null;
 			assunto	   = null;
 			textMsg    = null;
 			sendmail   = null;
 			message    = null;
 			
 		} catch (JMSException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }
}
