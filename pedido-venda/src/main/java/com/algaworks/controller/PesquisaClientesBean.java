/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.controller;

import com.algaworks.model.Cliente;
import com.algaworks.model.TipoPessoa;
import com.algaworks.repository.Clientes;
import com.algaworks.repository.filter.ClienteFilter;
import com.algaworks.util.jsf.FacesUtil;
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
public class PesquisaClientesBean implements Serializable{
    private ClienteFilter filtro = new ClienteFilter();
    private List<Cliente> clientesFiltrados;
    
    @Inject
    private Clientes clientes;
    
    @PostConstruct
    public void pesquisar() {
        this.clientesFiltrados = clientes.filtrados(filtro);
    }
    
    public void excluir() {
        
    }

    public boolean isTipoFisica(Cliente cliente) {
        return cliente.getTipoPessoa() == TipoPessoa.FISICA;
    }
    
    public boolean isTipoJuridica(Cliente cliente) {
        return cliente.getTipoPessoa() == TipoPessoa.JURIDICA;
    }

    public ClienteFilter getFiltro() {
        return filtro;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }
    
    public void excluir(Cliente cliente) {
        this.clientes.remover(cliente);
        FacesUtil.addInfoMessage("Cliente " + cliente.getNome() + " excluído.");
    }
}
