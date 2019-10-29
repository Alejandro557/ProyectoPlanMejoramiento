/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.modelo;

import com.proyectoNivelacion.entity.Tarjetas;
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
public class TarjetasFacade extends AbstractFacade<Tarjetas> {

    @PersistenceContext(unitName = "com.facturas_ProyectoNivelacion_war_1.0PU")
    private EntityManager em;
    private Query query;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetasFacade() {
        super(Tarjetas.class);
    }

    public List<Tarjetas> findAllTarjetasForEstadoTrue() {
        try {
            query = em.createQuery("select t from Tarjetas t where t.tarjetaEstado = true");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error en el modelo.tarjetasFacade.findAllTarjetasForEstadoTrue");
            e.printStackTrace();
        }
        return null;
    }

}
