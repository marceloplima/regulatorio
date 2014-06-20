package br.com.telefonica.ssi.regulatorio.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.core.application.exception.ApplicationException;
import br.com.telefonica.ssi.regulatorio.commom.domain.StatusRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.StatusRegulatorioService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class StatusRegulatorioServiceBean extends AbstractCrudServiceBean<StatusRegulatorio, Integer> implements StatusRegulatorioService{

	/**
	 *
	 */
	private static final long serialVersionUID = -6537077443907491722L;

	@Override
	public StatusRegulatorio findByName(String name) {
		if(name!=null && !name.equals("")){
			Query query = getEntityManager().createQuery("Select s from StatusRegulatorio s where UPPER(s.descricao) = :name");
			query.setParameter("name", name.toUpperCase());
			StatusRegulatorio status = (StatusRegulatorio)query.getResultList().get(0);
			return status;
		}

		return null;
	}

	@Override
	public List<StatusRegulatorio> findAll() throws ApplicationException {
		TypedQuery<StatusRegulatorio> q = getEntityManager().createQuery("select s from StatusRegulatorio s order by s.descricao asc",StatusRegulatorio.class);
		return q.getResultList();
	}
}
