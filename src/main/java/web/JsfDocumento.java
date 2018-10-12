/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Crud.CrudDocumento;
import Model.Documento;
import Utilitarios.Util;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author luizf
 */
@Named(value = "jsfDocumento")
@ManagedBean
@RequestScoped
public class JsfDocumento {

    /**
     * Creates a new instance of JsfDocumento
     */
    public JsfDocumento() {
    }

    private UUID id;
    private String codigo;
    private String descricao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String inserir() {
        Documento documento = new Documento();
        this.setId(Util.geraId());
        documento.setId(id);
        documento.setCodigo(codigo);
        documento.setDescricao(descricao);

        Exception insert = new CrudDocumento().persist(documento);
        if (insert == null) {
            this.setCodigo(null);
            this.setDescricao("");
            this.setCodigo("");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro adicionado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = insert.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            Util.log(msg);
            return null;
        }

        return "/operacoes/index.xhtml";
    }
}
