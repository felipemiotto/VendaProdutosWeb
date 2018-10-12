/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import Crud.CrudProduto;
import Model.Cep;
import Model.Grupo;
import Model.Produto;
import java.math.BigDecimal;
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
public class JsfProduto {
    
    private Object id;
    private String descricao;
    private String unidade;
    private BigDecimal peso;
    private BigDecimal preco;
    private BigDecimal custo;
    private Grupo idGrupo;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_vendaprodutosweb_war_1.0-SNAPSHOTPU");
    
    
  
    public JsfProduto(){        
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public String persist() {
        Model.Produto produto;
        produto = new Produto();
        produto.setCusto(custo);
        produto.setDescricao(descricao);
        produto.setGrupoId(idGrupo);
        produto.setId(Utilitarios.Util.geraId());
        produto.setPeso(peso);
        produto.setPreco(preco);
        produto.setUnidade(unidade);
        produto.setId(id);
        
        Exception insert = new Crud.CrudProduto().persist(produto);
        if(insert == null){
           this.setId(0);
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
        this.descricao = produto.getDescricao();
        this.unidade = produto.getUnidade();
        this.idGrupo = produto.getGrupoId();
        this.peso = produto.getPeso();
        this.preco = produto.getPreco();
        this.custo = produto.getCusto();        
        return "";
    }

    public String merge() {
        Model.Produto produto;
        produto = (Produto) new CrudProduto().find(this.id);
        produto.setDescricao(descricao);
        Exception e = new CrudProduto().merge(produto);
        if (e == null) {
            this.setId(0);
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
}
