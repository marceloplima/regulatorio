package br.com.telefonica.ssi.regulatorio.service;

import javax.ejb.Stateless;
import javax.persistence.Query;

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
	
}
