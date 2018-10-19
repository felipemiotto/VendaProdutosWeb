/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Crud.CrudPais;
import Model.Documento;
import Model.Pais;
import Utilitarios.Util;
import java.util.List;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author felip
 */
@Named(value = "jsfPais")
@ManagedBean
@RequestScoped
public class jsfPais {

    private UUID id;
    private String codigo;
    private String nome;
    CrudPais crudPais = new CrudPais();


    public String merge() {

        Pais p;
        p = new CrudPais().find(this.id);        
        p.setId(id);
        p.setNome(nome);
        p.setCodigo(codigo);
        
        Exception e = new CrudPais().merge(p);
  
        if (e == null) {
            this.setId(id);
            this.setCodigo("");
            this.setNome("");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro alterado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "/operacoes/index.xhtml";
    }   
    
    public String update(Pais pais) {
        this.id = pais.getId();
        this.codigo = pais.getCodigo();
        this.nome = pais.getNome();
        return "editar.xhtml";
    }
   
    
    public void remove(Pais pais) {
        Exception e =new CrudPais().remove(pais);
         if (e == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public String inserir() {
        Pais pais = new Pais();
        this.setId(Util.geraId());
        pais.setId(id);
        pais.setNome(nome);
        pais.setCodigo(codigo);

        Exception insert = new CrudPais().persist(pais);

        if (insert == null) {
            
            this.setCodigo("");
            this.setNome("");
              
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro adicionado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = insert.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            Util.log(msg);
            return null;
        }

        return "/operacoes/pais/adicionar.xhtml";
    }
    
    public List<Pais> listaTodos(){
        List<Pais> lst;
        lst = crudPais.getAll();
        return lst;
    }

    /**
     *
     * @return
     */
    public List<Pais> getAll() {
        return new CrudPais().getAll();
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CrudPais getCrudPais() {
        return crudPais;
    }

    public void setCrudPais(CrudPais crudPais) {
        this.crudPais = crudPais;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
        
    public jsfPais() {
    }
    
}
