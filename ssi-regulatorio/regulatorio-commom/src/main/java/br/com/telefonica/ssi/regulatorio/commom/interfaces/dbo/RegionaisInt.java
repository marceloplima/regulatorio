package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Regionais;

@Local
public interface RegionaisInt {
	public List<Regionais> recuperar();
	public Regionais recuperarUnico(Regionais regional);
}
