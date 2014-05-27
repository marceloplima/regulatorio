package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Modulos;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Noticias;

@Local
public interface NoticiasInt {

	public List<Noticias> recuperar();
	public Noticias recuperarUnico(Noticias noticia);
	public void alterar(Noticias noticia);
	public boolean verificaExistente(Noticias noticia) throws IndexOutOfBoundsException;
	public List<Noticias> recuperarFiltrado(Map<String, Object> filtros);
	public Noticias incluir(Noticias noticia);
	public List<Modulos> retornarModulosNoticias(Noticias noticia);
}
