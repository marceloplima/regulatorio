package br.com.telefonica.ssi.regulatorio.commom.interfaces.legado;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.TipoAnexo;

@Local
public interface TiposAnexosInt {
	
	public List<TipoAnexo> recuperarTiposAnexos();

}
