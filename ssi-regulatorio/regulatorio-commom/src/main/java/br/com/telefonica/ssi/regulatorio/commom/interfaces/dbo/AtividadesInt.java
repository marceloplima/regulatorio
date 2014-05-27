package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Atividades;

@Local
public interface AtividadesInt {
	public List<Atividades> recuperar();
	public Atividades recuperarUnico(Atividades obj);
	public boolean verificaExistente(Atividades obj) throws IndexOutOfBoundsException;
	public List<Atividades> recuperarFiltrado(Map<String, Object> filtros);
	public List<Areas> retornarAreasAtividade(Atividades ativ);
	public void incluir(Atividades obj);
	public void alterar(Atividades obj);
	public List<Atividades> recuperarporarea(Areas area);
}
