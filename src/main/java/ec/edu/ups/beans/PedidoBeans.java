/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.entidades.Categorias;
import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Detalle;
import ec.edu.ups.entidades.Pedido;
import ec.edu.ups.entidades.Producto;
import ec.edu.ups.entidades.Sucursal;
import ec.edu.ups.facade.DetalleFacade;
import ec.edu.ups.facade.PedidoFacade;
import ec.edu.ups.facade.ProductoFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elvis
 */
@SessionScoped
@Model
public class PedidoBeans implements Serializable {

    @EJB
    private PedidoFacade pedidoFacade;
    @EJB
    private DetalleFacade detalleFacade;
     @EJB
    private ProductoFacade productoFacade ;
    
     List<Detalle> detalles = new ArrayList<>();
    private Long id;
    private Pedido pedido;
    
    private Cliente cliente;
    
    private int totalP;
   
    private Sucursal sucursal;
    private Categorias categorias;

    @PostConstruct
    public void init() {
        this.pedido= new Pedido();
        this.sucursal = new Sucursal();
        this.categorias = new Categorias();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoFacade getPedidoFacade() {
        return pedidoFacade;
    }

    public void setPedidoFacade(PedidoFacade pedidoFacade) {
        this.pedidoFacade = pedidoFacade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getTotalP() {
        return totalP;
    }

    public void setTotalP(int totalP) {
        this.totalP = totalP;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }


    @Produces
    @RequestScoped
    @Named("listadoPedidos")
    public List<Pedido> listadoPedidos() {
        List<Pedido> prod = pedidoFacade.listar();
        return prod;
    }
    

    public String guardar() {
        try {
            this.pedidoFacade.guardar(pedido);
            updateStock(pedido);
            pedido = new Pedido();

        } catch (Exception e) {
        }
        return "pedidoCRUD.xhtml?faces-redirect=true";
    }

    public String eliminar(Long id) {
        pedidoFacade.eliminar(id);
        return "pedidoCRUD.xhtml?faces-redirect=true";
    }

    public String editar(Long id) {
        this.id = id;

        if (id != null && id > 0) {
            pedidoFacade.opcional(id).ifPresent(p -> {
                this.pedido = p;
            });
        }
        return "formPedido.xhtml";
    }
     public String verDeatalles(Long id) {
         detalles.clear();
         System.out.println("entra"+id);
         List<Detalle> detalles1=detalleFacade.listar();
         for (int i = 0; i < detalles1.size(); i++) {
                Detalle d = detalles1.get(i);
                if (d.getPedido().getId() == id) {
                    detalles.add(d);
                 
             }
         }
         System.out.println(detalles);
        return "formDetalles.xhtml?faces-redirect=true";
    }
    
    public void updateStock(Pedido pedido) {
        Pedido pedido1 = pedidoFacade.porId(pedido.getId());
        if (pedido.getEstado().equals("Aceptado")) {

            System.out.println("el pedido ya esta en estado Aceptado");
        } else if (!pedido.getEstado().equals("Aceptado")) {
            
            List<Detalle> detalles = detalleFacade.findAll();
            for (int i = 0; i < detalles.size(); i++) {
                Detalle d = detalles.get(i);
                if (d.getPedido().getId() == pedido.getId()) {

                    Producto p = productoFacade.porId(d.getProducto().getId());
                    p.setStock(p.getStock() - detalles.get(i).getCantidad());
                    productoFacade.edit(p);
                }

            }
        }
    }
}
