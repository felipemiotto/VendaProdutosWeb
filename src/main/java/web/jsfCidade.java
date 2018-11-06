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
    private String idAux;
    private String idAuxEstado;
    
    Crud.CrudCidade crudCidade = new CrudCidade();
    PostgreUuidConverter converter=new PostgreUuidConverter();

    
    public String merge() {
        Cidade cidade = null;
        List<Cidade> lista;
        lista = new CrudCidade().getAll();
        
        System.out.println(" ------------------------------- idAux do merge cidade antes do loop = " + idAux);

        for (int i = 0; i < lista.size(); i++) {                
        System.out.println(" ------------------------------- idAux do merge cidade no loop = " + idAux);

            if (lista.get(i).getId().toString().equals(idAux)) {  
                        System.out.println(" ------------------------------- idAux do merge no if que nao entra= " + idAux);
        System.out.println(" ------------------------------- mas entrou com o i = " + i);
        System.out.println(" ------------------------------- lista.get(i).getId() = " + lista.get(i).getId());
        System.out.println(" ------------------------------- idAuxEstado = " + idAuxEstado);
        System.out.println(" ------------------------------- nome = " + nome);

                cidade = new CrudCidade().find(lista.get(i).getId());
                cidade.setId(lista.get(i).getId());
                cidade.setNome(nome);
                cidade.setEstadoId(idAuxEstado);
            }
        }
                System.out.println(" ------------------------------- idAux do merge cidade depois do loop = " + idAux);

        Exception e = new CrudCidade().merge(cidade);
        if (e == null) {
            this.setId(null);
            this.setNome("");           
            this.setIdAuxEstado(idAuxEstado);
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro alterado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "/operacoes/cidade/listarTodos.xhtml";
    }

    public String update(Model.Cidade cidade) {
        this.idAux = cidade.getId().toString();
        this.nome = cidade.getNome();
        this.idAuxEstado = cidade.getEstadoId().getId().toString();
        return "editarCidade.xhtml";
    }
    
    public String persist() {
                        
        Model.Cidade cidade;
        cidade = new Cidade();
        
        this.setId(Util.geraId());
        
        cidade.setNome(nome);
        cidade.setId(id);
        cidade.setEstadoId(idAuxEstado);
             
        Exception insert = new Crud.CrudProduto().persist(cidade);
        
        if(insert == null){
            this.setNome("");
            this.setIdAuxEstado(null);
            
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

    public String getIdAux() {
        return idAux;
    }

    public void setIdAux(String idAux) {
        this.idAux = idAux;
    }

    public String getIdAuxEstado() {
        return idAuxEstado;
    }

    public void setIdAuxEstado(String idAuxEstado) {
        this.idAuxEstado = idAuxEstado;
    }

    
}
