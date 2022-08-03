package ec.edu.ups.beans;

import ec.edu.ups.entidades.Categorias;
import ec.edu.ups.facade.CategoriasFacade;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Optional;

/**
 *
 * @author Jonny
 */
@RequestScoped
@Named("CategoriaConverter")
public class CategoriaConverter implements Converter<Categorias> {

    @Inject
    private CategoriasFacade efacade;

    @Override
    public Categorias getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {
            return null;
        }
        Optional<Categorias> caOptional = efacade.opcional(Long.valueOf(id));
        if (caOptional.isPresent()) {
            return caOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Categorias ep) {
        if (ep == null) {
            return "0";
        }
        return "" + ep.getId();
    }

}
