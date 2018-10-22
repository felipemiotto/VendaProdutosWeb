/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Crud.CrudCidade;
import Crud.CrudEstado;
import Model.Cidade;
import Model.Estado;
import Model.Pais;
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
@Named(value = "jsdCidade")
@ManagedBean
@Dependent
public class jsfCidade {
    private UUID id;
    private String nome;
    private Estado estado;
    private String estadoId;
    
    Crud.CrudCidade crudCidade = new CrudCidade();
    PostgreUuidConverter converter=new PostgreUuidConverter();

    public String persist() {
                        
        Model.Cidade cidade;
        cidade = new Cidade();
        
        this.setId(Util.geraId());
        
        cidade.setNome(nome);
        cidade.setId(id);
        cidade.setEstadoId(estadoId);
             
        Exception insert = new Crud.CrudProduto().persist(cidade);
        
        if(insert == null){
            this.setNome("");
            this.setEstadoId(null);
            
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
    
    public void remove(Cidade cidade) {
        Exception e =new CrudCidade().remove(cidade);
         if (e == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public List<Cidade> listaTodos(){
        List<Cidade> lst;
        lst = crudCidade.getAll();
        return lst;
    }
    
    public jsfCidade() {}

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
    }

    public PostgreUuidConverter getConverter() {
        return converter;
    }

    public void setConverter(PostgreUuidConverter converter) {
        this.converter = converter;
    }


    public CrudCidade getCrudCidade() {
        return crudCidade;
    }

    public void setCrudCidade(CrudCidade crudCidade) {
        this.crudCidade = crudCidade;
    }

    
}
