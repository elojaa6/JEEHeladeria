/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.facade;


import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Producto;
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
public class SucursalFacade extends AbstractFacade<Sucursal> {

    @PersistenceContext(name = "TestJpa")
    private EntityManager em;

    public SucursalFacade() {
        super(Sucursal.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
   
    public List<Sucursal> listar(){
       return em.createQuery("SELECT u FROM Sucursal u", Sucursal.class).getResultList();
              
    }
    public Optional<Sucursal> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
    public Sucursal porId(Long id) {
        return em.find(Sucursal.class, id);
    }
    
    
    public void guardar(Sucursal sucursal) {
       
            em.merge(sucursal);
        
    }
    
    public void eliminar(Long id) {
        Sucursal sucursal = porId(id);
        em.remove(sucursal);
    }
    
    
}
