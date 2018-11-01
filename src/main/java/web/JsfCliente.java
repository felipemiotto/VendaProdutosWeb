/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Crud.CrudCep;
import Model.Cliente;
import Model.EnderecoModel;
import java.util.List;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

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
    private UUID cepId;

    private String cep;
    private String nomePais;
    private String nomeEstado;
    private String nomeCidade;

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

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

    public UUID getCepId() {
        return cepId;
    }

    public void setCepId(UUID cepId) {
        this.cepId = cepId;
    }

    public String adicionar() {
        return "operacoes/cliente/listaTodos.xhtml";
    }

    public void update(Cliente cliente) {

    }

    public void remove(Cliente cliente) {

    }

    public void listaTodos() {

    }

    public void buscaNome(String nome) {

    }

    public List<EnderecoModel> buscaCep() {
        List<EnderecoModel> lst;
        CrudCep crudCep = new CrudCep();
        lst = crudCep.selectJoin(cep);
        return lst;
    }

}
