package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.TiposFornecedor;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.TiposFornecedorInt;

@Stateless
public class TiposFornecedorServce implements TiposFornecedorInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	public TiposFornecedorServce(){}
	
	@Override
	public List<TiposFornecedor> recuperar() {
		final String jpaquery = "SELECT tipofornecedor FROM TiposFornecedor tipofornecedor";
		TypedQuery<TiposFornecedor> q = em.createQuery(jpaquery, TiposFornecedor.class);
		return q.getResultList();
	}
	
	@Override
	public TiposFornecedor recuperarUnico(TiposFornecedor tipofornecedor) {
		return em.find(TiposFornecedor.class, tipofornecedor);
	}

	
	@Override
	public boolean verificaExistente(TiposFornecedor obj) throws IndexOutOfBoundsException {
		try{
			String jpaquery = "SELECT tf FROM TiposFornecedor tf WHERE tf.cnmtipofornecedor = '"+obj.getCnmtipofornecedor().trim()+"'";
			TypedQuery<TiposFornecedor> q = em.createQuery(jpaquery, TiposFornecedor.class);
			q.getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		return true;
    	}
		
		return false;
	}
	
	@Override
	public List<TiposFornecedor> recuperarFiltrado(Map<String, Object> filtros) {
		
		String jpaquery = "SELECT tf FROM TiposFornecedor tf WHERE 1=1 ";
		
		TiposFornecedor tipofornecedor = (TiposFornecedor) filtros.get("tipofornecedor");
		
		
		if(tipofornecedor.getCnmtipofornecedor()!=null && !tipofornecedor.getCnmtipofornecedor().isEmpty()){
			jpaquery +="and tf.cnmtipofornecedor like '%"+tipofornecedor.getCnmtipofornecedor()+"%' ";
		}
		
		TypedQuery<TiposFornecedor> q = em.createQuery(jpaquery, TiposFornecedor.class);
		
		return q.getResultList();
	}
	
	@Override
	public void incluir(TiposFornecedor obj) {
		obj.setCnmtipofornecedor(obj.getCnmtipofornecedor().toUpperCase()); // For�a pra ficar em caixa alta
		em.merge(obj);
		em.flush();
	}

	@Override
	public void alterar(TiposFornecedor obj) {
		em.merge(obj);
		em.detach(obj);
	}
	
}
