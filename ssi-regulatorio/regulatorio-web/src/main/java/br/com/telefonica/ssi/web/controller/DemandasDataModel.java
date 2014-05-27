package br.com.telefonica.ssi.web.controller;
/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */


public class DemandasDataModel /*extends ExtendedDataModel<DemandasRegulatorio> implements java.io.Serializable, Arrangeable*/ {

    /**
	 *
	 */
	private static final long serialVersionUID = -5114954703031219608L;

	//@EJB
	/*private DemandaService demandaint;

	private Object rowKey;
    private ArrangeableState arrangeableState;
    private Map<String,Object> filtros;*/

  /*  public DemandasDataModel(DemandaService demandaint, Map<String,Object>filtros) {
        super();

        this.demandaint = demandaint;
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
    public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) {
    	int firstRow = ((SequenceRange)range).getFirstRow();
		int numberOfRows = ((SequenceRange)range).getRows();

		List<DemandasRegulatorio>data = demandaint.retornarPaginado(firstRow, numberOfRows, filtros);

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
    	return demandaint.getRowCount(filtros);
    	return 0;
    }

    @Override
    public DemandasRegulatorio getRowData() {

    	@SuppressWarnings("unchecked")
		List<DemandasRegulatorio>eventoschecked = (List<DemandasRegulatorio>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("demandaschecked");

    	if(eventoschecked != null){
    		DemandasRegulatorio e = demandaint.getRowData(rowKey);
    		if(eventoschecked.contains(e)){
    			e.setChecked(true);
    		}
    		return e;
    	}
    	else{
    	return demandaint.getRowData(rowKey);
    	}
    	return null;
    }

    @Override
    public int getRowIndex() {
        return -1;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getWrappedData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWrappedData(Object data) {
        throw new UnsupportedOperationException();
    }

	public Integer getId(Object obj) {
		DemandasRegulatorio d = (DemandasRegulatorio)obj;
		return d.getId();
	}



	public Map<String,Object> getFiltros() {
		return filtros;
	}



	public void setFiltros(Map<String,Object> filtros) {
		this.filtros = filtros;
	}
*/
}