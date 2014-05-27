package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.Complexidade;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface ComplexidadeService extends CrudService<Complexidade, Integer>{

	List<Complexidade> buscarTodos();
	Complexidade findByName(String name);

}
