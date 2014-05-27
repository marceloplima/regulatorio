package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.ConfigSMTP;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.ConfigSMTPInt;


/**
 * Session Bean implementation class SLSBAreas
 */
@Stateless
public class ConfigSMTPService implements ConfigSMTPInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
   
    public ConfigSMTPService() {}

	@Override
	public List<ConfigSMTP> recuperar() {
		
		final String jpaquery = "SELECT configsmtp FROM ConfigSMTP configsmtp";
		
		TypedQuery<ConfigSMTP> q = em.createQuery(jpaquery, ConfigSMTP.class);
		
		return q.getResultList();
	}
	
	@Override
	public ConfigSMTP recuperarUnico(Long idsmtp) {
		
		final String jpaquery = "SELECT configsmtp FROM ConfigSMTP configsmtp WHERE idsmtp = "+idsmtp;
		
		return em.createQuery(jpaquery, ConfigSMTP.class).getResultList().get(0);
		
	}

	@Override
	public boolean verificaExistente(ConfigSMTP obj) throws IndexOutOfBoundsException {
		try{
			String jpaquery = "SELECT obj FROM ConfigSMTP obj WHERE obj.cnmnomeservidor = '"+obj.getCnmnomeservidor().trim()+"' or obj.cnmipsmtp = '"+obj.getCnmipsmtp()+"'";
			TypedQuery<ConfigSMTP> q = em.createQuery(jpaquery, ConfigSMTP.class);
			q.getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		return true;
    	}
		
		return false;
	}
	
	@Override
	public List<ConfigSMTP> recuperarFiltrado(Map<String, Object> filtros) {
		
		String jpaquery = "SELECT obj FROM ConfigSMTP obj WHERE 1=1 ";
		
		ConfigSMTP obj = (ConfigSMTP) filtros.get("obj");
		
		
		if(obj!=null){
			if(obj.getCnmipsmtp()!=null && !obj.getCnmipsmtp().isEmpty()){
				jpaquery +="and obj.cnmipsmtp like '%"+obj.getCnmipsmtp()+"%' ";
			}
		}
		TypedQuery<ConfigSMTP> q = em.createQuery(jpaquery, ConfigSMTP.class);
		
		return q.getResultList();
	}
	
	@Override
	public void incluir(ConfigSMTP obj) {
		obj.setCnmnomeservidor(obj.getCnmnomeservidor().toUpperCase()); // For�a pra ficar em caixa alta
		em.merge(obj);
		em.flush();
	}

	@Override
	public void alterar(ConfigSMTP obj) {
		em.merge(obj);
		em.detach(obj);
	}

}
