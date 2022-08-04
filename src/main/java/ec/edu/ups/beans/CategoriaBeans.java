/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.entidades.Categorias;
import ec.edu.ups.facade.CategoriasFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jonny
 */
@Model
@SessionScoped
public class CategoriaBeans implements Serializable {

    @EJB
    private CategoriasFacade cFacade;
    private long id;
    private Categorias categorias;

    public String titulo() {
        return "CRUD Categorias";
    }

    @PostConstruct
    public void init() {
        this.categorias = new Categorias();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    @Produces
    
    @RequestScoped
    @Named("listadoCategorias")
    public List<Categorias> listadoCategorias() {
        List<Categorias> prod = cFacade.listar();
        return prod;
    }

    public String guardar() {
        try {
            this.cFacade.guardar(categorias);
            categorias = new Categorias();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Categoria creada"));
        } catch (Exception e) {
        }
        return "categoriaCRUD.xhtml?faces-redirect=true";
    }

    public String eliminar(Long id) {
        cFacade.eliminar(id);
        return "categoriaCRUD.xhtml?faces-redirect=true";
    }

    public String editar(Long id) {
        this.id = id;

        if (id != null && id > 0) {
            cFacade.opcional(id).ifPresent(p -> {
                this.categorias = p;
            });
        }
        return "formCategorias.xhtml";
    }

}
