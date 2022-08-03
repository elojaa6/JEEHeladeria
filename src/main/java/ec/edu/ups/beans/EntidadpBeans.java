/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;



import ec.edu.ups.entidades.EntidadPrincipal;
import ec.edu.ups.entidades.Sucursal;
import ec.edu.ups.entidades.Usuario;
import ec.edu.ups.facade.ClienteFacade;
import ec.edu.ups.facade.EntidadpFacade;
import ec.edu.ups.facade.SucursalFacade;
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
public class EntidadpBeans implements Serializable {

    @EJB
    private EntidadpFacade epFacade;

  private EntidadPrincipal entidadp;
    Long id;
   
    @PostConstruct
    public void init() {
        this.entidadp = new EntidadPrincipal();
    }

    public EntidadPrincipal getEntidadp() {
        return entidadp;
    }

    public void setEntidadp(EntidadPrincipal entidadp) {
        this.entidadp = entidadp;
    }

   

 
    
    @Produces
    @RequestScoped
    @Named("listadoEntidadPrincipal")
    public List<EntidadPrincipal> listadoEntidadPrincipal() {
        List<EntidadPrincipal> prod = epFacade.listar();
        return prod;
    }
    
    
    @Produces
    @Model
    public String tituloCategoria() {
        return "CRUD ENITDAD";
    }
    
    public String guardar(){
            this.epFacade.guardar(entidadp);
        return "EntidadpCRUD.xhtml?faces-redirect=true";
    }
    
    public String eliminar(Long id){
         epFacade.eliminar(id);
        return "EntidadpCRUD.xhtml?faces-redirect=true";
    }
    
    public String editar(Long id){
        this.id = id;
        
        if (id != null && id > 0) {
            epFacade.opcional(id).ifPresent(p -> {
                this.entidadp = p;
            });
        }
        return "formEntidadP.xhtml";
    }

   
}
