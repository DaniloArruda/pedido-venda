/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks;

import com.algaworks.model.Categoria;
import com.algaworks.model.Cliente;
import com.algaworks.model.EnderecoEntrega;
import com.algaworks.model.FormaPagamento;
import com.algaworks.model.ItemPedido;
import com.algaworks.model.Pedido;
import com.algaworks.model.Produto;
import com.algaworks.model.StatusPedido;
import com.algaworks.model.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author dasilva
 */
public class Teste {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoVendaPU");
        EntityManager manager = factory.createEntityManager();
        
        EntityTransaction trx = manager.getTransaction();
        
        trx.begin();
        
        Cliente cliente = manager.find(Cliente.class, 1L);
        Produto produto = manager.find(Produto.class, 1L);
        Usuario vendedor = manager.find(Usuario.class, 1L);

        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
        enderecoEntrega.setLogradouro("Rua dos Mercados");
        enderecoEntrega.setNumero("1000");
        enderecoEntrega.setCidade("Uberlândia");
        enderecoEntrega.setUf("MG");
        enderecoEntrega.setCep("38400-123");

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(new Date());
        pedido.setDataEntrega(new Date());
        pedido.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
        pedido.setObservacao("Aberto das 08 às 18h");
        pedido.setStatus(StatusPedido.ORCAMENTO);
        pedido.setValorDesconto(BigDecimal.ZERO);
        pedido.setValorFrete(BigDecimal.ZERO);
        pedido.setValorTotal(new BigDecimal(23.2));
        pedido.setVendedor(vendedor);
        pedido.setEnderecoEntrega(enderecoEntrega);

        ItemPedido item = new ItemPedido();
        item.setProduto(produto);
        item.setQuantidade(10);
        item.setValorUnitario(new BigDecimal(2.32));
        item.setPedido(pedido);

        pedido.getItens().add(item);

        manager.persist(pedido);
        
        trx.commit();
        
        manager.close();
        factory.close();
    }
}
