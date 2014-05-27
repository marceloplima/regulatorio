package br.com.telefonica.ssi.web.datamodel;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.DemandaService;


//TODO:Generalizar este componente DataModel e colocar no framework. Esta parte pode ser gneralizada.
public class DemandasRegulatorioDataModel extends ExtendedDataModel<DemandasRegulatorio>
		implements java.io.Serializable, Arrangeable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5114954703031219608L;

	private DemandaService demandaService;

	private Object rowKey;
	private ArrangeableState arrangeableState;
	private Map<String, Object> filtros;

	public DemandasRegulatorioDataModel(DemandaService demandaint,
			Map<String, Object> filtros) {
		super();

		this.demandaService = demandaint;
		this.filtros = filtros;
	}

	public void arrange(FacesContext context, ArrangeableState state) {
		arrangeableState = state;
	}

	@Override
	public void setRowKey(Object key) {
		rowKey = key;
	}

	@Override
	public Object getRowKey() {
		return rowKey;
	}

	protected ArrangeableState getArrangeableState() {
		return arrangeableState;
	}

	@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range,
			Object argument) {
		int firstRow = ((SequenceRange) range).getFirstRow();
		int numberOfRows = ((SequenceRange) range).getRows();

		List<DemandasRegulatorio> data = demandaService.retornarPaginado(
				firstRow, numberOfRows, filtros);

		for (DemandasRegulatorio t : data) {
			visitor.process(context, getId(t), argument);
		}
	}

	@Override
	public boolean isRowAvailable() {
		return rowKey != null;
	}

	@Override
	public int getRowCount() {
		return demandaService.getRowCount(filtros);
	}

	@Override
	public DemandasRegulatorio getRowData() {

		@SuppressWarnings("unchecked")
		List<DemandasRegulatorio> eventoschecked = (List<DemandasRegulatorio>) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("demandaschecked");

		if (eventoschecked != null) {
			DemandasRegulatorio e = demandaService.getRowData(rowKey);
			if (eventoschecked.contains(e)) {
				// e.setChecked(true);
			}
			return e;
		} else {
			return demandaService.getRowData(rowKey);
		}
	}

	@Override
	public int getRowIndex() {
		return -1;
	}

	public Integer getId(Object obj) {
		DemandasRegulatorio d = (DemandasRegulatorio) obj;
		return d.getId();
	}

	public Map<String, Object> getFiltros() {
		return filtros;
	}

	public void setFiltros(Map<String, Object> filtros) {
		this.filtros = filtros;
	}

	@Override
	public Object getWrappedData() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setRowIndex(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setWrappedData(Object arg0) {
		throw new UnsupportedOperationException();
	}

}