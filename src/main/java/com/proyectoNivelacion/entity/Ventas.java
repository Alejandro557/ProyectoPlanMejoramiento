/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "ventas")
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v")
    , @NamedQuery(name = "Ventas.findByVentaId", query = "SELECT v FROM Ventas v WHERE v.ventaId = :ventaId")
    , @NamedQuery(name = "Ventas.findByVentaMetodo", query = "SELECT v FROM Ventas v WHERE v.ventaMetodo = :ventaMetodo")
    , @NamedQuery(name = "Ventas.findByVentaFecha", query = "SELECT v FROM Ventas v WHERE v.ventaFecha = :ventaFecha")
    , @NamedQuery(name = "Ventas.findByVentaTotal", query = "SELECT v FROM Ventas v WHERE v.ventaTotal = :ventaTotal")
    , @NamedQuery(name = "Ventas.findByVentaEstado", query = "SELECT v FROM Ventas v WHERE v.ventaEstado = :ventaEstado")
    , @NamedQuery(name = "Ventas.findByVentaCantidad", query = "SELECT v FROM Ventas v WHERE v.ventaCantidad = :ventaCantidad")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "venta_id")
    private Integer ventaId;
    @Size(max = 20)
    @Column(name = "venta_metodo")
    private String ventaMetodo;
    @Column(name = "venta_fecha")
    @Temporal(TemporalType.DATE)
    private Date ventaFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "venta_total")
    private BigDecimal ventaTotal;
    @Column(name = "venta_estado")
    private Boolean ventaEstado;
    @Column(name = "venta_cantidad")
    private Integer ventaCantidad;
    @Lob
    @Size(max = 65535)
    @Column(name = "venta_detalle")
    private String ventaDetalle;
    @OneToMany(mappedBy = "ventaId")
    private List<VentaProductos> ventaProductosList;
    @JoinColumn(name = "persona_id", referencedColumnName = "persona_id")
    @ManyToOne
    private Datospersona personaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventaId")
    private List<Pagos> pagosList;

    public Ventas() {
    }

    public Ventas(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public String getVentaMetodo() {
        return ventaMetodo;
    }

    public void setVentaMetodo(String ventaMetodo) {
        this.ventaMetodo = ventaMetodo;
    }

    public Date getVentaFecha() {
        return ventaFecha;
    }

    public void setVentaFecha(Date ventaFecha) {
        this.ventaFecha = ventaFecha;
    }

    public BigDecimal getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(BigDecimal ventaTotal) {
        this.ventaTotal = ventaTotal;
    }

    public Boolean getVentaEstado() {
        return ventaEstado;
    }

    public void setVentaEstado(Boolean ventaEstado) {
        this.ventaEstado = ventaEstado;
    }

    public Integer getVentaCantidad() {
        return ventaCantidad;
    }

    public void setVentaCantidad(Integer ventaCantidad) {
        this.ventaCantidad = ventaCantidad;
    }

    public String getVentaDetalle() {
        return ventaDetalle;
    }

    public void setVentaDetalle(String ventaDetalle) {
        this.ventaDetalle = ventaDetalle;
    }

    public List<VentaProductos> getVentaProductosList() {
        return ventaProductosList;
    }

    public void setVentaProductosList(List<VentaProductos> ventaProductosList) {
        this.ventaProductosList = ventaProductosList;
    }

    public Datospersona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Datospersona personaId) {
        this.personaId = personaId;
    }

    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaId != null ? ventaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.ventaId == null && other.ventaId != null) || (this.ventaId != null && !this.ventaId.equals(other.ventaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoNivelacion.entity.Ventas[ ventaId=" + ventaId + " ]";
    }
    
}
