package br.com.telefonica.ssi.regulatorio.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.StatusRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.DemandaService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@SuppressWarnings("all")
@Stateless
public class DemandaServiceBean extends
		AbstractCrudServiceBean<DemandasRegulatorio, Integer> implements
		DemandaService {

	/**
	 *
	 */
	private static final long serialVersionUID = -7614096186536025819L;

	@Override
	public String getNumeroNovaDemanda() {
		Query q = getEntityManager()
				.createQuery(
						"select count(d) from DemandasRegulatorio d where d.numeroDemanda IS NOT NULL");
		Long result = (Long) q.getSingleResult();
		StringBuilder sb = new StringBuilder();
		sb.append("R");
		sb.append(String.format("%06d", result + 1));
		sb.append("/");
		sb.append(new SimpleDateFormat("MMyy").format(new Date()));
		return sb.toString();
	}

	@Override
	public List<DemandasRegulatorio> getMinhasDemandas(Pessoas autor) {
		Query q = getEntityManager().createQuery(
				"select d from DemandasRegulatorio d where d.autor = :autor");
		q.setParameter("autor", autor);
		List<DemandasRegulatorio> result = (List<DemandasRegulatorio>) q
				.getResultList();
		return result;
	}

	@Override
	public List<DemandasRegulatorio> getDemandasEncarregado(Pessoas encarregado) {
		Query q = getEntityManager()
				.createQuery(
						"select d from DemandasRegulatorio d where d.encarregado = :encarregado");
		q.setParameter("autor", encarregado);
		List<DemandasRegulatorio> result = (List<DemandasRegulatorio>) q
				.getResultList();
		return result;
	}

	@Override
	public List<DemandasRegulatorio> retornarPaginado(int firstRow,
			int numberOfRows, Map<String, Object> filtros) {

		List<DemandasRegulatorio> result = new ArrayList<DemandasRegulatorio>();

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<DemandasRegulatorio> cq = builder
				.createQuery(DemandasRegulatorio.class);
		Root<DemandasRegulatorio> demanda = cq.from(DemandasRegulatorio.class);

		cq.select(demanda);

		List<Predicate> predicados = getPredicados(builder, demanda, filtros);

		if (predicados.size() > 0) {
			cq.where(builder.or(predicados.toArray(new Predicate[] {})));
		}

		cq.orderBy(builder.asc(demanda.<Date> get("prazo")));

		TypedQuery<DemandasRegulatorio> query = getEntityManager().createQuery(
				cq).setFirstResult(firstRow);

		if (numberOfRows > 0) {
			query.setMaxResults(numberOfRows);
		}

		result = query.getResultList();

		return result;
	}

	@Override
	public List<DemandasRegulatorio> recuperarDemandasCriadasPeloAutor(
			Pessoas pessoa) {
		return null;
	}

	@Override
	public List<DemandasRegulatorio> recuperarDemandasEstaoComPessoaLogada(
			Pessoas pessoa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DemandasRegulatorio getRowData(Object rowKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getRowCount(Map<String, Object> filtros) {
		Long result = null;

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = builder.createQuery(Long.class);
		Root<DemandasRegulatorio> raiz = cq.from(DemandasRegulatorio.class);
		cq.select(builder.count(raiz));

		List<Predicate> predicados = getPredicados(builder, raiz, filtros);

		if (predicados.size() > 0) {
			cq.where(builder.and(predicados.toArray(new Predicate[] {})));
		}

		Query q = getEntityManager().createQuery(cq);

		result = (Long) q.getSingleResult();

		return result.intValue();
	}

	// TODO: generalizar!!!
	private List<Predicate> getPredicados(CriteriaBuilder builder,
			Root<DemandasRegulatorio> raiz, Map<String, Object> filtros) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (filtros.get("autor") != null) {
			predicados.add(builder.equal(raiz.get("autor"),
					(Pessoas) filtros.get("autor")));
		}

		if (filtros.get("encarregado") != null) {
			predicados.add(builder.equal(raiz.get("encarregado"),
					(Pessoas) filtros.get("encarregado")));
		}

		return predicados;
	}

	@Override
	public List<DemandasRegulatorio> getDemandasAVerComigo(Pessoas pessoa) {
		Query q = getEntityManager().createQuery("select d from DemandasRegulatorio d where d.autor = :autor or d.encarregado=:encarregado");
		q.setParameter("autor", pessoa);
		q.setParameter("encarregado", pessoa);
		List<DemandasRegulatorio> result = q.getResultList();

		return result;
	}
}