/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jonny,Daniel,Elvis,Edisson
 */
@Entity
@NamedQuery(name = "getPedido", query = "SELECT pe FROM  Pedido pe")
public class Pedido implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    @JoinColumn(name = "cliente_id",nullable = true)
    private Cliente cliente;
    private double latitud;
    private double longitud;
    private String estado;
    @Column(name = "costo_envio")
    private double costoEnvio;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Detalle> detalles;
    private double total;
    @OneToOne
    @JoinColumn(nullable = true)
    private Tarjeta tarjeta;
    
    
    

    public Pedido() {
    }

    public Pedido(long id, Cliente cliente, double latitud, double longitud, String estado, double costoEnvio, List<Detalle> detalles, double total, Tarjeta tarjeta) {
        this.id = id;
        this.cliente = cliente;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.costoEnvio = costoEnvio;
        this.detalles = detalles;
        this.total = total;
        this.tarjeta = tarjeta;
    }

  


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", cliente=" + cliente + ", latitud=" + latitud + ", longitud=" + longitud + ", estado=" + estado + ", costoEnvio=" + costoEnvio + ", detalles=" + detalles + ", total=" + total + ", tarjeta=" + tarjeta + '}';
    }
    

   

}
