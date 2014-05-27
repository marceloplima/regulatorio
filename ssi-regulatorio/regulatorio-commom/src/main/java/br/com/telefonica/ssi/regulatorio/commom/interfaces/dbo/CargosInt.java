package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Cargos;

@Local
public interface CargosInt {

	public List<Cargos> recuperar();
	public Cargos recuperarUnico(Cargos grupo);
	
}
