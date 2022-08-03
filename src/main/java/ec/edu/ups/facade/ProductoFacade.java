/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.facade;

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
public class ProductoFacade extends AbstractFacade<Producto> {
    @PersistenceContext(name="TestJpa")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    public List<Producto> listar() {
        return em.createQuery("select p from Producto p left outer join fetch p.sucursal", Producto.class).getResultList();
    }
    
    public void guardar(Producto producto) {
       
            em.merge(producto);
        
    }
    
    public Producto porId(Long id) {
        //return em.find(Producto.class, id);
        return em.createQuery("select p from Producto p left outer join fetch p.sucursal where p.id=:id", Producto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    public void eliminar(Long id) {
        Producto producto = porId(id);
        em.remove(producto);
    }
    
    public Optional<Producto> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
    public Producto getProductoByName(String name) {
        String jpql = "SELECT s FROM Producto s WHERE s.nombre = '" + name + "'";
        Producto producto = (Producto) em.createQuery(jpql).getSingleResult();
        return producto;
    }
}
