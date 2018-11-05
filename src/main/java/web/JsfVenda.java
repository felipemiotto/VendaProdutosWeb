/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Crud.CrudVenda;
import Model.Cliente;
import Model.Documento;
import Model.ItensVenda;
import Model.Produto;
import Model.Venda;
import Utilitarios.Util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author luizf
 */
@Named(value = "jsfVenda")
@ManagedBean
@SessionScoped
public class JsfVenda {

    private UUID id;
    private String numero;
    private Date emissao;
    private BigDecimal desconto;
    private BigDecimal total;
    private Cliente clienteId;
    private Documento documentoId;

    private Collection<ItensVenda> litem;

    public Collection<ItensVenda> getLitem() {
        return litem;
    }

    public void setLitem(Collection<ItensVenda> litem) {
        this.litem = litem;
    }

    /**
     * Creates a new instance of JsfVenda
     */
    public JsfVenda() {
        litem = new ArrayList();
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

    public void addProduto(Produto prod) {
        boolean achou = false;
        for (ItensVenda itemvenda : litem) {
            if (Objects.equals(itemvenda.getProdutoId().getId(), prod.getId())) {
                //o produto ja esta no carrinho
                //itemvenda.setQuantidade(itemvenda.getQuantidade() + 1);
                itemvenda.setQuantidade(itemvenda.getQuantidade().add(BigDecimal.valueOf(1.00)));
                achou = true;
                break;
            }
        }
        if (!achou) { //o produto nao esta no carrinho
            ItensVenda ic = new ItensVenda();
            ic.setProdutoId(prod);
            ic.setQuantidade(BigDecimal.valueOf(1.00));
            litem.add(ic);
        }
    }
    
    public void removeProduto(Produto prod) {

        for (ItensVenda itemvenda : litem) {
            if (itemvenda.getProdutoId().getId()== prod.getId()) {
                if (itemvenda.getQuantidade().equals(1)) {
                    litem.remove(itemvenda);
                } else {
                    itemvenda.setQuantidade(itemvenda.getQuantidade().min(BigDecimal.valueOf(1.00)));
                }
                break;
            }
        }
    }
    
    public void concluirVenda() {
        Venda venda = new Venda();
        venda.setId(Util.geraId());
        venda.setEmissao(new Date());
        venda.setItensVendaCollection(litem);
        Exception insert = new CrudVenda().persist(venda);

        if (insert == null) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro adicionado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = insert.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        }

        litem = new ArrayList();
    }

}
