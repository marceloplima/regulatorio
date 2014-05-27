package br.com.telefonica.ssi.regulatorio.service.legado;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Emails;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.EmailsInt;


@Stateless
public class EmailsService implements EmailsInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	@Override
	public boolean verificaExistente(Emails email) throws IndexOutOfBoundsException {
		Emails emailret;
		try{
			String jpaquery = "SELECT email FROM Emails email WHERE cnmemail = '"+email.getCnmemail().trim()+"'";
			
			//if(email.getIdemail()!=null && email.getIdemail()>=1)
				//jpaquery+=" AND idemail != "+email.getIdemail();
			
			//System.out.println(jpaquery);
			
			emailret = em.createQuery(jpaquery, Emails.class).getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		return false;
    	}
		
		if(emailret!=null)
			return true;
		else
			return false;
	}
	
	@Override
	public Emails incluir(Emails email) {
		em.persist(email);
		em.flush();
		em.refresh(email);
		
		return email;
	}

	@Override
	public void alterar(Emails email) {
		em.merge(email);
		em.flush();
		em.detach(email);
	}
	
	@Override
	public void remover(Emails email){
	}

}
