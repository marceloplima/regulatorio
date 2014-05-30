package br.com.telefonica.ssi.regulatorio.service;

import java.io.File;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.AnexosRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.TipoAnexo;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.AnexoService;
import br.com.telefonica.ssi.service.AbstractCrudServiceBean;

@Stateless
public class AnexoServiceBean extends AbstractCrudServiceBean<AnexosRegulatorio, Integer> implements AnexoService{

	/**
	 *
	 */
	private static final long serialVersionUID = -513924628803325173L;

	@SuppressWarnings("unchecked")
	@Override
	public List<AnexosRegulatorio> buscarAnexos(DemandasRegulatorio demanda) {
		Query q = getEntityManager().createQuery("select a from AnexosRegulatorio a where a.demanda = :demanda");
		q.setParameter("demanda", demanda);
		return q.getResultList();
	}

	@Override
	public void salvarAnexo(AnexosRegulatorio anexo, File file) {
		// TODO Auto-generated method stub

	}

	@Override
	public TipoAnexo recuperarTipoAnexo(Long idtipoanexo) {
		String jpaquery = "SELECT tipoanexo FROM TipoAnexo tipoanexo WHERE tipoanexo.idtipoanexo = :idtipoanexo";
	    TypedQuery<TipoAnexo> q = getEntityManager().createQuery(jpaquery,TipoAnexo.class);
	    q.setParameter("idtipoanexo", idtipoanexo);
	    try{
	    	return q.getResultList().get(0);
		}
	    catch(IndexOutOfBoundsException e){
	    	return new TipoAnexo();
	    }
	}

	@Override
	public AnexosRegulatorio incluir(AnexosRegulatorio anexo) {
		return getEntityManager().merge(anexo);
	}

}
