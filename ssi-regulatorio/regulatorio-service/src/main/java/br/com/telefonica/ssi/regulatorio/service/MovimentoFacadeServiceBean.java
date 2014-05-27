package br.com.telefonica.ssi.regulatorio.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAcionamentoArea;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseOperacional;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseTecnica;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoConclusao;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoFollowUp;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoRevisaoPrazo;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoTecnico;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.MovimentoFacade;


@Stateless
public class MovimentoFacadeServiceBean implements MovimentoFacade{

	/**
	 *
	 */
	private static final long serialVersionUID = -1872898081185709232L;
	@PersistenceContext
	EntityManager em;

	@Override
	public void salvaMovimento(Movimento movimento) {
		em.persist(movimento);
	}

	@Override
	public void salvaMovimentoRevisaoPrazo(MovimentoRevisaoPrazo revisao) {
		em.persist(revisao);
	}

	@Override
	public void salvaMovimentoAcionamentoArea(
			MovimentoAcionamentoArea acionamento) {
		em.persist(acionamento);
	}

	@Override
	public void salvaMovimentoAnaliseOperacional(
			MovimentoAnaliseOperacional operacional) {
		em.persist(operacional);
	}

	@Override
	public void salvaMovimentoAnaliseTecnica(
			MovimentoAnaliseTecnica analiseTecnica) {
		em.persist(analiseTecnica);
	}

	@Override
	public void salvaMovimentoConclusao(MovimentoConclusao conclusao) {
		em.persist(conclusao);
	}

	@Override
	public void salvaMovimentoTecnico(MovimentoTecnico movimentoTecnico) {
		em.persist(movimentoTecnico);
	}

	@Override
	public void salvarFollowUp(MovimentoFollowUp followUp) {
		em.persist(followUp);
	}
}
