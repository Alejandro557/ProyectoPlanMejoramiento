/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.controller;

import com.proyectoNivelacion.entity.Productos;
import com.proyectoNivelacion.helperBean.Mensaje;
import com.proyectoNivelacion.modelo.ProductosFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author kevin
 */
@Named(value = "controladorProducto")
@RequestScoped
public class ControladorProducto {

    /**
     * Creates a new instance of ControladorProducto
     */
    public ControladorProducto() {
    }
    @EJB
    ProductosFacade productof;
    @Inject
    Mensaje mensaje;

    Productos producto;
    BigDecimal valor;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @PostConstruct
    public void init() {
        producto = new Productos();
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public void registrar() {
        producto.setProductoValor(valor);
        if (productof.producto(producto)) {
            if (valor.intValueExact() <= 0) {
                mensaje.setMensaje("mensaje('Error!', 'El precio del producto no puede ser negativo', 'error');");
            } else {
                productof.create(producto);
                mensaje.setMensaje("mensaje('Hecho!', 'Registro exitoso!', 'success');");
            }

        } else {
            mensaje.setMensaje("mensaje('Error!', 'Este producto ya se encuentra registrado', 'error');");
        }

    }

    public List<Productos> consultar() {
        return productof.findAll();
    }
}
