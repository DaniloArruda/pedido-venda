/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.converter;

import com.algaworks.model.Pedido;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;
import com.algaworks.repository.Pedidos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Danilo
 */

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {
    private Pedidos pedidos;

    public PedidoConverter() {
        this.pedidos = CDIServiceLocator.getBean(Pedidos.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            Long id = new Long(value);
            return pedidos.porId(id);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Pedido pedido = (Pedido) value;
            return pedido.getId() == null ? null : pedido.getId().toString();
        }

        return "";
    }
}
