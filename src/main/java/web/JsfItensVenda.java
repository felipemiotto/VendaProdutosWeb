/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Model.Produto;
import java.math.BigDecimal;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author luizf
 */
@Named(value = "jsfItensVenda")
@Dependent
public class JsfItensVenda {

    private UUID id;
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal desconto;
    private UUID vendaId;
    private Produto produto;

    private String idAuxProduto;

    public String getIdAuxProduto() {
        return idAuxProduto;
    }

    public void setIdAuxProduto(String idAuxProduto) {
        this.idAuxProduto = idAuxProduto;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public UUID getVendaId() {
        return vendaId;
    }

    public void setVendaId(UUID vendaId) {
        this.vendaId = vendaId;
    }

    public Produto getProdutoId() {
        return produto;
    }

    public void setProdutoId(Produto produto) {
        this.produto = produto;
    }
    /**
     * Creates a new instance of JsfItensVenda
     */
    public JsfItensVenda() {
    }
    
}
