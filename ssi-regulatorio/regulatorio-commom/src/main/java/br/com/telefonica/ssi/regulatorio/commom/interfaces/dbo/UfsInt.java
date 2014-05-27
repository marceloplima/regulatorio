package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Ufs;

@Local
public interface UfsInt {

	public List<Ufs> recuperar();
	public Ufs recuperaUnico(Ufs uf);
	
}
