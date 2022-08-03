/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.facade;

import ec.edu.ups.entidades.Categorias;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jonny
 */
@Stateless
public class CategoriasFacade extends AbstractFacade<Categorias>{
    @PersistenceContext(name = "TestJpa")
    private EntityManager em;

    public CategoriasFacade() {
        super(Categorias.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
   
    public List<Categorias> listar(){
       return em.createQuery("SELECT u FROM Categorias u", Categorias.class).getResultList();
              
    } 
    public Optional<Categorias> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
    public Categorias porId(Long id) {
        return em.find(Categorias.class, id);
    }
    
    
    public void guardar( Categorias categorias) {
       
            em.merge(categorias);
        
    }
    
    public void eliminar(Long id) {
        Categorias categorias = porId(id);
        em.remove(categorias);
    }
    
}
