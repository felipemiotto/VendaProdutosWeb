/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import Crud.CrudCep;
import Crud.CrudProduto;
import Model.Cep;
import Model.Grupo;
import Model.Pais;
import Model.Produto;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alexj
 */
@Named(value = "jsfProduto")
@ManagedBean
@RequestScoped
public class JsfProduto {
    
    private UUID id;
    private String descricao;
    private String unidade;
    private BigDecimal peso;
    private BigDecimal preco;
    private BigDecimal custo;
    private Grupo idGrupo;   
    private String idGpProduto;
    private String idAux;
    
    CrudProduto crudProduto = new CrudProduto();
  
    public JsfProduto(){        
    }

    public String getIdAux() {
        return idAux;
    }

    public void setIdAux(String idAux) {
        this.idAux = idAux;
    }
    
    

    public String getIdGpProduto() {
        return idGpProduto;
    }

    public void setIdGpProduto(String idGpProduto) {
        this.idGpProduto = idGpProduto;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String persist() {
        Model.Produto produto;
        produto = new Produto();
        produto.setCusto(custo);
        produto.setDescricao(descricao);
        produto.setGrupoId(idGpProduto);
        produto.setId(Utilitarios.Util.geraId());
        produto.setPeso(peso);
        produto.setPreco(preco);
        produto.setUnidade(unidade);
        //produto.setId(id);
        
        Exception insert = new Crud.CrudProduto().persist(produto);
        if(insert == null){
           this.setId(null);
           this.setCusto(null);
           this.setDescricao("");
           this.setIdGrupo(null);
           this.setPeso(null);
           this.setPreco(null);
           this.setUnidade("");
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
    
     public java.util.List<Model.Produto> getAll() {
        return new CrudProduto().getAll();
    }

    public java.util.List<Model.Produto> getSelect() {
        if (this.descricao != null && !this.descricao.equals("")) {
            return new CrudProduto().SelectByDescricao(descricao);
        } else {
            return null;
        }
    }
    
    public void remove(Model.Produto produto) {
        Exception e =new CrudProduto().remove(produto);
         if (e == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public String update(Model.Produto produto) {
        this.id = produto.getId();
        this.idAux = produto.getId().toString();
        this.descricao = produto.getDescricao();
        this.unidade = produto.getUnidade();
        this.idGrupo = produto.getGrupoId();
        this.peso = produto.getPeso();
        this.preco = produto.getPreco();
        this.custo = produto.getCusto();        
        return "editarProduto.xhtml";
    }

    public String merge() {
        Produto produto = null;
        List<Produto> lista;
        lista = new CrudProduto().getAll();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().toString().equals(idAux)) {
                produto = new Produto();
                produto.setId(lista.get(i).getId());
                produto.setCusto(custo);
                produto.setDescricao(descricao);
                produto.setGrupoId(idGpProduto);
                produto.setPeso(peso);
                produto.setPreco(preco);
                produto.setUnidade(unidade);                
                continue;
            }
        }
        Exception e = new CrudProduto().merge(produto);
        if (e == null) {
            this.setId(null);
            this.setCusto(null);
            this.setDescricao("");
            this.setIdGrupo(null);
            this.setPeso(null);
            this.setPreco(null);
            this.setUnidade("");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro alterado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "";
    }
    
    public List<Produto> listaTodos(){
        List<Produto> lst;
        lst = crudProduto.getAll();
        return lst;
    }
}
