/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.entidades.EntidadPrincipal;
import ec.edu.ups.entidades.Producto;
import ec.edu.ups.entidades.Sucursal;
import ec.edu.ups.entidades.Usuario;
import ec.edu.ups.facade.ClienteFacade;
import ec.edu.ups.facade.SucursalFacade;
import ec.edu.ups.facade.UsuarioFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
@SessionScoped
@Model
public class SucursalBeans implements Serializable {

    @EJB
    private SucursalFacade sFacade;
    private long id;
    private Sucursal sucursal;

    @Produces
    @Model

    public String titulo() {
        return "CRUD SUCURSAL";
    }

    @PostConstruct
    public void init() {
        this.sucursal = new Sucursal();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Produces
    @RequestScoped
    @Named("listadoSucursales")
    public List<Sucursal> listadoSucursales() {
        List<Sucursal> prod = sFacade.listar();
        return prod;
    }

    public String guardar() {
        try {
            this.sFacade.guardar(sucursal);
            sucursal = new Sucursal();
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Sucursal creada"));
        } catch (Exception e) {
        }
        return "sucursalCRUD.xhtml?faces-redirect=true";
    }

    public String eliminar(Long id) {
        sFacade.eliminar(id);
        return "sucursalCRUD.xhtml?faces-redirect=true";
    }

    public String editar(Long id) {
        this.id = id;

        if (id != null && id > 0) {
            sFacade.opcional(id).ifPresent(p -> {
                this.sucursal = p;
            });
        }
        return "formSucursal.xhtml";
    }
}
