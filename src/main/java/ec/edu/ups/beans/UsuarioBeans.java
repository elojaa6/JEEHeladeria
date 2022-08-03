/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.entidades.Sucursal;
import ec.edu.ups.entidades.Usuario;
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
public class UsuarioBeans implements Serializable {

    @EJB
    private UsuarioFacade uFacade;
    private Usuario usuario;
    private long id;


    @PostConstruct
    public void init() {
        this.usuario = new Usuario();
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Produces
    @RequestScoped
    @Named("listadoUsuarios")
    public List<Usuario> listadoUsuarios() {
        List<Usuario> prod = uFacade.listar();
        return prod;
    }

    public String guardar() {
        try {
            this.uFacade.guardar(usuario);
            usuario = new Usuario();
        } catch (Exception e) {
        }
        return "usuarioCRUD.xhtml?faces-redirect=true";
    }

    public String eliminar(Long id) {
        uFacade.eliminar(id);
        return "usuarioCRUD.xhtml?faces-redirect=true";
    }

    public String editar(Long id) {
        this.id = id;

        if (id != null && id > 0) {
            uFacade.opcional(id).ifPresent(p -> {
                this.usuario = p;
            });
        }
        return "formUsuario.xhtml";
    }
}
