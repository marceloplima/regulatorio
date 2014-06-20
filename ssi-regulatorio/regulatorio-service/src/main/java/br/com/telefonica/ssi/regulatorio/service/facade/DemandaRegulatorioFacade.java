package br.com.telefonica.ssi.regulatorio.service.facade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.AlteracaoDemanda;
import br.com.telefonica.ssi.regulatorio.commom.cdi.qualifiers.CriarDemanda;
import br.com.telefonica.ssi.regulatorio.commom.domain.CategoriaRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.Procedencia;
import br.com.telefonica.ssi.regulatorio.commom.domain.StatusRegulatorio;
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

	@Inject
	@CriarDemanda
	private Event<DemandasRegulatorio> eventoSalvarRascunho;

	@Inject
	@AlteracaoDemanda
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
		if (demanda.getSolicitante().getPessoasareas().size() > 0) {
			demanda.setOrigem(demanda.getSolicitante().getPessoasareas().get(0));
		} else {
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
			String email = pessoaService.retornarEmailsPessoa(pessoa).size() > 0 ? pessoaService
					.retornarEmailsPessoa(pessoa).get(0).getCnmemail()
					: "";
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
	public List<Procedencia> getProcedenciasCategoriaDaDemanda(
			CategoriaRegulatorio categoria) {
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

		if (mailSolicitante != null && !mailSolicitante.trim().equals("")) {
			if (mailSolicitante.trim().matches(mail + "@" + domain)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public DemandasRegulatorio alteraAreaDemandaPorIdArea(String id,
			DemandasRegulatorio demanda) {
		if (id != null && !id.equals("")) {
			Long idArea = Long.parseLong(id);

			Query q = em
					.createQuery("select a from Areas a where a.idarea = :id");

			q.setParameter("id", idArea);

			demanda.setOrigem((Areas) q.getResultList().get(0));

			demanda.setSolicitante(areasService.recuperarPessoasArea(
					demanda.getOrigem()).get(0));
		}
		return demanda;
	}

	@Override
	public void salvaNovoEmailSolicitante(Emails email) {
		if (email != null) {
			email.setFlagativo(true);
			email.setDatacadastro(Calendar.getInstance());
			emailService.incluir(email);
		}
	}

	@Override
	public void salvarComoRascunho(DemandasRegulatorio demanda) {
		if (demanda != null) {
			demandaService.save(demanda);
		}
	}

	@Override
	public DemandasRegulatorio encaminhar(DemandasRegulatorio demanda) {
		if (demanda != null) {
			demanda.setNumeroDemanda(demandaService.getNumeroNovaDemanda());
			if (demanda.getStatus().getDescricao().equalsIgnoreCase("rascunho")) {
				StatusRegulatorio status = statusService
						.findByName("ANÁLISE PRELIMINAR");
				demanda.setStatus(status);
				demanda.setDataHoraDemanda(new Date());
				demandaService.save(demanda);
			}

			// Mensageria msg = new Mensageria();
			//
			// Modulos mod = moduloService.recuperarUnico(338L);
			//
			// msg.enviarMensagem(, assunto, listaemails, smtpserver, modulo,
			// ssi);
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

	@Override
	public List<DemandasRegulatorio> retornarPaginado(int firstRow,
			int numberOfRows, Map<String, Object> filtros, Pessoas pessoa) {
		String jpaQuery = "Select d from DemandasRegulatorio d ";

		// === OUTROS FILTROS ===

		if (filtros.get("nomeSolicitante") != null) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " upper(d.solicitante.cnmnome) like '%"
					+ filtros.get("nomeSolicitante").toString().toUpperCase()
					+ "%' ";
		}
		if (filtros.get("numeroDemanda") != null) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += "  upper(d.numeroDemanda) like '%"
					+ filtros.get("numeroDemanda").toString().toUpperCase()
					+ "%' ";
		}
		if (filtros.get("status") != null && !filtros.get("status").equals("")) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " upper(d.status.descricao) = '"
					+ filtros.get("status") + "'";
		}
		if (filtros.get("dataInicial") != null
				&& filtros.get("dataFinal") == null) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " d.dataHoraDemanda >= '"
					+ new SimpleDateFormat("yyyy-MMdd").format(filtros
							.get("dataInicial"))+"'";
		}
		if (filtros.get("dataInicial") == null
				&& filtros.get("dataFinal") != null) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " d.dataHoraDemanda <= '"
					+ new SimpleDateFormat("yyyy-MMdd").format(filtros
							.get("dataFinal"))+"'";
		}
		if (filtros.get("dataInicial") != null
				&& filtros.get("dataFinal") != null) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " d.dataHoraDemanda between '"
					+ new SimpleDateFormat("yyyy-MMdd").format(filtros
							.get("dataInicial"))+"'"+ " and '"
					+ new SimpleDateFormat("yyyy-MMdd").format(filtros
							.get("dataFinal"))+"'";
		}
		if (filtros.get("categoria") != null  && !((String)filtros.get("categoria")).equals("")) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " upper(d.categoria.descricao) =  '"+((String)filtros.get("categoria")).toUpperCase()+"' ";
		}
		if (filtros.get("procedencia") != null && !((String)filtros.get("procedencia")).equals("")) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " upper(d.procedencia.descricao) =  '"+((String)filtros.get("procedencia")).toUpperCase()+"' ";
		}
		if (filtros.get("area") != null  && !((String)filtros.get("area")).equals("")) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " upper(d.origem.cnmdescarea) =  '"+((String)filtros.get("area")).toUpperCase()+"' ";
		}
		// === FIM OUTROS FILTROS ===

		jpaQuery += " order by d.dataHoraDemanda desc";

		TypedQuery<DemandasRegulatorio> q = em.createQuery(jpaQuery,
				DemandasRegulatorio.class);

		if (firstRow > -1 && numberOfRows > 0) {
			q.setFirstResult(firstRow);
			q.setMaxResults(numberOfRows);
		}

		try {
			List<DemandasRegulatorio> result = q.getResultList();

			return result;
		} catch (NoResultException nre) {
			return new ArrayList<DemandasRegulatorio>();
		}
	}

	@Override
	public int getRowCount(Map<String, Object> filtros, Pessoas pessoa) {
		String jpaQuery = "Select count(d) from DemandasRegulatorio d ";

		// === OUTROS FILTROS ===

		if (filtros.get("nomeSolicitante") != null) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " upper(d.solicitante.cnmnome) like '%"
					+ filtros.get("nomeSolicitante").toString().toUpperCase()
					+ "%' ";
		}
		if (filtros.get("numeroDemanda") != null) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += "  upper(d.numeroDemanda) like '%"
					+ filtros.get("numeroDemanda").toString().toUpperCase()
					+ "%' ";
		}
		if (filtros.get("status") != null && !filtros.get("status").equals("")) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " upper(d.status.descricao) = '"
					+ filtros.get("status") + "'";
		}
		if (filtros.get("dataInicial") != null
				&& filtros.get("dataFinal") == null) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " d.dataHoraDemanda >= '"
					+ new SimpleDateFormat("yyyy-MMdd").format(filtros
							.get("dataInicial"))+"'";
		}
		if (filtros.get("dataInicial") == null
				&& filtros.get("dataFinal") != null) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " d.dataHoraDemanda <= '"
					+ new SimpleDateFormat("yyyy-MMdd").format(filtros
							.get("dataFinal"))+"'";
		}
		if (filtros.get("dataInicial") != null
				&& filtros.get("dataFinal") != null) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " d.dataHoraDemanda between '"
					+ new SimpleDateFormat("yyyy-MMdd").format(filtros
							.get("dataInicial"))+"'"+ " and '"
					+ new SimpleDateFormat("yyyy-MMdd").format(filtros
							.get("dataFinal"))+"'";
		}
		if (filtros.get("categoria") != null  && !((String)filtros.get("categoria")).equals("")) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " upper(d.categoria.descricao) =  '"+((String)filtros.get("categoria")).toUpperCase()+"' ";
		}
		if (filtros.get("procedencia") != null && !((String)filtros.get("procedencia")).equals("")) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " upper(d.procedencia.descricao) =  '"+((String)filtros.get("procedencia")).toUpperCase()+"' ";
		}
		if (filtros.get("area") != null  && !((String)filtros.get("area")).equals("")) {
			if (jpaQuery.indexOf("where") == -1) {
				jpaQuery += "where ";
			} else {
				jpaQuery += ("and ");
			}
			jpaQuery += " upper(d.origem.cnmdescarea) =  '"+((String)filtros.get("area")).toUpperCase()+"' ";
		}
		// === FIM OUTROS FILTROS ===

		TypedQuery<Long> q = em.createQuery(jpaQuery, Long.class);

		try {
			Long result = q.getSingleResult();

			return result.intValue();
		} catch (NoResultException nre) {
			return 0;
		}
	}

	@Override
	public DemandasRegulatorio getRowData(Object rowKey) {
		return em.find(DemandasRegulatorio.class, rowKey);
	}

}
