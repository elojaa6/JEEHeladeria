/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.facade;

import ec.edu.ups.entidades.Detalle;
import ec.edu.ups.entidades.Pedido;
import ec.edu.ups.entidades.Producto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Gabus
 */
@Stateless 
public class DetalleFacade extends AbstractFacade<Detalle> {
    @PersistenceContext(name="TestJpa")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleFacade() {
        super(Detalle.class);
    }
    
    public List<Detalle> listar() {
        return em.createQuery("select p from Detalle P", Detalle.class).getResultList();
    }
    
    public void guardar(Pedido Pedido) {
       
            em.merge(Pedido);
        
    }
    
    public Detalle porId(Long id) {
        //return em.find(Producto.class, id);
        return em.createQuery("select p from Detalle p  where p.id=:id", Detalle.class)
                .setParameter("id", id)
                .getSingleResult();
    }
   
    public void eliminar(Long id) {
        Detalle detalle = porId(id);
        em.remove(detalle);
    }
    
    public Optional<Detalle> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
   
}
