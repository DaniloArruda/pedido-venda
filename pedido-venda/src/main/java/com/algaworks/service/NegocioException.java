/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.service;

/**
 *
 * @author dasilva
 */
public class NegocioException extends RuntimeException {

    public NegocioException(String message) {
        super(message);
    }
    
}
