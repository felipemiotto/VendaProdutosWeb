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
    private String clienteId;
    private String documentoId;

    private static String idAuxCliente;
    private static String idAuxDocumento;
    private static String idAuxProduto;

    public String getIdAuxProduto() {
        return idAuxProduto;
    }

    public void setIdAuxProduto(String idAuxProduto) {
        this.idAuxProduto = idAuxProduto;
    }

    public String getIdAuxCliente() {
        return idAuxCliente;
    }

    public void setIdAuxCliente(String idAuxCliente) {
        this.idAuxCliente = idAuxCliente;
    }

    public String getIdAuxDocumento() {
        return idAuxDocumento;
    }

    public void setIdAuxDocumento(String idAuxDocumento) {
        this.idAuxDocumento = idAuxDocumento;
    }

    public BigDecimal getDescontoUnitario() {
        return descontoUnitario;
    }

    public void setDescontoUnitario(BigDecimal descontoUnitario) {
        this.descontoUnitario = descontoUnitario;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    private BigDecimal descontoUnitario;
    private BigDecimal valorUnitario;

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }
    private BigDecimal quantidade;

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

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(String documentoId) {
        this.documentoId = documentoId;
    }

    public void addProduto(Produto produto) {
        System.out.println("Cliente: " + this.clienteId);
        System.out.println("Documento: " + this.idAuxDocumento);
        System.out.println("Produto: " + this.idAuxProduto);
        boolean achou = false;
        for (ItensVenda itemvenda : litem) {
            if (Objects.equals(itemvenda.getProdutoId().getId(), produto.getId())) {
//                //o produto ja esta no carrinho
//                //itemvenda.setQuantidade(itemvenda.getQuantidade() + 1);
                itemvenda.setQuantidade(itemvenda.getQuantidade().add(BigDecimal.valueOf(1.00)));
                achou = true;
                break;
            }
        }
        if (!achou) { //o produto nao esta no carrinho
            ItensVenda ic = new ItensVenda();
            ic.setId(Util.geraId());
            ic.setProdutoId(idAuxProduto);
            ic.setQuantidade(quantidade);
            ic.setDesconto(descontoUnitario);
            ic.setValorUnitario(valorUnitario);
            ic.setValorTotal(ic.getQuantidade().multiply(ic.getValorUnitario()));
            ic.setDesconto(descontoUnitario);
            this.setTotal(ic.getValorTotal());
            this.setDesconto(ic.getDesconto());
            litem.add(ic);
            this.setQuantidade(null);
            this.setDescontoUnitario(null);
            this.setValorUnitario(null);
        }
    }

    public void removeProduto(Produto prod) {

        for (ItensVenda itemvenda : litem) {
            if (itemvenda.getProdutoId().getId() == prod.getId()) {
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
        venda.setNumero(numero);
        venda.setClienteId(idAuxCliente);
        venda.setEmissao(emissao);
        venda.setDocumentoId(idAuxDocumento);
        venda.setDesconto(desconto);
        venda.setTotal(total);
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
     public java.util.Collection<Venda> listaTodos() {
        return new CrudVenda().getAll();
    }
}
