/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.controller;

import com.proyectoNivelacion.entity.Ventas;
import com.proyectoNivelacion.modelo.VentasFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author kevin
 */
@Named(value = "reportesBean")
@SessionScoped
public class ReportesBean implements Serializable {

    /**
     * Creates a new instance of ReportesBean
     */
    public ReportesBean() {

    }

    private Date fechaInicial;
    private Date fechaFinal;

    @EJB
    VentasFacade ventasFacade;        
    Ventas ventas = new Ventas();
    

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public List<Ventas> validacionFechas() {
        return ventasFacade.listaFechas(fechaInicial, fechaFinal);

    }
    public String registrar(){
        validacionFechas();
        return "consultar-reporte-ventas";
    }

}
