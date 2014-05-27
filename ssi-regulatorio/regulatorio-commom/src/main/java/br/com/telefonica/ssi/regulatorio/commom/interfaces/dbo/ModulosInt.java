package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Modulos;

@Local
public interface ModulosInt {
	public Modulos incluir(Modulos modulo);
	public void alterar(Modulos modulo);
	public List<Modulos> recuperar();
	public Modulos recuperarUnico(Long idmodulo);
	public List<Modulos> buscarModulos(Modulos modulo);
	public boolean verificaExistente(Modulos modulo);
	public void remover(Modulos modulo);
}
