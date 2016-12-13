/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.controller;

import com.algaworks.model.Cliente;
import com.algaworks.model.Endereco;
import com.algaworks.model.TipoPessoa;
import com.algaworks.repository.Clientes;
import com.algaworks.service.CadastroClienteService;
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
public class CadastroClienteBean implements Serializable {
    private Cliente cliente = new Cliente();
    private Endereco endereco = new Endereco();
    private TipoPessoa[] tiposPessoa = TipoPessoa.values();
    
    @Inject
    private CadastroClienteService cadastroClienteService;
    
    @Inject
    private Clientes clientes;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoPessoa[] getTiposPessoa() {
        return tiposPessoa;
    }
    
    public void salvar() {
        this.cliente = this.cadastroClienteService.salvar(cliente);
        this.limpar();
        
        FacesUtil.addInfoMessage("Cliente salvo.");
    }

    private void limpar() {
        this.cliente = new Cliente();
    }
    
    public void adicionarEndereco() {
        endereco.setCliente(cliente);
        this.cliente.getEnderecos().add(endereco);
        
        endereco = new Endereco();
    }
    
    public boolean editando() {
        return this.cliente.getId() != null;
    }
}
