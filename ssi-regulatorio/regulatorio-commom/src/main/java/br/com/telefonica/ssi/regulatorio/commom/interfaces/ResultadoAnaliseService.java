package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.ResultadoAnalise;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface ResultadoAnaliseService extends CrudService<ResultadoAnalise, Integer>{

	List<ResultadoAnalise> buscarTodos();

}
