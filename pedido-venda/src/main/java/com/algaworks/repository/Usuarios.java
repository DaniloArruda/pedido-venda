/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.repository;

import com.algaworks.model.Produto;
import com.algaworks.model.Usuario;
import com.algaworks.service.NegocioException;
import com.algaworks.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author dasilva
 */
public class Usuarios implements Serializable {

    @Inject
    private EntityManager manager;
    
    public Usuario salvar(Usuario usuario) {
        return manager.merge(usuario);
    }
    
    public List<Usuario> todos() {
        return manager.createQuery("from Usuario", Usuario.class).getResultList();
    }
    
    public Usuario porId(Long id) {
        return manager.find(Usuario.class, id);
    }
    
    public List<Usuario> filtrados(String nome) {
        Session session = manager.unwrap(Session.class);
        
        Criteria criteria = session.createCriteria(Usuario.class);
        
        if (StringUtils.isNotBlank(nome))
            criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
        
        return criteria.addOrder(Order.asc("nome")).list();
    }

    @Transactional
    public void remover(Usuario usuario) {
        usuario = porId(usuario.getId());
        manager.remove(usuario);
    }
    
}
