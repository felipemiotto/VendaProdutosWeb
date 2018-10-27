/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Model.Cliente;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author luizf
 */
@Named(value = "jsfCliente")
@Dependent
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
    
    public String adicionar(Cliente cliente){
        return "operacoes/cliente/listaTodos.xhtml";
    }
    
    public void update(Cliente cliente){
        
    }
    
    public void remove(Cliente cliente){
        
    }
    
    public void listaTodos(){
        
    }
    
    public void buscaNome(String nome){
        
    }

}
