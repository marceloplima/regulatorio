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
import javax.faces.model.DataModel;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;

import br.com.telefonica.ssi.core.domain.patterns.entity.AbstractEntity;
import br.com.telefonica.ssi.service.CrudService;



/**
 *
 * Classe responsável pela paginação do primefaces - datatable
 *
 * @author <a href='mailto:marcelo.batista@druid.com.br'>Marcelo Batista</a>
 * @date 03/06/2013 14:00:42
 * @version $Id: EntityLazyDataModel.java 11114 2013-08-13 13:01:57Z marcelo.batista $
 */
public class EntityLazyDataModel/*<E extends AbstractEntity<Id>, Id> extends ExtendedDataModel<E> implements Serializable  */{

	public static final int MINIMUN_PAGE_SIZE = 10;

	/*private static final long serialVersionUID = -1005620701250674807L;

	private List<E> datasource;

    private CrudService<E, Id> crudService;

    private Map<String,String> filters;

    public EntityLazyDataModel(CrudService<E, Id> crudService, Map<String,String> filters) {
        this.crudService = crudService;
        this.filters = filters;
    }*/

  /*  public List<E> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
    	//Seta o pageSize passado ou o Default
    	super.setPageSize(pageSize);

    	this.setDatasource(this.getCrudService().findByFilter(this.getFilters(), first, super.getPageSize()));
        setRowCount(this.getCrudService().countByFilter(this.getFilters()).intValue());

        return this.getDatasource();
    }

    public void setPageSize(int pageSize) {
    	super.setPageSize( (pageSize == 0) ? MINIMUN_PAGE_SIZE : pageSize);
	}

    @Override
    public void setRowIndex(int rowIndex) {
		super.setRowIndex( ((rowIndex == -1) ? rowIndex : rowIndex % this.getPageSize()) );
	}

    public E getRowData(String rowKey) {
        for(E entity : datasource) {
            if(entity.getId().equals(rowKey))
                return entity;
        }

        return null;
    }

    public Object getRowKey(E entity) {
        return entity.getId();
    }

    protected List<E> getDatasource() {
		return datasource;
	}

	protected void setDatasource(List<E> datasource) {
		this.datasource = datasource;
	}

	protected Map<String, String> getFilters() {
		return filters;
	}

	protected void setFilters(Map<String, String> filters) {
		this.filters = filters;
	}

	protected CrudService<E, Id> getCrudService() {
		return crudService;
	}

	protected void setCrudService(CrudService<E, Id> crudService) {
		this.crudService = crudService;
	}

	@Override
	public Object getRowKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRowKey(Object arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void walk(FacesContext arg0, DataVisitor arg1, Range arg2,
			Object arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public E getRowData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRowIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getWrappedData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWrappedData(Object arg0) {
		// TODO Auto-generated method stub

	}*/
}