/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.facade;


import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.EntidadPrincipal;
import ec.edu.ups.entidades.Sucursal;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jonny,Daniel,Elvis,Edisson
 */
@Stateless
public class EntidadpFacade extends AbstractFacade<EntidadPrincipal> {

    @PersistenceContext(name = "TestJpa")
    private EntityManager em;

    public EntidadpFacade() {
        super(EntidadPrincipal.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public List<EntidadPrincipal> listar() {
        return em.createQuery("SELECT u FROM  Entidad_Principal u", EntidadPrincipal.class).getResultList();
    }
    
    public void guardar(EntidadPrincipal categoria) {
        
            em.merge(categoria);
        
    }
    
    public EntidadPrincipal porId(Long id) {
        return em.find(EntidadPrincipal.class, id);
    }
    public void eliminar(Long id) {
        EntidadPrincipal categoria = porId(id);
        em.remove(categoria);
    }
    
    public Optional<EntidadPrincipal> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }

    

}
