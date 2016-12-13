/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.controller;

import com.algaworks.model.EnderecoEntrega;
import com.algaworks.model.Pedido;
import com.algaworks.service.NegocioException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author dasilva
 */

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {
    private List<Integer> itens = new ArrayList<>();

    private Pedido pedido;
    
    @PostConstruct
    private void iniciarItens() {
        pedido = new Pedido();
        pedido.setEnderecoEntrega(new EnderecoEntrega());
        itens.add(1);
    }
    
    public List<Integer> getItens() {
        return itens;
    }
    
    public void salvar() {
        //throw new NegocioException("Não foi possível salvar o pedido");
    }
    
    public void salvar2() {
        throw new RuntimeException("lala");
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
