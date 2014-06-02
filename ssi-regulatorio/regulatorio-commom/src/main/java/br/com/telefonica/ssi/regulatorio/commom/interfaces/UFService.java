package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.UF;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface UFService extends CrudService<UF, Integer>{

	UF findByName(String name);

	
	
}
