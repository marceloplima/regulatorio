package br.com.telefonica.ssi.regulatorio.commom.interfaces.legado;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Local
public interface EmailServiceInt {

	void enviarEMail(List<Long> papeis, DemandasRegulatorio demanda, String mensagem, String assunto, List<Pessoas> outrasPessoasParaEnvio);
	
}
