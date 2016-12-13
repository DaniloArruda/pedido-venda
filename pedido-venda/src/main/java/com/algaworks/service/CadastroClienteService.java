/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.service;

import com.algaworks.model.Cliente;
import com.algaworks.model.Produto;
import com.algaworks.repository.Clientes;
import com.algaworks.repository.Produtos;
import com.algaworks.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author dasilva
 */
public class CadastroClienteService implements Serializable {
    @Inject
    private Clientes clientes;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return clientes.guardar(cliente);
    }
}
