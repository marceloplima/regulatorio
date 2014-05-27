package br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.ConfigSMTP;

@Local
public interface ConfigSMTPInt {
	public List<ConfigSMTP> recuperar();
	public ConfigSMTP recuperarUnico(Long idsmtp);
	public boolean verificaExistente(ConfigSMTP obj) throws IndexOutOfBoundsException;
	public List<ConfigSMTP> recuperarFiltrado(Map<String, Object> filtros);
	public void incluir(ConfigSMTP obj);
	public void alterar(ConfigSMTP obj);
}
