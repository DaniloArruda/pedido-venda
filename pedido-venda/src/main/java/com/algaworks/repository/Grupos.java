/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.repository;

import com.algaworks.model.Grupo;
import com.algaworks.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author dasilva
 */
public class Grupos implements Serializable {
    
    @Inject
    private EntityManager manager;
    
    public Grupo guardar(Grupo grupo) {
        return manager.merge(grupo);
    }
    
    public List<Grupo> todos() {
        return manager.createQuery("from Grupo", Grupo.class).getResultList();
    }
    
    public Grupo porId(Long id) {
        return manager.find(Grupo.class, id);
    }

    @Transactional
    public void remover(Grupo grupo) {
        grupo = porId(grupo.getId());
        manager.remove(grupo);
    }
}
