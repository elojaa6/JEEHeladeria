/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.facade;

import ec.edu.ups.entidades.Detalle;
import ec.edu.ups.entidades.Factura;
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
public class FacturaFacade extends AbstractFacade<Factura> {
    @PersistenceContext(name="TestJpa")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(Factura.class);
    }
    
    public List<Factura> listar() {
        return em.createQuery("select p from Factura P", Factura.class).getResultList();
    }
    
    public void guardar(Factura factura) {
       
            em.merge(factura);
        
    }
    
    public Factura porId(Long id) {
        //return em.find(Producto.class, id);
        return em.createQuery("select p from Factura p  where p.id=:id", Factura.class)
                .setParameter("id", id)
                .getSingleResult();
    }
   
    public void eliminar(Long id) {
        Factura factura = porId(id);
        em.remove(factura);
    }
    
    public Optional<Factura> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
   
}
