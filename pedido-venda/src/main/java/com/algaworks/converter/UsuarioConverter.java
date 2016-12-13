/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.converter;

import com.algaworks.model.Produto;
import com.algaworks.model.Usuario;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;
import com.algaworks.repository.Produtos;
import com.algaworks.repository.Usuarios;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author dasilva
 */

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {
    
    private Usuarios usuarios;

    public UsuarioConverter() {
        this.usuarios = CDIServiceLocator.getBean(Usuarios.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            Long id = new Long(value);
            return usuarios.porId(id);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Usuario usuario = (Usuario) value;
            return usuario.getId() == null ? null : usuario.getId().toString();
        }

        return "";
    }
    
}
