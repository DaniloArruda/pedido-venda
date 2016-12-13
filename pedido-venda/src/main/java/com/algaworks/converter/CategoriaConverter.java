/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.converter;

import com.algaworks.model.Categoria;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;
import com.algaworks.repository.Categorias;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author dasilva
 */

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {
    
    //@Inject
    private Categorias categorias;

    public CategoriaConverter() {
        this.categorias = CDIServiceLocator.getBean(Categorias.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            Long id = new Long(value);
            return categorias.porId(id);
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Categoria) value).getId().toString();
        }
        
        return "";
    }
    
}
