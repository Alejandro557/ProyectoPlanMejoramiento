/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.modelo;

import com.proyectoNivelacion.entity.Productos;
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
public class ProductosFacade extends AbstractFacade<Productos> {

    @PersistenceContext(unitName = "com.facturas_ProyectoNivelacion_war_1.0PU")
    private EntityManager em;
    private Query query;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosFacade() {
        super(Productos.class);
    }
    
    public List<Productos> findAllProductosForEstadoTrue() {
        try {
            query = em.createQuery("select p from Productos p where p.productoEstado = true");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error en el modelo.ProductosFacade.finAllProductosForEstadoTrue");
            e.printStackTrace();
        }
        return null;
    }
    
}
