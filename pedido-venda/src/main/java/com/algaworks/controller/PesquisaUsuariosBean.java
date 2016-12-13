/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.controller;

import com.algaworks.model.Usuario;
import com.algaworks.repository.Usuarios;
import com.algaworks.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
public class PesquisaUsuariosBean implements Serializable {
    private List<Usuario> usuariosFiltrados = new ArrayList<>();
    private String nome;

    @Inject
    private Usuarios usuarios;
    
    @PostConstruct
    private void init() {
        pesquisar();
    }
    
    public void pesquisar() {
        this.usuariosFiltrados = usuarios.filtrados(nome);
    }
    
    public void excluir(Usuario usuario) {
        this.usuarios.remover(usuario);
        this.usuariosFiltrados.remove(usuario);
        
        FacesUtil.addInfoMessage("Usuário " + usuario.getNome() + " excluído.");
    }
    
    public List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
