/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.facade;

import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Sucursal;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(name = "TestJpa")
    private EntityManager em;

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Usuario iniciarSesion(Usuario u) {
        Usuario usuario = null;
        String consulta;
        try {
            consulta = "SELECT u FROM Usuario u WHERE u.correo = ?1 and u.contrasenia= ?2";
            Query query;
            query = em.createQuery(consulta);
            query.setParameter(1, u.getCorreo());
            query.setParameter(2, u.getContrasenia());
            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }
    public List<Usuario> listar(){
       return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
              
    }
    public Optional<Usuario> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
    public Usuario porId(Long id) {
        return em.find(Usuario.class, id);
    }
    
    
    public void guardar(Usuario usuario) {
       
            em.merge(usuario);
        
    }
    
    public void eliminar(Long id) {
        Usuario usuario = porId(id);
        em.remove(usuario);
    }
}
