/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.facade;

import com.proyectoNivelacion.entity.Tarjetas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kevin
 */
@Stateless
public class TarjetasFacade extends AbstractFacade<Tarjetas> {

    @PersistenceContext(unitName = "com.facturas_ProyectoNivelacion_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetasFacade() {
        super(Tarjetas.class);
    }
    
}
