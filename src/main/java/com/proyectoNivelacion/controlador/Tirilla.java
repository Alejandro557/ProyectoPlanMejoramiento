/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.controlador;

import com.proyectoNivelacion.entity.ProductosPublicados;
import com.proyectoNivelacion.helperBean.Mensaje;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Alejandro
 */
@Named(value = "tirilla")
@SessionScoped
public class Tirilla implements Serializable {

    @Inject
    private Mensaje mensaje;
    private List<ProductosPublicados> productos = new ArrayList<>();

    /**
     * Creates a new instance of Tirilla
     */
    public Tirilla() {
    }

    public void anular() {
        productos = new ArrayList<>();
        mensaje.setMensaje("mensaje('Anulado!', 'Venta anulada!', 'success');");
    }
    
    public BigDecimal total() {
        BigDecimal total = new BigDecimal("0");
        double total2 = 0;
        for (ProductosPublicados producto : productos) {
            double valor = producto.getProductoId().getProductoValor().doubleValue();
            total2 += valor * producto.getCantidad();
        }
        total = new BigDecimal(total2);
        return total;
    }

    public void agregarProducto(ProductosPublicados producto) {
        productos.add(producto);
    }

    public void eliminarProducto(ProductosPublicados producto) {
        if (productos.remove(producto)) {
            mensaje.setMensaje("mensaje('Eliminado!', 'Producto eliminado!', 'success');");
        } else {
            mensaje.setMensaje("mensaje('No se encontro!', 'Producto no encontrado!', 'error');");
        }
    }

    /**
     * @return the productos
     */
    public List<ProductosPublicados> getProductos() {
        return productos;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(List<ProductosPublicados> productos) {
        this.productos = productos;
    }

}
