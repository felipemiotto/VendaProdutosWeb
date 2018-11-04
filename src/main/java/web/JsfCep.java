/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Crud.CrudCep;
import Crud.CrudDocumento;
import Model.Cep;
import Model.Cidade;
import Model.Documento;
import java.util.List;
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
@Named(value = "jsfCep")
@ManagedBean
@RequestScoped
public class JsfCep {
    
    private UUID id;
    private String cep;
    private Cidade idCidade;
    private String idCidadeAux;
    private String nomeCidade;
    private String idAux;
    
    CrudCep crudCep = new CrudCep();
    
    public JsfCep(){        
    }

    public String getIdAux() {
        return idAux;
    }

    public void setIdAux(String idAux) {
        this.idAux = idAux;
    }   

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
    
    

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Cidade idCidade) {
        this.idCidade = idCidade;
    }

    public String getIdCidadeAux() {
        return idCidadeAux;
    }

    public void setIdCidadeAux(String idCidadeAux) {
        this.idCidadeAux = idCidadeAux;
    }
    
    
    
    public String persist() {
        Model.Cep Cep;
        Cep = new Cep();
        Cep.setCep(cep);
        Cep.setCidadeId(idCidadeAux);
        Cep.setId(Utilitarios.Util.geraId());
        Exception insert = new Crud.CrudCep().persist(Cep);
        if(insert == null){
           this.setId(null);
           this.setCep("");
           this.setIdCidade(null);
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
        this.idAux = Cep.getId().toString();
        this.cep = Cep.getCep();
        this.idCidade = Cep.getCidadeId();
        return "editarCep.xhtml";
    }

    public String merge() {
          Cep cep = null;
        List<Cep> lista;
        lista = new CrudCep().getAll();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().toString().equals(idAux)) {
                cep = new CrudCep().find(lista.get(i).getId());
                cep.setId(lista.get(i).getId());
                cep.setCidadeId(idCidadeAux);
                cep.setCep(this.cep);
                continue;
            }
        }
        Exception e = new CrudCep().merge(cep);
        if (e == null) {
            this.setId(null);
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
    
    public List<Cep> listaTodos(){
        List<Cep> lst;
        lst = crudCep.getAll();
        return lst;
    }
}
