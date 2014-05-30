package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Local
public interface ParticipantesService{

	Pessoas recuperarResponsavelCategoria(DemandasRegulatorio demanda);

}