/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.modelo;

import com.proyectoNivelacion.entity.Ventas;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
@Stateless
public class VentasFacade extends AbstractFacade<Ventas> {

    Query query;
    

    @PersistenceContext(unitName = "com.facturas_ProyectoNivelacion_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentasFacade() {
        super(Ventas.class);
    }

    public List<Ventas> listaFechas(Date fechaIni, Date fechaFin) {

        List<Ventas> ventasList;
        query = em.createQuery("select f from Ventas f where f.ventaFecha between f.ventaFecha =:fechaInicio and f.ventaFecha =:fechaFinal");
        query.setParameter("fechaInicio", fechaIni);
        query.setParameter("fechaFinal", fechaFin);

        ventasList = query.getResultList();
        return ventasList;
    }
}
