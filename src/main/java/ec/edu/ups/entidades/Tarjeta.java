/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Jonny,Daniel,Elvis,Edisson
 */
@NamedQuery(name = "getTarjeta", query = "SELECT t FROM  Tarjeta t")
@Entity
public class Tarjeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "nombre_Titular")
    private String nombreTitular;
    
    @Column(name = "num_Tarjeta")
    private int numTarjeta;
    
    @Column(name = "fecha_Cducidad")
    private LocalDate fechaCducidad;
    
    @Column(name = "codigo_Cvv")
    private int codigoCvv;
    
    @Column(name = "tipo")
    private String tipo;
    
    @ManyToOne
    @JoinColumn(nullable = true)
    private Cliente cliente;
}
