package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.DocumentoOrigem;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface DocumentoOrigemService extends CrudService<DocumentoOrigem, Integer>{
	List<DocumentoOrigem> recuperaTodos();
}
