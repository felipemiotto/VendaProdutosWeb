/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Crud.CrudEstado;
import Crud.CrudPais;
import Model.Estado;
import Model.Pais;
import Utilitarios.Util;
import java.util.List;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author felip
 */
@Named(value = "jsfEstado")
@ManagedBean
@Dependent
public class jsfEstado {

    private UUID id;
    private String nome;
    private String sigla;
    private Pais paisId;
    
    CrudEstado crudEstado = new CrudEstado();

    
    public String inserir() {
        Estado estado = new Estado();
        this.setId(Util.geraId());
        estado.setId(id);
        estado.setNome(nome);
        estado.setSigla(sigla);
        estado.setPaisId(paisId);

        Exception insert = new CrudEstado().persist(estado);

        if (insert == null) {
            
            this.setNome("");
            this.setPaisId(null);
            this.setSigla("");
            
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
    
    public List<Estado> listaTodos(){
        List<Estado> lst;
        lst = crudEstado.getAll();
        return lst;
    }
    public jsfEstado() {
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

    public CrudEstado getCrudEstado() {
        return crudEstado;
    }

    public void setCrudEstado(CrudEstado crudEstado) {
        this.crudEstado = crudEstado;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Pais getPaisId() {
        return paisId;
    }

    public void setPaisId(Pais paisId) {
        this.paisId = paisId;
    }
    
    
    
}
