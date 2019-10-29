/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.controlador;

import com.proyectoNivelacion.entity.Productos;
import com.proyectoNivelacion.entity.ProductosPublicados;
import com.proyectoNivelacion.entity.Tarjetas;
import com.proyectoNivelacion.helperBean.Mensaje;
import com.proyectoNivelacion.modelo.ProductosFacade;
import com.proyectoNivelacion.modelo.ProductosPublicadosFacade;
import com.proyectoNivelacion.modelo.TarjetasFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author Alejandro
 */
@Named(value = "ventasBean")
@SessionScoped
public class VentasBean implements Serializable {

    @EJB
    private ProductosFacade productosDAO;
    private int cantidad = 0;
    @Inject
    private Mensaje menaje;
    private Productos productosDTO = new Productos();
    @EJB
    private ProductosPublicadosFacade productosPublicadosDAO;
    private ProductosPublicados productosPublicadosDTO = new ProductosPublicados();
    @Inject
    private Tirilla tirilla;
    private BigDecimal total = new BigDecimal("0");
    @EJB
    private TarjetasFacade tarjetasDAO;
    private Tarjetas tarjetasDTO = new Tarjetas();
    private int id = 0;
    private boolean estado = false;

    /**
     * Creates a new instance of VentasBean
     */
    public VentasBean() {
    }

    public void opcionVenta(ValueChangeEvent e) {
        if (id > 0) {
            estado = true;
        }
        id = 0;
    }
    
    public List<SelectItem> itemsTarjeta() {
        List<SelectItem> items = new ArrayList<>();
        for (Tarjetas tarjetas : tarjetasDAO.findAllTarjetasForEstadoTrue()) {
            SelectItem item = new SelectItem(tarjetas.getTarjetaId(), tarjetas.getTarjetaNombre() + " No Documento: " + tarjetas.getPersonaId().getPersonaDocumento());
            items.add(item);
        }
        return items;
    }

    public void anularVenta() {
        tirilla.anular();
        total = new BigDecimal("0");
    }

    public void eliminarProducto(ProductosPublicados producto) {
        tirilla.eliminarProducto(producto);
        total = tirilla.total();
    }

    public void agregarProductoATirilla(ProductosPublicados producto) {
        tirilla.agregarProducto(producto);
        total = tirilla.total();
        menaje.setMensaje("mensaje('Agregado!', 'Producto agregado a la tirilla!', 'success');");
    }

    public void venderProducto(Productos producto) {
        if (cantidad > 0) {
            if (producto.getProductoCantidad() >= cantidad) {
                productosPublicadosDTO.setCantidad(cantidad);
                productosPublicadosDTO.setProductoId(producto);
                productosPublicadosDAO.create(productosPublicadosDTO);
                int total = producto.getProductoCantidad() - cantidad;
                producto.setProductoCantidad(total);
                total = 0;
                productosDAO.edit(producto);
                menaje.setMensaje("mensaje('Vendido!', 'Producto a la venta!', 'success');");
            } else {
                menaje.setMensaje("mensaje('Error!', 'No tienes existencias disponibles!', 'error');");
            }
        } else {
            menaje.setMensaje("mensaje('Error!', 'La cantidad tienen que ser mayor a 0!', 'error');");
        }
        cantidad = 0;
        productosPublicadosDTO = new ProductosPublicados();
    }

    public List<Productos> productosSinVender() {
        return productosDAO.findAllProductosForEstadoTrue();
    }

    public List<ProductosPublicados> productosVendidos() {
        return productosPublicadosDAO.findAll();
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the productosDTO
     */
    public Productos getProductosDTO() {
        return productosDTO;
    }

    /**
     * @param productosDTO the productosDTO to set
     */
    public void setProductosDTO(Productos productosDTO) {
        this.productosDTO = productosDTO;
    }

    /**
     * @return the productosPublicadosDTO
     */
    public ProductosPublicados getProductosPublicadosDTO() {
        return productosPublicadosDTO;
    }

    /**
     * @param productosPublicadosDTO the productosPublicadosDTO to set
     */
    public void setProductosPublicadosDTO(ProductosPublicados productosPublicadosDTO) {
        this.productosPublicadosDTO = productosPublicadosDTO;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the tarjetasDTO
     */
    public Tarjetas getTarjetasDTO() {
        return tarjetasDTO;
    }

    /**
     * @param tarjetasDTO the tarjetasDTO to set
     */
    public void setTarjetasDTO(Tarjetas tarjetasDTO) {
        this.tarjetasDTO = tarjetasDTO;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
