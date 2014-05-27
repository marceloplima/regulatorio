package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.PapeisAreas;

@Local
public interface PapeisAreasInt {
	
	public List<PapeisAreas> recuperar();
	public PapeisAreas recuperarUnico(PapeisAreas area);

}
