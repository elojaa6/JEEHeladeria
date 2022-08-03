package ec.edu.ups.beans;


import ec.edu.ups.entidades.Sucursal;
import ec.edu.ups.facade.SucursalFacade;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;

@RequestScoped
@Named("sucursalConverter")
public class SucursalConverter implements Converter<Sucursal> {

    @Inject
    private SucursalFacade sfacade;
            
    @Override
    public Sucursal getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {
            return null;
        }
        Optional<Sucursal> suOptional = sfacade.opcional(Long.valueOf(id));
        if (suOptional.isPresent()) {
            return suOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Sucursal su) {
        if (su == null) {
            return "0";
        }
        return su.getId().toString();
    }
}
