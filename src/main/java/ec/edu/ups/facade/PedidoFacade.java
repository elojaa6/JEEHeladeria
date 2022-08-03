/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.facade;

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
public class PedidoFacade extends AbstractFacade<Pedido> {
    @PersistenceContext(name="TestJpa")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    
    public List<Pedido> listar() {
        return em.createQuery("select p from Pedido P", Pedido.class).getResultList();
    }
    
    public void guardar(Pedido Pedido) {
       
            em.merge(Pedido);
        
    }
    
    public Pedido porId(Long id) {
        //return em.find(Producto.class, id);
        return em.createQuery("select p from Pedido p  where p.id=:id", Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    public void eliminar(Long id) {
        Pedido pedido = porId(id);
        em.remove(pedido);
    }
    
    public Optional<Pedido> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
   
}
