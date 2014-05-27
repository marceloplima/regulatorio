package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Atividades;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.AtividadesInt;

/**
 * Session Bean implementation class SLSBAreas
 */
@Stateless
public class AtividadesService implements AtividadesInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
   
    public AtividadesService() {}

	@Override
	public List<Atividades> recuperar() {
		
		final String jpaquery = "SELECT atividade FROM Atividades atividade";
		TypedQuery<Atividades> q = em.createQuery(jpaquery, Atividades.class);
		return q.getResultList();
	}
	
	@Override
	public Atividades recuperarUnico(Atividades obj) {
		
		final String jpaquery = "SELECT atividade FROM Atividades atividade WHERE atividade = :atividade";
		TypedQuery<Atividades> q = em.createQuery(jpaquery, Atividades.class);
		q.setParameter("atividade", obj);
		return q.getResultList().get(0);
	}

	@Override
	public boolean verificaExistente(Atividades obj) throws IndexOutOfBoundsException {
		try{
			String jpaquery = "SELECT obj FROM Atividades obj WHERE obj.cnmatividade = '"+obj.getCnmatividade()+"'";
			TypedQuery<Atividades> q = em.createQuery(jpaquery, Atividades.class);
			q.getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		return true;
    	}
		
		return false;
	}
	
	@Override
	public List<Atividades> recuperarFiltrado(Map<String, Object> filtros) {
		
		String jpaquery = "SELECT obj FROM Atividades obj WHERE 1=1 ";
		
		Atividades obj = (Atividades) filtros.get("obj");
		
		
		if(obj!=null){
			if(obj.getCnmatividade()!=null && !obj.getCnmatividade().isEmpty()){
				jpaquery +="and obj.cnmatividade like '%"+obj.getCnmatividade()+"%' ";
			}
		}
		TypedQuery<Atividades> q = em.createQuery(jpaquery, Atividades.class);
		
		return q.getResultList();
	}
	
	@Override
	public List<Areas> retornarAreasAtividade(Atividades ativ){
		String jpaquery = "SELECT DISTINCT area FROM Areas area join fetch area.areasatividades WHERE :ativ in elements(area.areasatividades)";
		TypedQuery<Areas> q = em.createQuery(jpaquery,Areas.class);
		q.setParameter("ativ", ativ);
		return q.getResultList();
	}
	
//	@Override
//	public List<Pessoas> retornarCopiadosAtividade(Atividades ativ){
//		String jpaquery = "SELECT DISTINCT pessoa FROM Pessoas pessoa join fetch pessoa.pessoasatividades WHERE :ativ in elements(pessoa.pessoasatividades)";
//		TypedQuery<Pessoas> q = em.createQuery(jpaquery,Pessoas.class);
//		q.setParameter("ativ", ativ);
//		return q.getResultList();
//	}
	
	@Override
	public void incluir(Atividades obj) {
		em.merge(obj);
		em.flush();
	}

	@Override
	public void alterar(Atividades obj) {
		em.merge(obj);
		em.detach(obj);
	}

	@Override
	public List<Atividades> recuperarporarea(Areas area) {
		String jpaquery = "SELECT DISTINCT ativ FROM Atividades ativ join fetch ativ.atividadesareas WHERE :area in elements(ativ.atividadesareas)";
		TypedQuery<Atividades> q = em.createQuery(jpaquery,Atividades.class);
		q.setParameter("area", area);
		return q.getResultList();
	}

}
