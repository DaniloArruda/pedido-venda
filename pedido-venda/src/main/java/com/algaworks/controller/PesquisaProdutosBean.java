/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.controller;

import com.algaworks.model.Produto;
import com.algaworks.repository.Produtos;
import com.algaworks.repository.filter.ProdutoFilter;
import com.algaworks.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author dasilva
 */

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {
    private List<Produto> produtosFiltrados;
    private ProdutoFilter filtro;
    
    @Inject
    private Produtos produtos;

    public PesquisaProdutosBean() {
        this.filtro = new ProdutoFilter();
    }
    
    @PostConstruct
    public void pesquisar() {
        this.produtosFiltrados = produtos.filtrados(filtro);
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public ProdutoFilter getFiltro() {
        return filtro;
    }
    
    public void excluir(Produto produto) {
        this.produtos.remover(produto);
        this.produtosFiltrados.remove(produto);
        
        FacesUtil.addMessage("Produto " + produto.getSku() + " excluído com sucesso.", FacesMessage.SEVERITY_INFO);
    }
}
