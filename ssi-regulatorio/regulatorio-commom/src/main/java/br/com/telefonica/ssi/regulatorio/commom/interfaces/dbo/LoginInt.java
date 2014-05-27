package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Funcionalidades;

@Local
public interface LoginInt {
	public boolean autenticar();
	public List<Areas> verificarAcessoAreas();
	public List<Funcionalidades> verificarAcessoSistema();
}
