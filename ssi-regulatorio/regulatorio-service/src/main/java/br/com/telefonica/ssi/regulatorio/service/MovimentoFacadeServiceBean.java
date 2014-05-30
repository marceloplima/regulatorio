package br.com.telefonica.ssi.regulatorio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAcionamentoArea;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseOperacional;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseTecnica;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoConclusao;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoFollowUp;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoRevisaoPrazo;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoTecnico;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
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

	@Override
	public List<MovimentoAcionamentoArea> movimentosAcionamentosPorDemanda(
			DemandasRegulatorio demanda) {
		List<MovimentoAcionamentoArea> acionamentos = new ArrayList<MovimentoAcionamentoArea>();
		TypedQuery<MovimentoAcionamentoArea> q = em.createQuery("select m from MovimentoAcionamentoArea m where m.movimento.demanda = :demanda order by m.movimento.dataHora desc",MovimentoAcionamentoArea.class);
		q.setParameter("demanda", demanda);
		acionamentos = q.getResultList();
		return acionamentos;
	}

	@Override
	public List<MovimentoAnaliseOperacional> analisesOperacionaisPorDemanda(
			DemandasRegulatorio demanda) {
		List<MovimentoAnaliseOperacional> analises = new ArrayList<MovimentoAnaliseOperacional>();
		TypedQuery<MovimentoAnaliseOperacional> q = em.createQuery("select m from MovimentoAnaliseOperacional m where m.movimento.demanda = :demanda order by m.movimento.dataHora desc",MovimentoAnaliseOperacional.class);
		q.setParameter("demanda", demanda);
		analises = q.getResultList();
		return analises;
	}

	@Override
	public MovimentoAnaliseOperacional getAnaliseOperacionalPorId(Integer id) {
		MovimentoAnaliseOperacional analises = null;
		TypedQuery<MovimentoAnaliseOperacional> q = em.createQuery("select m from MovimentoAnaliseOperacional m where m.idMovmentoAnaliseOperacional = :id",MovimentoAnaliseOperacional.class);
		q.setParameter("id", id);
		analises = q.getSingleResult();
		return analises;
	}

	@Override
	public List<Movimento> getMovimentosPorDemanda(DemandasRegulatorio demanda) {
		List<Movimento> analises = new ArrayList<Movimento>();
		TypedQuery<Movimento> q = em.createQuery("select m from Movimento m where m.demanda = :demanda order by m.dataHora desc",Movimento.class);
		q.setParameter("demanda", demanda);
		analises = q.getResultList();
		return analises;
	}

	@Override
	public Movimento getRowDataMovimento(Object rowKey) {
		return em.find(Movimento.class, rowKey);
	}

	@Override
	public List<Movimento> retornarPaginadoMovimentos(int firstRow,
			int numberOfRows, Map<String, Object> filtros,
			DemandasRegulatorio demanda, Pessoas pessoa) {
		String jpaQuery = "Select m from Movimento m where m.demanda = :demanda AND (m.demanda.autor = :pessoa "
				+ "OR m.demanda.encarregado = :pessoa OR m.demanda.solicitante = :pessoa) ";

		// === OUTROS FILTROS ===

		// === FIM OUTROS FILTROS ===

		jpaQuery+=" order by m.dataHora desc";

		TypedQuery<Movimento> q = em.createQuery(jpaQuery,Movimento.class);
		q.setParameter("demanda", demanda);
		q.setParameter("pessoa", pessoa);

		if(firstRow>0 && numberOfRows>0){
			q.setFirstResult(firstRow);
			q.setMaxResults(numberOfRows);
		}

		try{
			List<Movimento> result = q.getResultList();

			return result;
		}
		catch(NoResultException nre){
			return new ArrayList<Movimento>();
		}
	}

	@Override
	public int getRowCountMovimentos(Map<String, Object> filtros,
			DemandasRegulatorio demanda, Pessoas pessoa) {
		String jpaQuery = "Select count(m.idMovimento) from Movimento m where m.demanda = :demanda AND (m.demanda.autor = :pessoa OR "
				+ "m.demanda.encarregado = :pessoa OR m.demanda.solicitante = :pessoa) ";

		// === OUTROS FILTROS ===

		// === FIM OUTROS FILTROS ===

		TypedQuery<Long> q = em.createQuery(jpaQuery,Long.class);

		q.setParameter("demanda", demanda);
		q.setParameter("pessoa", pessoa);

		try{
			Long result = q.getSingleResult();

			return result.intValue();
		}
		catch(NoResultException nre){
			return 0;
		}
	}

}