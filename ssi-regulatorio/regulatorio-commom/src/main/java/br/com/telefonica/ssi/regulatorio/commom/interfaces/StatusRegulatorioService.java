package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.StatusRegulatorio;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface StatusRegulatorioService extends CrudService<StatusRegulatorio, Integer>{
	
	StatusRegulatorio findByName(String name);
	
}
