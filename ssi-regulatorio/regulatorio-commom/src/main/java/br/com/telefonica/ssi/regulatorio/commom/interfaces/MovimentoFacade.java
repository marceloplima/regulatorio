package br.com.telefonica.ssi.regulatorio.commom.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAcionamentoArea;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseOperacional;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoAnaliseTecnica;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoConclusao;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoFollowUp;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoRevisaoPrazo;
import br.com.telefonica.ssi.regulatorio.commom.domain.MovimentoTecnico;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;

@Local
public interface MovimentoFacade extends Serializable{

	void salvaMovimento(Movimento movimento);

	void salvaMovimentoRevisaoPrazo(MovimentoRevisaoPrazo revisao);

	void salvaMovimentoAcionamentoArea(MovimentoAcionamentoArea acionamento);

	void salvaMovimentoAnaliseOperacional(MovimentoAnaliseOperacional operacional);

	void salvaMovimentoAnaliseTecnica(MovimentoAnaliseTecnica analiseTecnica);

	void salvaMovimentoConclusao(MovimentoConclusao conclusao);

	void salvaMovimentoTecnico(MovimentoTecnico movimentoTecnico);

	void salvarFollowUp(MovimentoFollowUp followUp);

	List<MovimentoAcionamentoArea> movimentosAcionamentosPorDemanda(DemandasRegulatorio demanda);

	List<MovimentoAnaliseOperacional> analisesOperacionaisPorDemanda(DemandasRegulatorio demanda);

	MovimentoAnaliseOperacional getAnaliseOperacionalPorId(Integer id);

	List<Movimento> getMovimentosPorDemanda(DemandasRegulatorio demanda);

	List<Movimento> retornarPaginadoMovimentos(int firstRow, int numberOfRows, Map<String, Object> filtros, DemandasRegulatorio demanda, Pessoas pessoa);

	int getRowCountMovimentos(Map<String, Object> filtros,DemandasRegulatorio demanda,Pessoas pessoa);

	Movimento getRowDataMovimento(Object rowKey);
}
