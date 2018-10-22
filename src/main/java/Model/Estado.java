/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Crud.CrudPais;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felip
 */
@Entity
@Table(name = "estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e")
    , @NamedQuery(name = "Estado.findByNome", query = "SELECT e FROM Estado e WHERE e.nome = :nome")
    , @NamedQuery(name = "Estado.findBySigla", query = "SELECT e FROM Estado e WHERE e.sigla = :sigla")})
public class Estado implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "sigla")
    private String sigla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoId")
    private Collection<Cidade> cidadeCollection;
    @JoinColumn(name = "pais_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pais paisId;

    public Estado() {
    }

    public Estado(UUID id) {
        this.id = id;
    }

    public Estado(UUID id, String nome, String sigla, Collection<Cidade> cidadeCollection, Pais paisId) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.cidadeCollection = cidadeCollection;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Pais getPaisId() {
        return paisId;
    }

    public void setPaisId(String paisId) {
        List<Model.Pais> listaPaises;
        listaPaises = new CrudPais().getAll();
        for(int i = 0; i < listaPaises.size(); i++){
            if(listaPaises.get(i).getId().toString().equals(paisId)){
                 this.paisId = listaPaises.get(i);
                continue;
            }
        }
    } 

    @XmlTransient
    public Collection<Cidade> getCidadeCollection() {
        return cidadeCollection;
    }

    public void setCidadeCollection(Collection<Cidade> cidadeCollection) {
        this.cidadeCollection = cidadeCollection;
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
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Estado[ id=" + id + " ]";
    }
    
}
