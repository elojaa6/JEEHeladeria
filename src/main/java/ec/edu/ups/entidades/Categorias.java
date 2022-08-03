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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jonny
 */
@Entity
public class Categorias implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;

    public Categorias() {
        super();
    }

    public Categorias(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    @ManyToOne
    @JoinColumn(nullable = true)
    private Sucursal sucursal;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorias")
    private Set<Producto> productos = new HashSet<Producto>();

    public Categorias(long id, String nombre, Sucursal sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.sucursal = sucursal;
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

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categorias other = (Categorias) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Categorias{" + "id=" + id + ", nombre=" + nombre + ", sucursal=" + sucursal + '}';
    }
    
    

}
