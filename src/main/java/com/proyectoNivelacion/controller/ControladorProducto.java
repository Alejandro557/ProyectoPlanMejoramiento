/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.controller;

import com.proyectoNivelacion.entity.Productos;
import com.proyectoNivelacion.facade.ProductosFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

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

    Productos producto;

    @PostConstruct
    public void init() {
        producto = new Productos();
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public void registrar() {
        productof.create(producto);
    }

    public List<Productos> consultar() {
        return productof.findAll();
    }
}
