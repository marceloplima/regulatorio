package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoRevisaoPrazo;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface MovimentoRevisaoPrazoService extends CrudService<MovimentoRevisaoPrazo, Integer>{

}
