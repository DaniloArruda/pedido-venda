/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.controller;

import com.algaworks.model.Foto;
import java.io.Serializable;
import java.util.Arrays;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author dasilva
 */

@Named
@ViewScoped
public class TesteBean implements Serializable {
    private String texto;
    private UploadedFile file;
    
    private static final long serialVersionUID = 1L;
    
    @Inject 
    private EntityManager manager;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void handleFileUpload(FileUploadEvent event) throws Exception {
        UploadedFile arquivo = event.getFile();
        byte[] contents = arquivo.getContents();
        salvarFoto(contents);
        System.out.println("lala");
        System.out.println(Arrays.toString(contents));
        System.out.println(arquivo.getFileName());
        System.out.println("lele");
        arquivo.write("C:\\Users\\dasilva\\Desktop\\ll.txt");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "feito!", "Feito!"));
    }

    public void salvarFoto(byte[] contents) {
        EntityTransaction trx = manager.getTransaction();
        trx.begin();
        
        Foto foto = new Foto();
        foto.setFoto(contents);
        
        manager.persist(foto);
        
        trx.commit();
    }
    
    public void upload() {
        System.out.println(file.getFileName());
        System.out.println(Arrays.toString(file.getContents()));
    }
}
