/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Model.Cliente;
import Model.Documento;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author luizf
 */
@Named(value = "jsfVenda")
@RequestScoped
public class JsfVenda {
    
    private UUID id;
    private String numero;
    private Date emissao;
    private BigDecimal desconto;
    private BigDecimal total;
    private Cliente clienteId;
    private Documento documentoId;

    
    
    /**
     * Creates a new instance of JsfVenda
     */
    public JsfVenda() {
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Documento getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Documento documentoId) {
        this.documentoId = documentoId;
    }

}
