/*
 * EntityLazyDataModel.java
 * Created on 03/06/2013
 *
 */
package br.com.telefonica.ssi.web.datamodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;
import br.com.telefonica.ssi.service.CrudService;

public class LazyPaginationDataModel<E extends AbstractEntity<Id>, Id> extends ExtendedDataModel<E> implements Serializable, Arrangeable {

	public static final int MINIMUN_PAGE_SIZE = 10;

	private static final long serialVersionUID = -1005620701250674807L;

	private List<E> datasource;

	private CrudService<E, Id> crudService;

	private Map<String, String> filters;

	@Override
	public void arrange(FacesContext arg0, ArrangeableState arg1) {

	}

	@Override
	public Object getRowKey() {
		return null;
	}

	@Override
	public void setRowKey(Object arg0) {

	}

	@Override
	public void walk(FacesContext arg0, DataVisitor arg1, Range arg2,
			Object arg3) {

	}

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public E getRowData() {
		return null;
	}

	@Override
	public int getRowIndex() {
		return 0;
	}

	@Override
	public Object getWrappedData() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isRowAvailable() {
		return false;
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