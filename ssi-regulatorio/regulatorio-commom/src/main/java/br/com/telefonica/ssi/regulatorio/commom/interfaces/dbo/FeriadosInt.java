package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.FeriadosLocais;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.FeriadosNacionais;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Ufs;

@Local
public interface FeriadosInt {

	public List<FeriadosNacionais> recuperarFeriadosNacionaisPorPeriodo(Calendar dataInicial,Calendar dataFinal);
	public List<FeriadosLocais> recuperarFeriadosLocaisPorPeriodo(Calendar dataInicial,Calendar dataFinal, List<Ufs> ufs);
	
}
