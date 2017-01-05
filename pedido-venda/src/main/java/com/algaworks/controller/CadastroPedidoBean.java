/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.controller;

import com.algaworks.model.Cliente;
import com.algaworks.model.EnderecoEntrega;
import com.algaworks.model.FormaPagamento;
import com.algaworks.model.ItemPedido;
import com.algaworks.model.Pedido;
import com.algaworks.model.Produto;
import com.algaworks.model.Usuario;
import com.algaworks.repository.Clientes;
import com.algaworks.repository.Produtos;
import com.algaworks.repository.Usuarios;
import com.algaworks.service.CadastroPedidoService;
import com.algaworks.service.NegocioException;
import com.algaworks.util.jsf.FacesUtil;
import com.algaworks.validation.SKU;
import java.io.Serializable;
import java.math.BigDecimal;
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
    private Produto produtoLinhaEditavel;
    private String sku;
    
    @Inject
    private Usuarios usuarios;
    
    @Inject
    private Clientes clientes;
    
    @Inject
    private Produtos produtos;
    
    @Inject
    private CadastroPedidoService cadastroPedidoService;

    public CadastroPedidoBean() {
        limpar();
    }
    
    public void inicializar() {
        if (!FacesUtil.isPostBack()) {
            vendedores = usuarios.todos();
            
            this.pedido.adicionarItemVazio();
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

    public Produto getProdutoLinhaEditavel() {
        return produtoLinhaEditavel;
    }

    public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
        this.produtoLinhaEditavel = produtoLinhaEditavel;
    }

    @SKU
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
    
    public void salvar() {
        pedido = this.cadastroPedidoService.salvar(pedido);
        
        FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
    }
    
    public boolean editando() {
        return this.pedido.getId() != null;
    }
    
    public void recalcularPedido() {
        BigDecimal total = this.pedido.getValorFrete().subtract(this.pedido.getValorDesconto());
        
        for (ItemPedido item : this.pedido.getItens()) {
            if (item.getProduto() != null && item.getProduto().getId() != null)
                total = total.add(item.getValorTotal());
        }
        
        this.pedido.setValorTotal(total);
    }
    
    private boolean produtoRepetido() {
        for (ItemPedido item : this.pedido.getItens()) {
            if (item.getProduto().equals(this.produtoLinhaEditavel))
                return true;
        }
        return false;
    }
    
    public void adicionarItem() {
        if (this.produtoRepetido())
            FacesUtil.addErrorMessage("Não pode repetir produtos dentro do pedido.");
        else {
            ItemPedido item = new ItemPedido();

            item.setPedido(pedido);
            item.setProduto(produtoLinhaEditavel);
            item.setValorUnitario(item.getProduto().getValorUnitario());

            this.pedido.getItens().add(item);
            this.recalcularPedido();
        }
        this.produtoLinhaEditavel = null;
        this.sku = null;
    }
    
    public void buscarProdutoPorSku() {
        Produto produto = this.produtos.porSku(sku);
        
        if (produto != null) {
            this.produtoLinhaEditavel = produto;
            this.adicionarItem();
        } else
            FacesUtil.addErrorMessage("Não há produtos com esse SKU.");
    }
    
    public List<Cliente> completarCliente(String nome) {
        return clientes.porNome(nome);
    }
    
    public List<Produto> completarProduto(String nome) {
        return produtos.porNome(nome);
    }
}
