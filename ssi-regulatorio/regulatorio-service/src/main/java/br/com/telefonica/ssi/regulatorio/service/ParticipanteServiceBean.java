package br.com.telefonica.ssi.regulatorio.service;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.GruposModulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ParticipantesService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.GruposModulosInt;

@SuppressWarnings("all")
@Stateless
public class ParticipanteServiceBean implements ParticipantesService{

	@EJB
	private GruposModulosInt gruposModulosService;

	@Override
	public Pessoas recuperarResponsavelCategoria(DemandasRegulatorio demanda) {

		GruposModulos gmodulos = gruposModulosService.recuperarPorNome(demanda.getCategoria().getDescricao());

		Pessoas responsavel = null;

		List<Pessoas> pessoas = gmodulos.getGruposmodulospessoas();

		Iterator<Pessoas> it = pessoas.iterator();

		while(it.hasNext()){
			Pessoas p = it.next();
			if(p.getCargo().getCnmcargo().equalsIgnoreCase("gestor")){
				responsavel = p;
				break;
			}
		}
		if(responsavel ==null){
			return new Pessoas();
		}

		return responsavel;
	}



	/*@Override
	public List<Movimento> todosPorDemanda(DemandasRegulatorio demanda) {
		Query q = getEntityManager().createQuery("select m from Movimento m where m.demanda = :demanda order by m.dataHora desc");
		q.setParameter("demanda", demanda);
		List<Movimento> result = q.getResultList();
		return result;
	}*/

}