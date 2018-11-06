/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Crud.CrudCliente;
import Model.Cliente;
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
@Named(value = "jsfCliente")
@ManagedBean
@RequestScoped
public class JsfCliente {

    private UUID id;
    private String nome;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String fone;
    private String cpf;
    private String email;
    private String cepId;
    CrudCliente c = new CrudCliente();
    public static String idCliente;

    public String getIdAux() {
        return idAux;
    }

    public void setIdAux(String idAux) {
        this.idAux = idAux;
    }
    private String idAux;

    public JsfCliente() {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCepId() {
        return cepId;
    }

    public void setCepId(String cepId) {
        this.cepId = cepId;
    }

    public String adicionar() {
        Cliente cliente;
        cliente = new Cliente();
        this.setId(Util.geraId());
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setBairro(bairro);
        cliente.setComplemento(complemento);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);
        cliente.setFone(fone);
        cliente.setNumero(numero);
        cliente.setCepId(cepId);
        Exception insert = new Crud.CrudCliente().persist(cliente);
        if (insert == null) {
            this.setNome("");
            this.setBairro("");
            this.setCepId("");
            this.setComplemento("");
            this.setCpf("");
            this.setEmail("");
            this.setEndereco("");
            this.setFone("");
            this.setNumero("");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro adicionado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = insert.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no persist!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }

        return "operacoes/cliente/listarTodos.xhtml";
    }

    public String update(Cliente cliente) {
        this.id = cliente.getId();
        this.idAux = cliente.getId().toString();
        this.bairro = cliente.getBairro();
        this.complemento = cliente.getComplemento();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.endereco = cliente.getEndereco();
        this.fone = cliente.getFone();
        this.nome = cliente.getNome();
        this.numero = cliente.getNumero();
        this.cepId = cliente.getCepId().toString();
        idCliente = cliente.getId().toString();
        return "editar.xhtml";
    }

    public void remove(Cliente cliente) {
        Exception e = new CrudCliente().remove(cliente);
        if (e == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String merge() {
        Cliente cliente = null;
        List<Cliente> lista;
        lista = new CrudCliente().getAll();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().toString().equals(idCliente)) {
                cliente = new CrudCliente().find(lista.get(i).getId());
                cliente.setId(lista.get(i).getId());
                cliente.setNome(nome);
                cliente.setBairro(bairro);
                cliente.setComplemento(complemento);
                cliente.setCpf(cpf);
                cliente.setEmail(email);
                cliente.setEndereco(endereco);
                cliente.setFone(fone);
                cliente.setNumero(numero);
                cliente.setCepId(cepId);
                continue;
            }
        }
        /**/
        Exception e = new CrudCliente().merge(cliente);

        if (e == null) {
            this.setId(id);
            this.setBairro("");
            this.setCepId("");
            this.setComplemento("");
            this.setCpf("");
            this.setEmail("");
            this.setEndereco("");
            this.setFone("");
            this.setNome("");
            this.setNumero("");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", "Registro alterado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            String msg = e.getMessage();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe o administrador do erro: " + msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "/operacoes/cliente/listarTodos.xhtml";
    }

    public List<Cliente> listaTodos() {
        List<Cliente> lst;
        lst = c.getAll();
        return lst;
    }

    public void buscaNome(String nome) {

    }
}
