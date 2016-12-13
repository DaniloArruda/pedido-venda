/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.converter;

import com.algaworks.model.Grupo;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;
import com.algaworks.repository.Grupos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author dasilva
 */

@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {
    private Grupos grupos;

    public GrupoConverter() {
        this.grupos = CDIServiceLocator.getBean(Grupos.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            Long id = new Long(value);
            return grupos.porId(id);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Grupo grupo = (Grupo) value;
            return grupo.getId() == null ? null : grupo.getId().toString();
        }

        return "";
    }
    
}
