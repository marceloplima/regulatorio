package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Modulos;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.ModulosInt;

@Stateless
public class ModulosService implements ModulosInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	@Override
	public List<Modulos> buscarModulos(Modulos modulo) {
		final String jpaquery = "SELECT modulo FROM Modulos modulo WHERE cnmmodulo like '%"+modulo.getCnmmodulo().trim()+"%'";
		TypedQuery<Modulos> q = em.createQuery(jpaquery, Modulos.class);
		return q.getResultList();
	}
	
	@Override
	public Modulos recuperarUnico(Long idmodulo) {
		final String jpaquery = "SELECT modulo FROM Modulos modulo WHERE idmodulo = "+idmodulo;
		return em.createQuery(jpaquery, Modulos.class).getResultList().get(0);
	}
	
	@Override
	public boolean verificaExistente(Modulos modulo) throws IndexOutOfBoundsException {
		Modulos mod;
		try{
			String jpaquery = "SELECT modulo FROM Modulos modulo WHERE cnmmodulo = '"+modulo.getCnmmodulo().trim()+"'";
			
			if(modulo.getId()!=null && modulo.getId()>=1)
				jpaquery+=" AND idmodulo != "+modulo.getId();
			
			//System.out.println(jpaquery);
			
			mod = em.createQuery(jpaquery, Modulos.class).getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		return false;
    	}
		
		if(mod!=null)
			return true;
		else
			return false;
	}
	
	@Override
	public Modulos incluir(Modulos modulo) {
		em.persist(modulo);
		em.flush();
		em.refresh(modulo);
		
		return modulo;
	}

	@Override
	public void alterar(Modulos modulo) {
		em.merge(modulo);
		em.detach(modulo);
	}

	@Override
	public List<Modulos> recuperar() {
		final String jpaquery = "SELECT modulo FROM Modulos modulo";
		TypedQuery<Modulos> q = em.createQuery(jpaquery, Modulos.class);
		return q.getResultList();
	}
	
	@Override
	public void remover(Modulos modulo){
//		em.remove(em.contains(modulo) ? modulo : em.merge(modulo));
//		em.flush();
//		em.detach(modulo);
	}

}
