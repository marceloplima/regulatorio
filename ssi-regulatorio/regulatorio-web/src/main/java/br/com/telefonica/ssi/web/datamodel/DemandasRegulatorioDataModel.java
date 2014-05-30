package br.com.telefonica.ssi.web.datamodel;
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
import br.com.telefonica.ssi.regulatorio.commom.domain.Movimento;
import br.com.telefonica.ssi.regulatorio.commom.interfaces.facade.DemandaServiceFacade;
import br.com.telefonica.ssi.web.utils.RecuperadorInstanciasBean;


public class DemandasRegulatorioDataModel extends ExtendedDataModel<DemandasRegulatorio> implements java.io.Serializable, Arrangeable {

    /**
	 *
	 */
	private static final long serialVersionUID = -5114954703031219608L;


	private DemandaServiceFacade demandFacade;

	private Object rowKey;
    private ArrangeableState arrangeableState;
    private Map<String,Object> filtros;

    public DemandasRegulatorioDataModel(DemandaServiceFacade demandaFacade, Map<String,Object>filtros,DemandasRegulatorio demanda) {
        super();

        this.demandFacade = demandaFacade;
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

		List<DemandasRegulatorio>data = demandFacade.retornarPaginado(firstRow, numberOfRows, filtros,RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado());

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
    	return demandFacade.getRowCount(filtros,RecuperadorInstanciasBean.recuperarInstanciaLoginBean().recuperarPessoaLogado());
    }

    @Override
    public DemandasRegulatorio getRowData() {
    	return demandFacade.getRowData(rowKey);
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

}