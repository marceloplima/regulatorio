package br.com.telefonica.ssi.web.datamodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.model.DataModel;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;
import br.com.telefonica.ssi.service.CrudService;

@SuppressWarnings("all")
public class PagedDataModel <E extends AbstractEntity<Id>, Id> extends DataModel<E> implements Serializable {

	private int rowIndex = -1;
	private int totalNumRows;
	private int pageSize;
	private List<E> datasource;
	private CrudService<E, Id> crudService;
	private Map<String, String> filters;


	public PagedDataModel() {
		super();
	}

	@SuppressWarnings("unchecked")
	public PagedDataModel(CrudService<E, Id> crudService, Map<String,String> filters, int pageSize) {
//		super();
//
//		this.crudService = crudService;
//
//		this.filters = filters;
//
//		setWrappedData(crudService.findBy);
//		this.totalNumRows = totalNumRows;
//		this.pageSize = list.size();
	}

	@SuppressWarnings("unchecked")
	public PagedDataModel(List list, int totalNumRows, int pageSize) {
		super();
		setWrappedData(list);
		this.totalNumRows = totalNumRows;
		this.pageSize = pageSize;
	}

	@Override
	public boolean isRowAvailable() {
		if (datasource == null)
			return false;

		int rowIndex = getRowIndex();
		if (rowIndex >= 0 && rowIndex < datasource.size())
			return true;
		else
			return false;
	}

	@Override
	public int getRowCount() {
		return totalNumRows;
	}

	@Override
	public E getRowData() {
		if (datasource == null)
			return null;
		else if (!isRowAvailable())
			throw new IllegalArgumentException();
		else {
			int dataIndex = getRowIndex();
			return datasource.get(dataIndex);
		}
	}

	@Override
	public int getRowIndex() {
		try {
			return (rowIndex % pageSize);
		} catch (ArithmeticException e) {
			return 0;
		}
	}

	@Override
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	@Override
	public Object getWrappedData() {
		return datasource;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setWrappedData(Object list) {
		this.datasource = (List) list;
	}

	public List<E> getDatasource() {
		return datasource;
	}

	public void setDatasource(List<E> datasource) {
		this.datasource = datasource;
	}

	public CrudService<E, Id> getCrudService() {
		return crudService;
	}

	public void setCrudService(CrudService<E, Id> crudService) {
		this.crudService = crudService;
	}
}
