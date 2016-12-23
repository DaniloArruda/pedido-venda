/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.controller;

import com.algaworks.model.Cliente;
import com.algaworks.model.EnderecoEntrega;
import com.algaworks.model.FormaPagamento;
import com.algaworks.model.Pedido;
import com.algaworks.model.Usuario;
import com.algaworks.repository.Clientes;
import com.algaworks.repository.Usuarios;
import com.algaworks.service.CadastroPedidoService;
import com.algaworks.service.NegocioException;
import com.algaworks.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author dasilva
 */

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {
    private Pedido pedido;
    private List<Usuario> vendedores;
    private FormaPagamento[] formasPagamento = FormaPagamento.values();
    
    @Inject
    private Usuarios usuarios;
    
    @Inject
    private Clientes clientes;
    
    @Inject
    private CadastroPedidoService cadastroPedidoService;

    public CadastroPedidoBean() {
        limpar();
    }
    
    public void inicializar() {
        if (!FacesUtil.isPostBack()) {
            vendedores = usuarios.todos();
        }
    }
    
    private void limpar() {
        this.pedido = new Pedido();
        this.pedido.setEnderecoEntrega(new EnderecoEntrega());
    }
    
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Usuario> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Usuario> vendedores) {
        this.vendedores = vendedores;
    }

    public FormaPagamento[] getFormasPagamento() {
        return formasPagamento;
    }
    
    public void salvar() {
        pedido = this.cadastroPedidoService.salvar(pedido);
        
        FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
    }
    
    public List<Cliente> completarCliente(String nome) {
        return clientes.porNome(nome);
    }
}
