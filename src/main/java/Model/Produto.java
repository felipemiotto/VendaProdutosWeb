/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Crud.CrudGrupoProduto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex
 */

  /**
   * Classe De Produtos
   * 
   */
@Entity
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
    , @NamedQuery(name = "Produto.findByDescricao", query = "SELECT p FROM Produto p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "Produto.findByUnidade", query = "SELECT p FROM Produto p WHERE p.unidade = :unidade")
    , @NamedQuery(name = "Produto.findByPeso", query = "SELECT p FROM Produto p WHERE p.peso = :peso")
    , @NamedQuery(name = "Produto.findByPreco", query = "SELECT p FROM Produto p WHERE p.preco = :preco")
    , @NamedQuery(name = "Produto.findByCusto", query = "SELECT p FROM Produto p WHERE p.custo = :custo")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "id")
    private UUID id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "unidade")
    private String unidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso")
    private BigDecimal peso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preco")
    private BigDecimal preco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "custo")
    private BigDecimal custo;
    @JoinColumn(name = "grupo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grupo grupoId;

    public Produto() {
    }

    public Produto(UUID id) {
        this.id = id;
    }

    public Produto(UUID id, String descricao, String unidade, BigDecimal peso, BigDecimal preco, BigDecimal custo) {
        this.id = id;
        this.descricao = descricao;
        this.unidade = unidade;
        this.peso = peso;
        this.preco = preco;
        this.custo = custo;
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

    public Grupo getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(String grupoId) {
        List<Model.Grupo> g1;
        g1 = new CrudGrupoProduto().getAll();
        for(int i = 0; i < g1.size(); i++){
            if(g1.get(i).getId().toString().equals(grupoId)){
                 this.grupoId = g1.get(i);
                continue;
            }
        }
       
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Produto[ id=" + id + " ]";
    }
    
}
