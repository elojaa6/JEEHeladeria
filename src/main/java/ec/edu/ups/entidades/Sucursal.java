/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity
@NamedQuery(name = "getSucursal", query = "SELECT s FROM  Sucursal s")
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String correo;
    private double latitud;
    private double longitud;
    @ManyToOne
    @JoinColumn(nullable = true, name = "entidad_principal_id")
    private EntidadPrincipal entidadPrincipal;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal")
    private Set<Producto> productos = new HashSet<Producto>();
    
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal")
    private Set<Categorias> categorias = new HashSet<Categorias>();

    public Sucursal() {
        super();
    }

    public Sucursal(Long id, String nombre, String telefono, String direccion, String ciudad, String correo, double latitud, double longitud, EntidadPrincipal entidadPrincipal) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.correo = correo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.entidadPrincipal = entidadPrincipal;
    }

  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    

    public EntidadPrincipal getEntidadPrincipal() {
        return entidadPrincipal;
    }

    public void setEntidadPrincipal(EntidadPrincipal entidadPrincipal) {
        this.entidadPrincipal = entidadPrincipal;
    }

    

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public Set<Categorias> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categorias> categorias) {
        this.categorias = categorias;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sucursal categoria = (Sucursal) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public String toString() {
        return "Sucursal{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + ", ciudad=" + ciudad + ", correo=" + correo + ", latitud=" + latitud + ", longitud=" + longitud + '}';
    }

  

}
