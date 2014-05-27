package br.com.telefonica.ssi.regulatorio.commom.interfaces.legado;


import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.PapeisAreas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Local
public interface DemandasServiceInt {

	DemandasRegulatorio setaPessoaComQuemEsta(Pessoas pessoa,DemandasRegulatorio demanda);
	DemandasRegulatorio setaGrupoComQuemEsta(PapeisAreas papel,DemandasRegulatorio demanda);
	
}
