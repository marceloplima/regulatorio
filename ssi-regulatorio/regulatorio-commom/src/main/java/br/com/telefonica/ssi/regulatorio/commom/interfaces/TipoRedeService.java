package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.util.List;

import br.com.telefonica.ssi.regulatorio.commom.domain.TipoRede;
import br.com.telefonica.ssi.service.CrudService;

public interface TipoRedeService extends CrudService<TipoRede, Integer>{
	public List<TipoRede> recuperaTodos();
}
