package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.Execucao;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface ExecucaoService extends CrudService<Execucao, Integer>{

	List<Execucao> buscarTodos();

	Execucao findByName(String name);
}
