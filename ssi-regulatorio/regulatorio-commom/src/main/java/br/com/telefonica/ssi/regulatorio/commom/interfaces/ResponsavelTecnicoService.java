package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.ResponsavelTecnico;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface ResponsavelTecnicoService extends CrudService<ResponsavelTecnico, Integer> {

}
