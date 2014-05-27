package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.CategoriaRegulatorio;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface CategoriaService extends CrudService<CategoriaRegulatorio, Integer>{
	
	CategoriaRegulatorio findByName(String name);
	
}
