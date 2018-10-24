/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Crud.CrudDocumento;
import Model.Documento;
import Utilitarios.PostgreUuidConverter;
import Utilitarios.Util;
import java.util.List;
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

    private UUID id;
    private String codigo;
    private String descricao;
    PostgreUuidConverter converter = new PostgreUuidConverter();
    CrudDocumento crudDocumento = new CrudDocumento();
    /**
     * Creates a new instance of JsfDocumento
     */
    public JsfDocumento() {
    }

    public String getIdAux() {
        return idAux;
    }

    public void setIdAux(String idAux) {
        this.idAux = idAux;
    }
    private String idAux;
    
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
    
     public PostgreUuidConverter getConverter() {
        return converter;
    }

    public void setConverter(PostgreUuidConverter converter) {
        this.converter = converter;
    }

    public String inserir() {
        Documento documento = new Documento();
        this.setId(Util.geraId());
        documento.setId(converter.convertToEntityAttribute(id));
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

        return "/operacoes/documento/listarTodos.xhtml";
    }

    /**
     * Método que retorna uma lista de todos documentos registrado no banco de
     * dados
     *
     * @return
     */
    public List<Documento> listaTodos() {
        List<Documento> lst;
        lst = crudDocumento.getAll();
        return lst;
    }

    public void remove(Documento documento) {
        Exception e = new CrudDocumento().remove(documento);
        if (e == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String update(Documento documento) {
        this.id = documento.getId();
        this.idAux = documento.getId().toString();
        this.codigo = documento.getCodigo();
        this.descricao = documento.getDescricao();
        return "editar.xhtml";
    }

    public String merge() {
        Documento documento = null;
        List<Documento> lista;
        lista = new CrudDocumento().getAll();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().toString().equals(idAux)) {
                documento = new CrudDocumento().find(lista.get(i).getId());
                documento.setId(lista.get(i).getId());
                documento.setDescricao(descricao);
                documento.setCodigo(codigo);
                continue;
            }
        }
        /**/
        Exception e = new CrudDocumento().merge(documento);

        if (e == null) {
            this.setId(id);
            this.setCodigo("");
            this.setDescricao("");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro alterado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "/operacoes/documento/listarTodos.xhtml";
    }

}
