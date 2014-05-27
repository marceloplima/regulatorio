package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.AreasInt;


/**
 * Session Bean implementation class SLSBAreas
 */
@Stateless
public class AreasService implements AreasInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
   
    public AreasService() {}
    
	@Override
	public List<Areas> recuperar() {
		final String jpaquery = "SELECT area FROM Areas area order by area.cnmsiglaarea";
		TypedQuery<Areas> q = em.createQuery(jpaquery, Areas.class);
		return q.getResultList();
	}
	
	@Override
	public Areas recuperarUnico(Areas area) {
		final String jpaquery = "SELECT area FROM Areas area WHERE area = :area";
		TypedQuery<Areas> q = em.createQuery(jpaquery, Areas.class);
		q.setParameter("area", area);
		return q.getResultList().get(0);
	}

	@Override
	public boolean verificaExistente(Areas obj) throws IndexOutOfBoundsException {
		try{
			String jpaquery = "SELECT obj FROM Areas obj WHERE obj.cnmsiglaarea = '"+obj.getCnmsiglaarea().trim()+"'";
			TypedQuery<Areas> q = em.createQuery(jpaquery, Areas.class);
			q.getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		return true;
    	}
		
		return false;
	}
	
	@Override
	public List<Areas> recuperarFiltrado(Map<String, Object> filtros) {
		
		String jpaquery = "SELECT obj FROM Areas obj WHERE 1=1 ";
		
		Areas obj = (Areas) filtros.get("obj");
		
		
		if(obj!=null){
			if(obj.getCnmsiglaarea()!=null && !obj.getCnmsiglaarea().isEmpty()){
				jpaquery +="and obj.cnmsiglaarea like '%"+obj.getCnmsiglaarea()+"%' ";
			}
		}
		
		jpaquery +=" order by obj.cnmsiglaarea";
		
		TypedQuery<Areas> q = em.createQuery(jpaquery, Areas.class);
		
		return q.getResultList();
	}
	
	@Override
	public void incluir(Areas obj) {
		obj.setCnmsiglaarea(obj.getCnmsiglaarea().toUpperCase()); // For�a pra ficar em caixa alta
		em.merge(obj);
		em.flush();
	}

	@Override
	public void alterar(Areas obj) {
		em.merge(obj);
		em.detach(obj);
	}

	@Override
	public Areas recuperaAreaPorSigla(String sigla) throws IndexOutOfBoundsException {

		try{
			final String jpaquery = "SELECT area FROM Areas area WHERE area.cnmsiglaarea = :area";
			TypedQuery<Areas> q = em.createQuery(jpaquery, Areas.class);
			q.setParameter("area", sigla);
			return q.getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		e.printStackTrace();
    		return null;
    	}
						
	}

	@Override
	public List<Pessoas> recuperarPessoasArea(Areas area) {

		final String jpaquery = "SELECT pessoas FROM Areas area INNER JOIN area.areaspessoas pessoas WHERE area = :area";
		TypedQuery<Pessoas> q = em.createQuery(jpaquery, Pessoas.class);
		q.setParameter("area", area);
		
		return q.getResultList();
	}

	@Override
	public List<Areas> retornarAreasOperacao() {
		final String jpaquery = "SELECT area FROM Areas area WHERE area.papelarea.idpapelarea = 1 order by area.cnmsiglaarea";
		TypedQuery<Areas> q = em.createQuery(jpaquery, Areas.class);
		return q.getResultList();
	}

}
