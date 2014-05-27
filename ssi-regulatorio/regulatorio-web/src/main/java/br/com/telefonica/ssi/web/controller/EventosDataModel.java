package br.com.telefonica.ssi.web.controller;

import java.util.List;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
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

import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;

import br.com.telefonica.ssi.regulatorio.commom.domain.DemandasRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.domain.EventosRegulatorio;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.legado.EventosInt;
 
 
public class EventosDataModel extends ExtendedDataModel<EventosRegulatorio> implements java.io.Serializable, Arrangeable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5114954703031219608L;
	
//	private EventosInt eventosint;
	
	private Object rowKey;
    private ArrangeableState arrangeableState;
    private EventosRegulatorio evento;
    private DemandasRegulatorio demanda;
    
    public EventosDataModel(EventosInt eventosint, DemandasRegulatorio demanda) {
        super();
        
//        this.eventosint = eventosint;
        this.demanda = demanda;
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
 
    protected EventosRegulatorio getEvento() {
        return evento;
    }
 
    @Override
    public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) {
    	int firstRow = ((SequenceRange)range).getFirstRow();
		int numberOfRows = ((SequenceRange)range).getRows();
		
//		List<EventosRegulatorio>data = eventosint.retornarPaginado(firstRow, numberOfRows, demanda);
		
//        for (EventosRegulatorio t : data) {
//            visitor.process(context, getId(t), argument);
//        }
    }
 
    @Override
    public boolean isRowAvailable() {
        return rowKey != null;
    }
 
    @Override
    public int getRowCount() {
//    	return eventosint.getRowCount(demanda);
    	return 0;
    }
 
    @Override
    public EventosRegulatorio getRowData() {
    	
    	@SuppressWarnings("unchecked")
		List<EventosRegulatorio>eventoschecked = (List<EventosRegulatorio>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("eventoschecked");
		
//    	if(eventoschecked != null){
//    		EventosRegulatorio e = eventosint.getRowData(rowKey);
////    		if(eventoschecked.contains(e)){
////    			e.setChecked(true);
////    		}
//    		return e;
//    	}
//    	else{
//    		return eventosint.getRowData(rowKey);
//    	}
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
		EventosRegulatorio e = (EventosRegulatorio)obj;
		return e.getId();
	}

}