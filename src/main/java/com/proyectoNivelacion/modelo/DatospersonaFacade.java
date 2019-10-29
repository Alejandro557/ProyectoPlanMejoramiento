/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.modelo;

import com.proyectoNivelacion.entity.Datospersona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alejandro
 */
@Stateless
public class DatospersonaFacade extends AbstractFacade<Datospersona> {

    @PersistenceContext(unitName = "com.facturas_ProyectoNivelacion_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatospersonaFacade() {
        super(Datospersona.class);
    }
    
}
