package com.algaworks.service;

import com.algaworks.model.Produto;
import com.algaworks.util.jpa.Transactional;
import com.algaworks.repository.Produtos;
import java.io.Serializable;
import javax.inject.Inject;

public class CadastroProdutoService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Produtos produtos;
    
    @Transactional
    public Produto salvar(Produto produto) {
        Produto produtoExistente = produtos.porSku(produto.getSku());

        if (produtoExistente != null && !produtoExistente.equals(produto)) {
            throw new NegocioException("Já existe um produto com o SKU informado.");
        }

        return produtos.guardar(produto);
    }
}
