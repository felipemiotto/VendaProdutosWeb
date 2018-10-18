/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Crud.CrudGrupoProduto;
import Model.Grupo;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexj
 */
@Named(value = "jsfGrupoProduto")
@ManagedBean
@RequestScoped
public class jsfGrupoProduto {
    
    private UUID id;
    private String codigo;
    private String nome;
    
    CrudGrupoProduto crudeGrupo = new CrudGrupoProduto();
    
    public jsfGrupoProduto(){        
    }

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
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    

    public String persist() {
        Model.Grupo grupo;
        grupo = new Grupo();
        grupo.setCodigo(codigo);
        grupo.setNome(nome);
        grupo.setId(Utilitarios.Util.geraId());
        Exception insert = new Crud.CrudGrupoProduto().persist(grupo);
        if(insert == null){
           this.setId(null);
           this.setNome("");
           this.setCodigo("");
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro adicionado com sucesso");
           FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = insert.getMessage();
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
           FacesContext.getCurrentInstance().addMessage(null, message);
           return null;
        }
        
        return "";        
    }
    
     public java.util.List<Model.Grupo> getAll() {
        return new CrudGrupoProduto().getAll();
    }

    public java.util.List<Model.Grupo> getSelect() {
        if (this.nome != null && !this.nome.equals("")) {
            return new CrudGrupoProduto().SelectByNome(nome);
        } else {
            return null;
        }
    }
    
    public void remove(Model.Grupo grupo) {
        Exception e =new CrudGrupoProduto().remove(grupo);
         if (e == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public String update(Model.Grupo grupo) {
        this.id = grupo.getId();
        this.nome = grupo.getNome();
        this.codigo = grupo.getCodigo();
        return "";
    }

    public String merge() {
        Model.Grupo grupo;
        grupo = new CrudGrupoProduto().find(this.id);
        grupo.setCodigo(codigo);
        grupo.setNome(nome);
        Exception e = new CrudGrupoProduto().merge(grupo);
        if (e == null) {
            this.setId(null);
            this.setCodigo("");
            this.setNome("");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro alterado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "";
    }
}
