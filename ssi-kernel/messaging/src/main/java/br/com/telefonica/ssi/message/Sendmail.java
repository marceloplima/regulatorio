package br.com.telefonica.ssi.message;


import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Sendmail {
	
	public void enviarEmail(String strSubject, String toMail, String toName, String strMessage, String fromName, String fromMail, String smtpServer, String modulo) throws MessagingException, UnsupportedEncodingException{
		
		smtpServer = smtpServer.trim();
		
		// Configura as props com os parametros
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol","smtp");
		props.setProperty("mail.host",smtpServer);
		props.setProperty("mail.user",fromMail);
		props.setProperty("mail.password","");
			
		// Inicia o mail session
		Session mailSession = Session.getDefaultInstance(props,null);
			
		Message msg = new MimeMessage(mailSession);
		msg.setFrom( new InternetAddress(fromMail,fromName) );
			
		//System.out.println(">>>email: "+toMail+" >>>> name: "+toName);
			
		msg.setRecipient(Message.RecipientType.TO,new InternetAddress(toName,toMail));
		msg.setSubject(strSubject);
			
		MimeBodyPart corpoMsg = new MimeBodyPart();
		corpoMsg.setContent(strMessage,"text/html");
		
		// Adiciona o anexo
//			MimeBodyPart anexoArquivo = new MimeBodyPart();
//			FileDataSource fds = new FileDataSource(anexo);
//			anexoArquivo.setDataHandler(new DataHandler(fds));
//			anexoArquivo.setFileName(fds.getName());
			
		// Monta a mensagem smtp
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(corpoMsg);
		//mp.addBodyPart(anexoArquivo);
		msg.setContent(mp);
			
		//Envia
		Transport.send(msg);
			
		System.out.println("Mensagem de email enviada pelo JMS MailerJMS - Remetente: "+fromMail+" | Destinatario: "+toMail);
			
		msg = null;
		mp = null;
		corpoMsg = null;
		mailSession = null;
		props = null;
		smtpServer = null;
		fromMail = null;
		fromName = null;		
		strMessage = null;
		toName = null;
		toMail = null;
		strSubject = null;
		
	}

}
