package ec.edu.ups.beans;

import ec.edu.ups.entidades.EntidadPrincipal;

import ec.edu.ups.facade.EntidadpFacade;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;
@RequestScoped
@Named("EntidadpConverter")
public class EntidadpConverter implements Converter<EntidadPrincipal> {

    @Inject
    private EntidadpFacade efacade;

    @Override
    public EntidadPrincipal getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {
            return null;
        }
        Optional<EntidadPrincipal> suOptional = efacade.opcional(Long.valueOf(id));
        if (suOptional.isPresent()) {
            return suOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, EntidadPrincipal ep) {
        if (ep == null) {
            return "0";
        }
        return ""+ep.getId();
    }
}
