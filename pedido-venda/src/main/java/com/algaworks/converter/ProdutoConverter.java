/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.converter;

import com.algaworks.model.Produto;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;
import com.algaworks.repository.Produtos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author dasilva
 */

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {
    private Produtos produtos;

    public ProdutoConverter() {
        this.produtos = CDIServiceLocator.getBean(Produtos.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            Long id = new Long(value);
            return produtos.porId(id);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Produto produto = (Produto) value;
            return produto.getId() == null ? null : produto.getId().toString();
        }

        return "";
    }
}
