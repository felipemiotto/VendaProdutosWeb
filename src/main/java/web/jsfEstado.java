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
import Model.Produto;
import Utilitarios.PostgreUuidConverter;
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
    private Pais pais;
    private String paisId;
    
    CrudEstado crudEstado = new CrudEstado();
    PostgreUuidConverter converter=new PostgreUuidConverter();
    
    public String persist() {
                        
        
        Model.Estado estado;
        estado = new Estado();
        
        this.setId(Util.geraId());        
        
        estado.setId(converter.convertToEntityAttribute(id));
        estado.setNome(nome);
        estado.setSigla(sigla);
        estado.setPaisId(paisId);
     
        Exception insert = new Crud.CrudProduto().persist(estado);
        if(insert == null){
            this.setNome("");
            this.setIdPais(null);
            this.setSigla("");
            
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro adicionado com sucesso");
           FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = insert.getMessage();
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no persist!", "Informe o administrador do erro: " + msg);
           FacesContext.getCurrentInstance().addMessage(null, message);
           return null;
        }
        
        return "";        
    }

    public void remove(Estado estado) {
        Exception e =new CrudEstado().remove(estado);
         if (e == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public List<Estado> listaTodos(){
        List<Estado> lst;
        lst = crudEstado.getAll();
        return lst;
    }
    
    public jsfEstado() {
    }
    
    public String getPaisId() {
        return paisId;
    }

    public void setPaisId(String paisId) {
        this.paisId = paisId;
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


    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getIdPais() {
        return paisId;
    }

    public void setIdPais(String paisId) {
        this.paisId = paisId;
    }


    
    
    
}
