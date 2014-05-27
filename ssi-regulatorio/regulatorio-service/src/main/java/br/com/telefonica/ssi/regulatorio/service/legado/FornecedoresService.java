package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Fornecedores;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.TiposFornecedor;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.FornecedoresInt;

@Stateless
public class FornecedoresService implements FornecedoresInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;
	
	public FornecedoresService(){}
	
	@Override
	public boolean verificaExistente(Fornecedores obj) throws IndexOutOfBoundsException {
		try{
			String jpaquery = "SELECT fornecedor FROM Fornecedores fornecedor WHERE fornecedor.cnmfornecedor = '"+obj.getCnmfornecedor().trim()+"'";
			TypedQuery<Fornecedores> q = em.createQuery(jpaquery, Fornecedores.class);
			q.getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		return true;
    	}
		
		return false;
	}
	
	@Override
	public List<TiposFornecedor> retornarTiposFornecedorFornecedor(Fornecedores fornecedor){
		String jpaquery = "SELECT DISTINCT tiposfornecedor FROM TiposFornecedor tiposfornecedor join fetch tiposfornecedor.fornecedores WHERE :fornecedor in elements(tiposfornecedor.fornecedores)";
		TypedQuery<TiposFornecedor> q = em.createQuery(jpaquery,TiposFornecedor.class);
		q.setParameter("fornecedor", fornecedor);
		return q.getResultList();
	}
	
	@Override
	public List<Fornecedores> recuperar() {
		final String jpaquery = "SELECT fornecedor FROM Fornecedores fornecedor ORDER BY fornecedor.cnmfornecedor";
		TypedQuery<Fornecedores> q = em.createQuery(jpaquery, Fornecedores.class);
		return q.getResultList();
	}
	
	@Override
	public List<Fornecedores> recuperarFiltrado(Map<String, Object> filtros) {
		
		String jpaquery = "SELECT fornecedor FROM Fornecedores fornecedor WHERE 1=1 ";
		
		TiposFornecedor tipofornecedor = (TiposFornecedor) filtros.get("tipofornecedor");
				
		Fornecedores fornecedor = (Fornecedores) filtros.get("fornecedor");
		
		if(fornecedor.getCnmfornecedor()!=null && !fornecedor.getCnmfornecedor().isEmpty()){
			jpaquery +="and fornecedor.cnmfornecedor like '%"+fornecedor.getCnmfornecedor()+"%' ";
		}
		
		if(tipofornecedor!=null){
			jpaquery +="and :tipofornecedor in elements(fornecedor.tiposfornecedor)  ";
		}
		
		TypedQuery<Fornecedores> q = em.createQuery(jpaquery, Fornecedores.class);
		
		if(tipofornecedor!=null){
			
			q.setParameter("tipofornecedor", tipofornecedor);
		}
		
		return q.getResultList();
	}
	
	@Override
	public void incluir(Fornecedores obj) {
		obj.setCnmfornecedor(obj.getCnmfornecedor().toUpperCase()); // For�a pra ficar em caixa alta, o nome do Grupo
		obj.remapearItens(obj);
		em.merge(obj);
		em.flush();
	}
	
	@Override
	public Fornecedores recuperarUnico(Fornecedores obj) {
		if(em==null){
			System.out.println("EM VAZIO");
		}
		return em.find(Fornecedores.class, obj);
	}

	@Override
	public void alterar(Fornecedores obj) {
		em.merge(obj);
		em.detach(obj);
	}

	@Override
	public List<String> recuperarAutocomplete(String autobusca) {
		final String jpaquery = "SELECT fornecedor.cnmfornecedor FROM Fornecedores fornecedor WHERE fornecedor.cnmfornecedor like '%"+autobusca+"%'";
		TypedQuery<String> q = em.createQuery(jpaquery, String.class);
		return q.getResultList();
	}
	
	@Override
	public Fornecedores recuperarPorNome(String cnmfornecedor) {
		final String jpaquery = "SELECT fornecedor from Fornecedores fornecedor WHERE fornecedor.cnmfornecedor = '"+cnmfornecedor+"'";
		
		TypedQuery<Fornecedores> q = em.createQuery(jpaquery, Fornecedores.class);
		
		try{
			return q.getResultList().get(0);
		}
		catch(IndexOutOfBoundsException e){
			return new Fornecedores();
		}
	}
	
}
