/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.entity;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "productos_publicados")
@NamedQueries({
    @NamedQuery(name = "ProductosPublicados.findAll", query = "SELECT p FROM ProductosPublicados p")
    , @NamedQuery(name = "ProductosPublicados.findByProductoPubId", query = "SELECT p FROM ProductosPublicados p WHERE p.productoPubId = :productoPubId")
    , @NamedQuery(name = "ProductosPublicados.findByCantidad", query = "SELECT p FROM ProductosPublicados p WHERE p.cantidad = :cantidad")})
public class ProductosPublicados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "producto_pub_id")
    private Integer productoPubId;
    @Column(name = "cantidad")
    private Integer cantidad;
    @OneToMany(mappedBy = "productoId")
    private List<VentaProductos> ventaProductosList;
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    @ManyToOne
    private Productos productoId;

    public ProductosPublicados() {
    }

    public ProductosPublicados(Integer productoPubId) {
        this.productoPubId = productoPubId;
    }

    public Integer getProductoPubId() {
        return productoPubId;
    }

    public void setProductoPubId(Integer productoPubId) {
        this.productoPubId = productoPubId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<VentaProductos> getVentaProductosList() {
        return ventaProductosList;
    }

    public void setVentaProductosList(List<VentaProductos> ventaProductosList) {
        this.ventaProductosList = ventaProductosList;
    }

    public Productos getProductoId() {
        return productoId;
    }

    public void setProductoId(Productos productoId) {
        this.productoId = productoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoPubId != null ? productoPubId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosPublicados)) {
            return false;
        }
        ProductosPublicados other = (ProductosPublicados) object;
        if ((this.productoPubId == null && other.productoPubId != null) || (this.productoPubId != null && !this.productoPubId.equals(other.productoPubId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoNivelacion.entity.ProductosPublicados[ productoPubId=" + productoPubId + " ]";
    }
    
}
