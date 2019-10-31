/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.controller;

import com.proyectoNivelacion.entity.Datospersona;
import com.proyectoNivelacion.facade.DatospersonaFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author kevin
 */
@Named(value = "controladorPersona")
@RequestScoped
public class ControladorPersona {

    /**
     * Creates a new instance of ControladorPersona
     */
    public ControladorPersona() {
    }
    @EJB
    DatospersonaFacade datosf;

    Datospersona dato;

    @PostConstruct
    public void init() {
        dato = new Datospersona();
    }

    public Datospersona getDato() {
        return dato;
    }

    public void setDato(Datospersona dato) {
        this.dato = dato;
    }

    public void registrar() {
        datosf.create(dato);
    }

    public List<Datospersona> consultar() {
        return datosf.findAll();
    }
}
