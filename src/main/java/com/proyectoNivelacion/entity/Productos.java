/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "productos")
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p")
    , @NamedQuery(name = "Productos.findByProductoId", query = "SELECT p FROM Productos p WHERE p.productoId = :productoId")
    , @NamedQuery(name = "Productos.findByProductoNombre", query = "SELECT p FROM Productos p WHERE p.productoNombre = :productoNombre")
    , @NamedQuery(name = "Productos.findByProductoCantidad", query = "SELECT p FROM Productos p WHERE p.productoCantidad = :productoCantidad")
    , @NamedQuery(name = "Productos.findByProductoValor", query = "SELECT p FROM Productos p WHERE p.productoValor = :productoValor")
    , @NamedQuery(name = "Productos.findByProductoEstado", query = "SELECT p FROM Productos p WHERE p.productoEstado = :productoEstado")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "producto_id")
    private Integer productoId;
    @Size(max = 20)
    @Column(name = "producto_nombre")
    private String productoNombre;
    @Column(name = "producto_cantidad")
    private Integer productoCantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "producto_valor")
    private BigDecimal productoValor;
    @Column(name = "producto_estado")
    private Boolean productoEstado;
    @OneToMany(mappedBy = "productoId")
    private List<ProductosPublicados> productosPublicadosList;

    public Productos() {
    }

    public Productos(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public Integer getProductoCantidad() {
        return productoCantidad;
    }

    public void setProductoCantidad(Integer productoCantidad) {
        this.productoCantidad = productoCantidad;
    }

    public BigDecimal getProductoValor() {
        return productoValor;
    }

    public void setProductoValor(BigDecimal productoValor) {
        this.productoValor = productoValor;
    }

    public Boolean getProductoEstado() {
        return productoEstado;
    }

    public void setProductoEstado(Boolean productoEstado) {
        this.productoEstado = productoEstado;
    }

    public List<ProductosPublicados> getProductosPublicadosList() {
        return productosPublicadosList;
    }

    public void setProductosPublicadosList(List<ProductosPublicados> productosPublicadosList) {
        this.productosPublicadosList = productosPublicadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoId != null ? productoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.productoId == null && other.productoId != null) || (this.productoId != null && !this.productoId.equals(other.productoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoNivelacion.entity.Productos[ productoId=" + productoId + " ]";
    }
    
}
