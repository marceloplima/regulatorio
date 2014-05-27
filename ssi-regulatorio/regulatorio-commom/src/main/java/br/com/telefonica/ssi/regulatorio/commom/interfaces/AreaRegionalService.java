package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.AreasRegionais;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface AreaRegionalService extends CrudService<AreasRegionais, Long>{

	List<AreasRegionais> recuperarTodos();

}
