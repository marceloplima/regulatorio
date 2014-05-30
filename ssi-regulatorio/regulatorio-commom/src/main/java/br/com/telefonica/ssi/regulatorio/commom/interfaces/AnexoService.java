package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.io.File;
import java.util.List;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.AnexosRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.TipoAnexo;
import br.com.telefonica.ssi.service.CrudService;

@Local
public interface AnexoService extends CrudService<AnexosRegulatorio, Integer>{

	List<AnexosRegulatorio> buscarAnexos(DemandasRegulatorio demanda);

	void salvarAnexo(AnexosRegulatorio anexo, File file);

	TipoAnexo recuperarTipoAnexo(Long idtipoanexo);

	AnexosRegulatorio incluir(AnexosRegulatorio anexo);

}
