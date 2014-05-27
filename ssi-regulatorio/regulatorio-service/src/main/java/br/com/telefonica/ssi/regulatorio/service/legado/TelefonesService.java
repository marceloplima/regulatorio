package br.com.telefonica.ssi.regulatorio.service.legado;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Telefones;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.TelefonesInt;

@Stateless
public class TelefonesService implements TelefonesInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	@Override
	public boolean verificaExistente(Telefones telefone) throws IndexOutOfBoundsException {
		Telefones telret;
		try{
			String jpaquery = "SELECT telefone FROM Telefones telefone WHERE cnmtelefone = '"+telefone.getCnmtelefone().trim()+"'";
			
			if(telefone.getId()!=null && telefone.getId()>=1)
				jpaquery+=" AND idtelefone != "+telefone.getId();
			
			//System.out.println(jpaquery);
			
			telret = em.createQuery(jpaquery, Telefones.class).getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		return false;
    	}
		
		if(telret!=null)
			return true;
		else
			return false;
	}
	
	@Override
	public Telefones incluir(Telefones telefone) {
		em.persist(telefone);
		em.flush();
		em.refresh(telefone);
		
		return telefone;
	}

	@Override
	public void alterar(Telefones telefone) {
		em.merge(telefone);
		em.flush();
		em.detach(telefone);
	}
	
	@Override
	public void remover(Telefones telefone){
	}

}
