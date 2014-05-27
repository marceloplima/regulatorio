package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.io.Serializable;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAcionamentoArea;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseOperacional;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseTecnica;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoConclusao;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoFollowUp;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoRevisaoPrazo;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoTecnico;

@Local
public interface MovimentoFacade extends Serializable{

	void salvaMovimento(Movimento movimento);

	void salvaMovimentoRevisaoPrazo(MovimentoRevisaoPrazo revisao);

	void salvaMovimentoAcionamentoArea(MovimentoAcionamentoArea acionamento);

	void salvaMovimentoAnaliseOperacional(MovimentoAnaliseOperacional operacional);

	void salvaMovimentoAnaliseTecnica(MovimentoAnaliseTecnica analiseTecnica);

	void salvaMovimentoConclusao(MovimentoConclusao conclusao);

	void salvaMovimentoTecnico(MovimentoTecnico movimentoTecnico);

	void salvarFollowUp(MovimentoFollowUp followUp);
}
