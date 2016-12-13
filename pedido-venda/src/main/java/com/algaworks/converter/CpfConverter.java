/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author dasilva
 */

@FacesConverter("com.algaworks.CpfConverter")
public class CpfConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String cpf = value;
        
        if (value != null && !value.equals(""))
            cpf = value.replaceAll("\\.", "").replaceAll("\\-", "");
        return cpf;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String cpf = (String) value;
        if (cpf != null && cpf.length() == 11) {
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
            return cpf;
        } else
            return null;
    }
    
}
