package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.TiposUsuario;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.TiposUsuarioInt;

@Stateless
public class TiposUsuariosService implements TiposUsuarioInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	public TiposUsuariosService(){}
	
	@Override
	public List<TiposUsuario> recuperar() {
		final String jpaquery = "SELECT tipousuario FROM TiposUsuario tipousuario";
		TypedQuery<TiposUsuario> q = em.createQuery(jpaquery, TiposUsuario.class);
		return q.getResultList();
	}
	
	@Override
	public TiposUsuario recuperarUnico(TiposUsuario tipousuario) {
		return em.find(TiposUsuario.class, tipousuario);
	}

	@Override
	public boolean verificaExistente(TiposUsuario obj) throws IndexOutOfBoundsException {
		try{
			String jpaquery = "SELECT tu FROM TiposUsuario tu WHERE tu.cnmtipousuario = '"+obj.getCnmtipousuario().trim()+"'";
			TypedQuery<TiposUsuario> q = em.createQuery(jpaquery, TiposUsuario.class);
			q.getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		return true;
    	}
		
		return false;
	}
	
	@Override
	public List<TiposUsuario> recuperarFiltrado(Map<String, Object> filtros) {
		
		String jpaquery = "SELECT tu FROM TiposUsuario tu WHERE 1=1 ";
		
		TiposUsuario obj = (TiposUsuario) filtros.get("tipousuario");
		
		
		if(obj.getCnmtipousuario()!=null && !obj.getCnmtipousuario().isEmpty()){
			jpaquery +="and tu.cnmtipousuario like '%"+obj.getCnmtipousuario()+"%' ";
		}
		
		TypedQuery<TiposUsuario> q = em.createQuery(jpaquery, TiposUsuario.class);
		
		return q.getResultList();
	}
	
	@Override
	public void incluir(TiposUsuario obj) {
		obj.setCnmtipousuario(obj.getCnmtipousuario().toUpperCase()); // For�a pra ficar em caixa alta
		em.merge(obj);
		em.flush();
	}

	@Override
	public void alterar(TiposUsuario obj) {
		em.merge(obj);
		em.detach(obj);
	}
	
}
