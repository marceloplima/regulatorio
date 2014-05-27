package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoTecnico;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface MovimentoTecnicoService extends CrudService<MovimentoTecnico, Integer>{

}
