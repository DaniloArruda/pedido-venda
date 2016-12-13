/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.controller;

import com.algaworks.model.Grupo;
import com.algaworks.model.Usuario;
import com.algaworks.repository.Grupos;
import com.algaworks.service.CadastroUsuarioService;
import com.algaworks.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author dasilva
 */

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {
    private Usuario usuario = new Usuario();
    private Grupo grupo = new Grupo();
    private List<Grupo> listaGrupos;
    
    @Inject
    private Grupos grupos;
    
    @Inject
    private CadastroUsuarioService cadastroUsuarioService;

    @PostConstruct
    public void carregarGrupos() {
        this.listaGrupos = this.grupos.todos();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }
    
    public void salvar() {
        usuario = this.cadastroUsuarioService.salvar(usuario);
        limpar();
        
        FacesUtil.addMessage("Usuário salvo.", FacesMessage.SEVERITY_INFO);
    }
    
    private void limpar() {
        this.usuario = new Usuario();
    }
    
    public boolean isEditando() {
        return this.usuario.getId() != null;
    }
    
    public void adicionarGrupo() {
        usuario.getGrupos().add(grupo);
    }
    
    public void removerGrupo(Grupo grupo) {
        usuario.getGrupos().remove(grupo);
    }
}
