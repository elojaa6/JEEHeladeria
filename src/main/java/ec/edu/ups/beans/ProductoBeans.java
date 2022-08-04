/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.entidades.Categorias;
import ec.edu.ups.entidades.Producto;
import ec.edu.ups.entidades.Sucursal;
import ec.edu.ups.facade.ProductoFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.faces.annotation.FacesConfig;


/**
 *
 * @author Elvis
 */
@FacesConfig(version = FacesConfig.Version.JSF_2_3)

@SessionScoped
@Model
public class ProductoBeans implements Serializable {

    @EJB
    private ProductoFacade producFacade;
    private Producto producto;
    private int totalP;
    private Long id;
    private Sucursal sucursal;
    private Categorias categorias;

    @PostConstruct
    public void init() {
        this.producto = new Producto();
        this.sucursal = new Sucursal();
        this.categorias = new Categorias();
    }

    public Producto getProducto() {
        return producto;
    }

    public int getTotalP() {
        return totalP;
    }

    public void setTotalP(int totalP) {
        this.totalP = totalP;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Produces
    @RequestScoped
    @Named("listadoProductos")
    public List<Producto> listarProductos() {
        List<Producto> prod = producFacade.listar();
        return prod;
    }

    public String guardar() {
        try {
            this.producFacade.guardar(producto);
            producto = new Producto();

        } catch (Exception e) {
        }
        return "productoCRUD.xhtml?faces-redirect=true";
    }

    public String eliminar(Long id) {
        producFacade.eliminar(id);
        return "productoCRUD.xhtml?faces-redirect=true";
    }

    public String editar(Long id) {
        this.id = id;

        if (id != null && id > 0) {
            producFacade.opcional(id).ifPresent(p -> {
                this.producto = p;
            });
        }
        return "formProducto.xhtml";
    }
}
