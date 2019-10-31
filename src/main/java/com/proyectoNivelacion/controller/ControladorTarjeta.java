/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.controller;

import com.proyectoNivelacion.entity.Tarjetas;
import com.proyectoNivelacion.modelo.TarjetasFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author kevin
 */
@Named(value = "controladorTarjeta")
@RequestScoped
public class ControladorTarjeta {

    /**
     * Creates a new instance of ControladorTarjeta
     */
    public ControladorTarjeta() {
    }
    @EJB
    TarjetasFacade tarjetaf;
    
    Tarjetas tarjeta;
    
    @PostConstruct
    public void init (){
    tarjeta = new Tarjetas();
    }

    public Tarjetas getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjetas tarjeta) {
        this.tarjeta = tarjeta;
    }
    public void registrar(){
    tarjetaf.create(tarjeta);
    }
    public List<Tarjetas> consultar(){
    return tarjetaf.findAll();
    }
}
