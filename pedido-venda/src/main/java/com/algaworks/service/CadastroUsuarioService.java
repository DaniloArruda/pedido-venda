/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.service;

import com.algaworks.controller.PesquisaUsuariosBean;
import com.algaworks.model.Usuario;
import com.algaworks.repository.Usuarios;
import com.algaworks.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author dasilva
 */
public class CadastroUsuarioService implements Serializable {

    @Inject
    private Usuarios usuarios;
    
    @Transactional
    public Usuario salvar(Usuario usuario) {
        return this.usuarios.salvar(usuario);
    }
    
}
