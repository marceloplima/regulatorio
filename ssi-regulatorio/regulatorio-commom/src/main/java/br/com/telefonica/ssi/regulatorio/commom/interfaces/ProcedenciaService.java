package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.CategoriaRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Procedencia;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface ProcedenciaService extends CrudService<Procedencia, Integer>{
	
	List<Procedencia> findByCategoria(CategoriaRegulatorio categoria);
	
}
