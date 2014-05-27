package br.com.telefonica.ssi.regulatorio.service.legado;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.GruposModulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Modulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.GruposModulosInt;

@Stateless
public class GruposModulosService implements GruposModulosInt {

	@PersistenceContext(unitName="ssifwpc")
	EntityManager em;

	public GruposModulosService(){}

	@Override
	public List<Pessoas> recuperaPessoasDoGrupo(GruposModulos grupoModulo) {

		String jpaquery = "SELECT DISTINCT pessoa FROM Pessoas pessoa join fetch pessoa.pessoasgruposmodulos WHERE :grupomodulo in elements(pessoa.pessoasgruposmodulos)";
		TypedQuery<Pessoas> q = em.createQuery(jpaquery,Pessoas.class);
		q.setParameter("grupomodulo", grupoModulo);

		try{
			return q.getResultList();
		}
		catch(NoResultException e){
			return new ArrayList<Pessoas>();
		}

	}

	@Override
	public boolean verificaExistente(GruposModulos grupomodulo) throws IndexOutOfBoundsException {
		try{
			String jpaquery = "SELECT grupomodulo FROM GruposModulos grupomodulo WHERE grupomodulo.cnmgrupomodulo = '"+grupomodulo.getCnmgrupomodulo().trim()+"' and grupomodulo.modulo.idmodulo = "+grupomodulo.getModulo().getId();

			em.createQuery(jpaquery, GruposModulos.class).getResultList().get(0);
		}
    	catch(IndexOutOfBoundsException e){
    		return true;
    	}

		return false;
	}

	@Override
	public List<GruposModulos> recuperar() {
		final String jpaquery = "SELECT grupomodulo FROM GruposModulos grupomodulo";
		TypedQuery<GruposModulos> q = em.createQuery(jpaquery, GruposModulos.class);
		return q.getResultList();
	}

	@Override
	public List<GruposModulos> recuperarFiltrado(Map<String, Object> filtros) {

		String jpaquery = "SELECT grupomodulo FROM GruposModulos grupomodulo WHERE 1=1 ";

		GruposModulos grupomodulo = (GruposModulos) filtros.get("grupomodulo");
		Modulos modulo = grupomodulo.getModulo();

		if(grupomodulo!=null){

			if(!grupomodulo.getCnmgrupomodulo().isEmpty()){
				jpaquery +="and grupomodulo.cnmgrupomodulo like '%"+grupomodulo.getCnmgrupomodulo()+"%' ";
			}

			if(modulo !=null){
				jpaquery +="and grupomodulo.modulo = :modulo ";
			}

		}

		TypedQuery<GruposModulos> q = em.createQuery(jpaquery, GruposModulos.class);

		if(modulo !=null){
			q.setParameter("modulo", modulo);
		}
		return q.getResultList();
	}

	@Override
	public GruposModulos incluir(GruposModulos gm) {
		gm.setCnmgrupomodulo(gm.getCnmgrupomodulo().toUpperCase()); // For�a pra ficar em caixa alta, o nome do Grupo
		em.persist(gm);
		em.flush();
		em.refresh(gm);

		return gm;
	}

	@Override
	public GruposModulos recuperarUnico(GruposModulos grupo) {
		return em.find(GruposModulos.class, grupo);
	}

	@Override
	public void alterar(GruposModulos gm) {
		em.merge(gm);
		em.flush();
		em.detach(gm);
	}

	@Override
	public GruposModulos recuperarPorNome(String nome) {
		Query q = em.createQuery("select g from GruposModulos g where upper(g.cnmgrupomodulo) = :nome");
		q.setParameter("nome", nome.toUpperCase());
		GruposModulos result = (GruposModulos) q.getSingleResult();
		return result;
	}
}
