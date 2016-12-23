/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.service;

import com.algaworks.model.Pedido;
import com.algaworks.repository.Pedidos;
import com.algaworks.util.jpa.Transactional;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author Danilo
 */
public class CadastroPedidoService implements Serializable {
    
    @Inject
    private Pedidos pedidos;
    
    @Transactional
    public Pedido salvar(Pedido pedido) {
        if (pedido.isNovo()) {
            pedido.setDataCriacao(new Date());
        }
        
        pedido = pedidos.guardar(pedido);
        return pedido;
    }
}
