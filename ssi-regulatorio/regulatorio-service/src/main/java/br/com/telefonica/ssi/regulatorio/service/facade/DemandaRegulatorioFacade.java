package br.com.telefonica.ssi.regulatorio.service.facade;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.AlteracaoDemanda;
import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.CriarDemanda;
import br.com.telefonica.ssi.regulatorio.commom.domain.AreasRegionais;
import br.com.telefonica.ssi.regulatorio.commom.domain.CategoriaRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Procedencia;
import br.com.telefonica.ssi.regulatorio.commom.domain.StatusRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.UF;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Areas;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Emails;
import br.com.telefonica.ssi.regulatorio.commom.domain.dbo.Pessoas;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.AreaRegionalService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.CategoriaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.DemandaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.ProcedenciaService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.StatusRegulatorioService;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.AreasInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.EmailsInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.ModulosInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.dbo.PessoasInt;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.facade.DemandaServiceFacade;

@Stateless
public class DemandaRegulatorioFacade implements DemandaServiceFacade {

	@Inject @CriarDemanda
	private Event<DemandasRegulatorio> eventoSalvarRascunho;

	@Inject @AlteracaoDemanda
	private Event<DemandasRegulatorio> enventoEncaminhar;

	@PersistenceContext
	private EntityManager em;

	/**
	 *
	 */
	private static final long serialVersionUID = 4345587753053772267L;

	@EJB
	private PessoasInt pessoaService;

	@EJB
	private DemandaService demandaService;

	@EJB
	private EmailsInt emailService;

	@EJB
	private StatusRegulatorioService statusService;

	@EJB
	private CategoriaService categoriaService;

	@EJB
	private ProcedenciaService procedenciaService;

	@EJB
	private AreasInt areasService;

	@EJB
	private ModulosInt moduloService;

	@EJB
	private AreaRegionalService areaService;

	@Override
	public DemandasRegulatorio criaNovaDemanda() {
		return new DemandasRegulatorio();
	}

	@Override
	public void salvaDemanda(DemandasRegulatorio demanda) {
		demandaService.save(demanda);
	}

	@Override
	public DemandasRegulatorio recuperaDemanda(Integer id) {
		DemandasRegulatorio demanda = demandaService.findById(id);
		return demanda;
	}

	@Override
	public DemandasRegulatorio criaNovaDemanda(Pessoas autor) {
		DemandasRegulatorio demanda = new DemandasRegulatorio();
		demanda.setAutor(autor);
		demanda.setSolicitante(autor);
		demanda.setStatus(statusService.findByName("rascunho"));
		demanda.setDataHoraDemanda(new Date());
		demanda.setCategoria(categoriaService.findByName("regulatório"));
		if(demanda.getSolicitante().getPessoasareas().size()>0){
			demanda.setOrigem(demanda.getSolicitante().getPessoasareas().get(0));
		}
		else{
			demanda.setOrigem(demanda.getSolicitante().getArea());
		}
		demanda.setProcedencia(procedenciaService.findAll().get(0));
		return demanda;
	}

	@Override
	public DemandasRegulatorio novaDemanda() {
		return new DemandasRegulatorio();
	}

	@Override
	public String getEmailSolicitante(Pessoas pessoa) {
		if (pessoa != null) {
			String email = pessoaService.retornarEmailsPessoa(pessoa)
					.size() > 0 ? pessoaService
					.retornarEmailsPessoa(pessoa).get(0)
					.getCnmemail() : "";
			return email;
		} else {
			return "";
		}
	}

	@Override
	public void setEmailSolicitante(Emails email) {
		emailService.incluir(email);
	}


	@Override
	public List<Areas> getAreasSolicitante(Pessoas pessoa) {
		return pessoaService.retornarAreasPessoa(pessoa);
	}

	@Override
	public List<Procedencia> getProcedencias() {
		return procedenciaService.findAll();
	}

	@Override
	public List<Procedencia> getProcedenciasCategoriaDaDemanda(CategoriaRegulatorio categoria) {
		return procedenciaService.findByCategoria(categoria);
	}


	@Override
	public List<Pessoas> pessoasAreaOrigem(Areas area) {
		if (area != null) {
			return areasService.recuperarPessoasArea(area);
		} else {
			return null;
		}
	}

	@Override
	public boolean solicitantePossuiEmail(Pessoas pessoa) {
		String mailSolicitante = getEmailSolicitante(pessoa);

		String mail = "[a-zA-Z]+[a-zA-Z0-9]*([\\.|\\-|_][a-zA-Z0-9]+)*";
		String domain = "[a-zA-Z]+[a-zA-Z0-9]*([\\.|\\-|_][a-zA-Z0-9]+)+";

		if(mailSolicitante!=null && !mailSolicitante.trim().equals("")){
			if(mailSolicitante.trim().matches(mail+"@"+domain)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}

	@Override
	public DemandasRegulatorio alteraAreaDemandaPorIdArea(String id,DemandasRegulatorio demanda) {
		if(id !=null && !id.equals("")){
			Long idArea = Long.parseLong(id);

			Query q = em.createQuery("select a from Areas a where a.idarea = :id");

			q.setParameter("id", idArea);

			demanda.setOrigem((Areas)q.getResultList().get(0));

			demanda.setSolicitante(areasService.recuperarPessoasArea(demanda.getOrigem()).get(0));
		}
		return demanda;
	}

	@Override
	public void salvaNovoEmailSolicitante(Emails email) {
		if(email!=null){
			email.setFlagativo(true);
			email.setDatacadastro(Calendar.getInstance());
			emailService.incluir(email);
		}
	}

	@Override
	public void salvarComoRascunho(DemandasRegulatorio demanda) {
		if(demanda!=null){
			demandaService.save(demanda);
		}
	}

	@Override
	public DemandasRegulatorio encaminhar(DemandasRegulatorio demanda) {
		if(demanda!=null){
			demanda.setNumeroDemanda(demandaService.getNumeroNovaDemanda());
			if(demanda.getStatus().getDescricao().equalsIgnoreCase("rascunho")){
				StatusRegulatorio status = statusService.findByName("ANÁLISE PRELIMINAR");
				demanda.setStatus(status);
				demanda.setDataHoraDemanda(new Date());
				demandaService.save(demanda);
			}

//			Mensageria msg = new Mensageria();
//
//			Modulos mod = moduloService.recuperarUnico(338L);
//
//			msg.enviarMensagem(, assunto, listaemails, smtpserver, modulo, ssi);
		}
		return demanda;
	}

	public PessoasInt getPessoaService() {
		return pessoaService;
	}

	public DemandaService getDemandaService() {
		return demandaService;
	}

	public EmailsInt getEmailService() {
		return emailService;
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public ProcedenciaService getProcedenciaService() {
		return procedenciaService;
	}

	public AreasInt getAreasService() {
		return areasService;
	}

	public StatusRegulatorioService getStatusService() {
		return statusService;
	}

}
