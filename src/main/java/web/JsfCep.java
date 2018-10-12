/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Crud.CrudCep;
import Model.Cep;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alexj
 */
@ManagedBean
@RequestScoped
public class JsfCep {
    
    private String id;
    private String cep;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_vendaprodutosweb_war_1.0-SNAPSHOTPU");
    
    public JsfCep(){        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String persist() {
        Model.Cep Cep;
        Cep = new Cep();
        Cep.setCep(cep);
        Cep.setId(Utilitarios.Util.geraId());
        Exception insert = new Crud.CrudCep().persist(Cep);
        if(insert == null){
           this.setId("");
           this.setCep("");
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
    
     public java.util.List<Model.Cep> getAll() {
        return new CrudCep().getAll();
    }

    public java.util.List<Model.Cep> getSelect() {
        if (this.cep != null && !this.cep.equals("")) {
            return new CrudCep().SelectByNome(cep);
        } else {
            return null;
        }
    }
    
    public void remove(Model.Cep Cep) {
        Exception e =new CrudCep().remove(Cep);
         if (e == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public String update(Model.Cep Cep) {
        this.id = Cep.getId();
        this.cep = Cep.getCep();
        return "";
    }

    public String merge() {
        Model.Cep Cep;
        Cep = new CrudCep().find(this.id);
        Cep.setCep(cep);
        Exception e = new CrudCep().merge(Cep);
        if (e == null) {
            this.setId("");
            this.setCep("");
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
