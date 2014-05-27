package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Dominios;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Usuarios;

@Local
public interface AutenticadorInt {

	public void setUsuario(Usuarios usuario);
	public void setDominio(Dominios dominio);
	public boolean autenticar();
}
