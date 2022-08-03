/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.entidades.Cliente;

import ec.edu.ups.facade.ClienteFacade;
import ec.edu.ups.facade.UsuarioFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Jonny,Daniel,Elvis,Edisson
 */
@Model
@SessionScoped
public class ClienteBeans implements Serializable {

   
    @EJB
    private ClienteFacade cFacade;
    private Cliente cliente;
    private long id;

    

    @PostConstruct
    public void init() {
        this.cliente = new Cliente();
   
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

    @Produces
    @RequestScoped
    @Named("listadoClientes")
    public List<Cliente> listadoClientes() {
        List<Cliente> prod = cFacade.listar();
        return prod;
    }

    public String guardar() {
        try {
            this.cFacade.guardar(cliente);
            cliente = new Cliente();
        } catch (Exception e) {
        }
        return "clienteCRUD.xhtml?faces-redirect=true";
    }

    public String eliminar(Long id) {
        cFacade.eliminar(id);
        return "clienteCRUD.xhtml?faces-redirect=true";
    }

    public String editar(Long id) {
        this.id = id;

        if (id != null && id > 0) {
            cFacade.opcional(id).ifPresent(p -> {
                this.cliente = p;
            });
        }
        return "formCliente.xhtml";
    }
}


