/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Model.Cliente;
import Model.Documento;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author luizf
 */
@Named(value = "jsfVenda")
@RequestScoped
public class JsfVenda {
    
    private UUID id;
    private String numero;
    private Date emissao;
    private BigDecimal desconto;
    private BigDecimal total;
    private Cliente clienteId;
    private Documento documentoId;

    /**
     * Creates a new instance of JsfVenda
     */
    public JsfVenda() {
    }
    
}
