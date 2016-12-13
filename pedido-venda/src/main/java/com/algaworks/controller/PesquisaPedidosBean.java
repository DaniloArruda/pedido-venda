/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.controller;

import com.algaworks.model.Pedido;
import com.algaworks.model.StatusPedido;
import com.algaworks.repository.Pedidos;
import com.algaworks.repository.filter.PedidoFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author dasilva
 */

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {
    
    @Inject
    private Pedidos pedidos;
    
    private PedidoFilter filtro = new PedidoFilter();
    private List<Pedido> pedidosFiltrados = new ArrayList<>();
    private StatusPedido[] statuses = StatusPedido.values();

    public PedidoFilter getFiltro() {
        return filtro;
    }

    public List<Pedido> getPedidosFiltrados() {
        return pedidosFiltrados;
    }

    public StatusPedido[] getStatuses() {
        return statuses;
    }

    public void pesquisar() {
        this.pedidosFiltrados = this.pedidos.filtrados(filtro);
    }
}
