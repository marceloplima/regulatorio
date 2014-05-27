package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.AnexosRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.TipoAnexo;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.legado.AnexosInt;

@Stateless
public class AnexosService implements AnexosInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	@Override
	public void incluir(AnexosRegulatorio anexo) {
		em.merge(anexo);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public AnexosRegulatorio getRowData(Object rowKey) {
		return em.find(AnexosRegulatorio.class, rowKey);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Integer getRowCount(Map<String, Object> filtros) {
		
		DemandasRegulatorio demanda = (DemandasRegulatorio)filtros.get("demanda");
		
		if(demanda!= null && demanda.getId()!=null && demanda.getId()>=1){
			String jpaquery = "SELECT count(anexo.idanexo) FROM AnexosRegulatorio anexo where 1=1 ";
			
			
			
			//if(demanda!= null && demanda.getIddemanda()!=null && demanda.getIddemanda()>=1){
		    	jpaquery+=" and anexo.demanda = :demanda ";
		    //}
			
		    TypedQuery<Long> q = em.createQuery(jpaquery,Long.class);
		    
		    //if(demanda!= null && demanda.getIddemanda()!=null && demanda.getIddemanda()>=1){
		    	q.setParameter("demanda", demanda);
		    //}
		    		
		    Long lid = q.getResultList().get(0);
		    jpaquery = null;
		    
		    try{
		    	return lid.intValue();
		    }
		    catch(IndexOutOfBoundsException e){
		    	return 0;
		    }
		}
		
		return 0;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<AnexosRegulatorio> retornarPaginado(int firstRow, int numberOfRows,
			Map<String, Object> filtros) {
		
		DemandasRegulatorio demanda = (DemandasRegulatorio)filtros.get("demanda");
		Long tipo = (Long)filtros.get("tipo");
		
		if(demanda!= null && demanda.getId()!=null && demanda.getId()>=1){
			String jpaquery = "SELECT anexo FROM AnexosRegulatorio anexo where 1=1 ";
		    
			if(tipo!= null && tipo>=1L){
		    	jpaquery+=" and anexo.tipoanexo.idtipoanexo = :tipo ";
		    }
		    
		    //if(demanda!= null && demanda.getIddemanda()!=null && demanda.getIddemanda()>=1){
		    	jpaquery+=" and anexo.demanda = :demanda ";
		    //}
		    
		    jpaquery+="ORDER BY anexo.idanexo DESC";
		    
		    TypedQuery<AnexosRegulatorio> q = em.createQuery(jpaquery,AnexosRegulatorio.class);
		    
		    //if(demanda!= null && demanda.getIddemanda()!=null && demanda.getIddemanda()>=1){
		    	q.setParameter("demanda", demanda);
		    //}
		    	
		    if(tipo!= null && tipo>=1L){
		    	q.setParameter("tipo", tipo);
			}
		        
		    if (firstRow >= 0 && numberOfRows > 0){
		    	q.setFirstResult(firstRow);
		    	q.setMaxResults(numberOfRows);
		    }
		    try{
		    	return q.getResultList();
			}
		    catch(NoResultException nex){
		    	return new ArrayList<AnexosRegulatorio>();
		    }
		}
		
		return new ArrayList<AnexosRegulatorio>();
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public AnexosRegulatorio retornarUltimaMinutaDemanda(DemandasRegulatorio demanda) {
		
		String jpaquery = "SELECT anexo FROM AnexosRegulatorio anexo WHERE anexo.demanda = :demanda and anexo.tipoanexo.idtipoanexo = 1 ORDER BY anexo.idanexo DESC";
	    TypedQuery<AnexosRegulatorio> q = em.createQuery(jpaquery,AnexosRegulatorio.class);
	    q.setMaxResults(1);
	    q.setParameter("demanda", demanda);
		
	    try{
	    	return q.getResultList().get(0);
		}
	    catch(IndexOutOfBoundsException e){
	    	return new AnexosRegulatorio();
	    }
	}

	@Override
	public void remover(AnexosRegulatorio anexo) {
		em.remove(em.merge(anexo));
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public TipoAnexo recuperarTipoAnexo(Long idtipoanexo) {
		String jpaquery = "SELECT tipoanexo FROM TiposAnexosRegulatorio tipoanexo WHERE tipoanexo.idtipoanexo = :idtipoanexo";
	    TypedQuery<TipoAnexo> q = em.createQuery(jpaquery,TipoAnexo.class);
	    q.setParameter("idtipoanexo", idtipoanexo);
	    try{
	    	return q.getResultList().get(0);
		}
	    catch(IndexOutOfBoundsException e){
	    	return new TipoAnexo();
	    }
	}
}
