/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.facade;


import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Usuario;
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
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(name = "TestJpa")
    private EntityManager em;

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

     public List<Cliente> listar(){
       return em.createQuery("SELECT u FROM Cliente u", Cliente.class).getResultList();
              
    }
    public Optional<Cliente> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
    public Cliente porId(Long id) {
        return em.find(Cliente.class, id);
    }
    
    
    public void guardar(Cliente cliente) {
       
            em.merge(cliente);
        
    }
    
    public void eliminar(Long id) {
        Cliente cliente = porId(id);
        em.remove(cliente);
    }
    
    public Cliente getClienteCedula(String cedula) {
       
        return em.createQuery("select c from Cliente c  where c.cedula=:cedula", Cliente.class)
                .setParameter("cedula", cedula)
                .getSingleResult();
    }

   
    

}
