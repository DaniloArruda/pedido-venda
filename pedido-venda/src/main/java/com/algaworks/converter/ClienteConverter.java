/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.converter;

import com.algaworks.model.Cliente;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;
import com.algaworks.repository.Clientes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author dasilva
 */

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {
    
    private Clientes clientes;

    public ClienteConverter() {
        this.clientes = CDIServiceLocator.getBean(Clientes.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            Long id = new Long(value);
            return clientes.porId(id);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Cliente cliente = (Cliente) value;
            return cliente.getId() == null ? null : cliente.getId().toString();
        }

        return "";
    }
    
}
