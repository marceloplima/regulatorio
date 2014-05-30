package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import br.com.telefonica.ssi.regulatorio.commom.domain.UF;
import br.com.telefonica.ssi.service.CrudService;

public interface UFService extends CrudService<UF, Integer>{

	UF findByName(String name);

}
