/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.controller;

import com.algaworks.model.Categoria;
import com.algaworks.model.Produto;
import com.algaworks.repository.Categorias;
import com.algaworks.repository.Produtos;
import com.algaworks.service.CadastroProdutoService;
import com.algaworks.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class CadastroProdutoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Produto produto;
    private Categoria categoriaPai;
    private List<Categoria> categoriasRaizes;
    private List<Categoria> subcategorias;
    
    @Inject
    private Produtos produtos;
    
    @Inject
    private Categorias categorias;
    
    @Inject
    private CadastroProdutoService cadastroProdutoService;

    public CadastroProdutoBean() {
        this.limpar();
    }
    
    public void inicializar() {
        System.out.println("Inicializando...");
        
        if (!FacesUtil.isPostBack()) {
            this.categoriasRaizes = categorias.raizes();
            
            if (this.categoriaPai != null)
                this.carregarSubcategorias();
        }
    }
    
    public void carregarSubcategorias() {
        subcategorias = categorias.subcategoriasDe(categoriaPai);
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        
        if (this.produto != null) {
            this.categoriaPai = produto.getCategoria().getCategoriaPai();
        }
    }

    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    public List<Categoria> getCategoriasRaizes() {
        return categoriasRaizes;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

    public void salvar() {
        produto = cadastroProdutoService.salvar(produto);
        this.limpar();
        FacesUtil.addMessage("Produto salvo com sucesso", FacesMessage.SEVERITY_INFO);
    }
    
    private void limpar() {
        produto = new Produto();
        categoriaPai = null;
        subcategorias = new ArrayList<>();
    }
    
    public boolean editando() {
        return this.produto.getId() != null;
    }
}
