/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.faces.annotation.FacesConfig;


/**
 *
 * @author Jonny
 */
@FacesConfig(version = FacesConfig.Version.JSF_2_3)

@Named
@RequestScoped
public class CerrarS implements Serializable {

    public void cerrarSesion() {
        try {
             FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }
}
