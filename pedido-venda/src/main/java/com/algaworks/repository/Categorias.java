/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.repository;

import com.algaworks.model.Categoria;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author dasilva
 */
public class Categorias implements Serializable {
    
    @Inject
    private EntityManager manager;
    
    public Object porId(Long id) {
        return manager.find(Categoria.class, id);
    }
    
    public List<Categoria> raizes() {
        return manager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();
    }

    public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
        return manager.createQuery("from Categoria where categoriaPai = :raiz", Categoria.class)
                .setParameter("raiz", categoriaPai)
                .getResultList();
    }
    
}
