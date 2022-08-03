/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Jonny,Daniel,Elvis,Edisson
 */
@Entity(name = "Entidad_Principal")

public class EntidadPrincipal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String ruc;
    private String telefono;
    private String direccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidadPrincipal")
    private Set<Sucursal> sucursal = new HashSet<Sucursal>();
    
    
   

    public EntidadPrincipal() {
        super();
    }

    public EntidadPrincipal(long id, String nombre, String ruc, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.ruc = ruc;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
   
    

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<Sucursal> getSucursal() {
        return sucursal;
    }

    public void setSucursal(Set<Sucursal> sucursal) {
        this.sucursal = sucursal;
    }

    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadPrincipal entidadPrincipal = (EntidadPrincipal) o;
        return Objects.equals(id, entidadPrincipal.id);
    }
    @Override
    public String toString() {
        return "EntidadPrincipal{" + "id=" + id + ", nombre=" + nombre + ", ruc=" + ruc + ", telefono=" + telefono + ", direccion=" + direccion +'}';
    }
    
     
}
