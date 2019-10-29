/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "venta_productos")
@NamedQueries({
    @NamedQuery(name = "VentaProductos.findAll", query = "SELECT v FROM VentaProductos v")
    , @NamedQuery(name = "VentaProductos.findByVentaProductosId", query = "SELECT v FROM VentaProductos v WHERE v.ventaProductosId = :ventaProductosId")})
public class VentaProductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "venta_productos_id")
    private Integer ventaProductosId;
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    @ManyToOne
    private Productos productoId;
    @JoinColumn(name = "venta_id", referencedColumnName = "venta_id")
    @ManyToOne
    private Ventas ventaId;

    public VentaProductos() {
    }

    public VentaProductos(Integer ventaProductosId) {
        this.ventaProductosId = ventaProductosId;
    }

    public Integer getVentaProductosId() {
        return ventaProductosId;
    }

    public void setVentaProductosId(Integer ventaProductosId) {
        this.ventaProductosId = ventaProductosId;
    }

    public Productos getProductoId() {
        return productoId;
    }

    public void setProductoId(Productos productoId) {
        this.productoId = productoId;
    }

    public Ventas getVentaId() {
        return ventaId;
    }

    public void setVentaId(Ventas ventaId) {
        this.ventaId = ventaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaProductosId != null ? ventaProductosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaProductos)) {
            return false;
        }
        VentaProductos other = (VentaProductos) object;
        if ((this.ventaProductosId == null && other.ventaProductosId != null) || (this.ventaProductosId != null && !this.ventaProductosId.equals(other.ventaProductosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoNivelacion.entity.VentaProductos[ ventaProductosId=" + ventaProductosId + " ]";
    }
    
}
