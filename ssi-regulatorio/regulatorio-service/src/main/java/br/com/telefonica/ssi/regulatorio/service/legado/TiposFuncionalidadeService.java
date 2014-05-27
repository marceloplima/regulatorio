package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.TiposFuncionalidade;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.TiposFuncionalidadeInt;


@Stateless
public class TiposFuncionalidadeService implements TiposFuncionalidadeInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
    public TiposFuncionalidadeService() {}
    
    @Override
	public boolean verificaExistente(TiposFuncionalidade obj) throws IndexOutOfBoundsException {
		try{
			String jpaquery = "SELECT tf FROM TiposFuncionalidade tf WHERE tf.cnmtipofuncionalidade = '"+obj.getCnmtipofuncionalidade().trim()+"'";
			TypedQuery<TiposFuncionalidade> q = em.createQuery(jpaquery, TiposFuncionalidade.class);
			q.getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		return true;
    	}
		
		return false;
	}
    
    @Override
	public List<TiposFuncionalidade> recuperarFiltrado(Map<String, Object> filtros) {
		
		String jpaquery = "SELECT tf FROM TiposFuncionalidade tf WHERE 1=1 ";
		
		TiposFuncionalidade tipofunc = (TiposFuncionalidade) filtros.get("tipofuncionalidade");
		
		
		if(tipofunc.getCnmtipofuncionalidade()!=null && !tipofunc.getCnmtipofuncionalidade().isEmpty()){
			jpaquery +="and tf.cnmtipofornecedor like '%"+tipofunc.getCnmtipofuncionalidade()+"%' ";
		}
		
		TypedQuery<TiposFuncionalidade> q = em.createQuery(jpaquery, TiposFuncionalidade.class);
		
		return q.getResultList();
	}
    
    @Override
	public List<TiposFuncionalidade> recuperar(){
    	
    	final String jpaquery = "SELECT tf FROM TiposFuncionalidade tf";
    	
    	TypedQuery<TiposFuncionalidade> q = em.createQuery(jpaquery,TiposFuncionalidade.class);
    	
    	return q.getResultList();
    }
    
    @Override
	public TiposFuncionalidade recuperarUnico(Long idtipofuncionalidade){
    	
    	final String jpaquery = "SELECT tf FROM TiposFuncionalidade tf WHERE idtipofuncionalidade = "+idtipofuncionalidade;
    	
    	return em.createQuery(jpaquery,TiposFuncionalidade.class).getResultList().get(0);
    }

    @Override
	public void incluir(TiposFuncionalidade obj) {
		obj.setCnmtipofuncionalidade(obj.getCnmtipofuncionalidade().toUpperCase()); // For�a pra ficar em caixa alta
		em.merge(obj);
		em.flush();
	}

	@Override
	public void alterar(TiposFuncionalidade obj) {
		em.merge(obj);
		em.detach(obj);
	}

	@Override
	public boolean remover() {
		// TODO Auto-generated method stub
		return false;
	}

}
