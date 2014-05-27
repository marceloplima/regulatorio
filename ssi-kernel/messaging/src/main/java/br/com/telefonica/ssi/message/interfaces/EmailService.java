package br.com.telefonica.ssi.message.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import br.com.telefonica.ssi.entity.mail.MaquinaEmails;

@Remote
public interface EmailService {
	public List<MaquinaEmails> recuperar();
	public MaquinaEmails recuperarUnico(MaquinaEmails grupo);
	public List<MaquinaEmails> recuperarFiltrado(Map<String, Object> filtros);
	public void incluir(MaquinaEmails obj);
	public void alterar(MaquinaEmails obj);
}
