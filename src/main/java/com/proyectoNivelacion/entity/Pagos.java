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
@Table(name = "pagos")
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p")
    , @NamedQuery(name = "Pagos.findByPagoId", query = "SELECT p FROM Pagos p WHERE p.pagoId = :pagoId")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pago_id")
    private Integer pagoId;
    @JoinColumn(name = "venta_id", referencedColumnName = "venta_id")
    @ManyToOne(optional = false)
    private Ventas ventaId;
    @JoinColumn(name = "tarjeta_id", referencedColumnName = "tarjeta_id")
    @ManyToOne
    private Tarjetas tarjetaId;
    @JoinColumn(name = "efectivo_id", referencedColumnName = "efectivo_id")
    @ManyToOne
    private Efectivo efectivoId;

    public Pagos() {
    }

    public Pagos(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public Integer getPagoId() {
        return pagoId;
    }

    public void setPagoId(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public Ventas getVentaId() {
        return ventaId;
    }

    public void setVentaId(Ventas ventaId) {
        this.ventaId = ventaId;
    }

    public Tarjetas getTarjetaId() {
        return tarjetaId;
    }

    public void setTarjetaId(Tarjetas tarjetaId) {
        this.tarjetaId = tarjetaId;
    }

    public Efectivo getEfectivoId() {
        return efectivoId;
    }

    public void setEfectivoId(Efectivo efectivoId) {
        this.efectivoId = efectivoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagoId != null ? pagoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.pagoId == null && other.pagoId != null) || (this.pagoId != null && !this.pagoId.equals(other.pagoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoNivelacion.entity.Pagos[ pagoId=" + pagoId + " ]";
    }
    
}
