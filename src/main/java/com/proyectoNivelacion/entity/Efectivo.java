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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "efectivo")
@NamedQueries({
    @NamedQuery(name = "Efectivo.findAll", query = "SELECT e FROM Efectivo e")
    , @NamedQuery(name = "Efectivo.findByEfectivoId", query = "SELECT e FROM Efectivo e WHERE e.efectivoId = :efectivoId")
    , @NamedQuery(name = "Efectivo.findByEfectivoDinero", query = "SELECT e FROM Efectivo e WHERE e.efectivoDinero = :efectivoDinero")
    , @NamedQuery(name = "Efectivo.findByEfectivoVueltas", query = "SELECT e FROM Efectivo e WHERE e.efectivoVueltas = :efectivoVueltas")
    , @NamedQuery(name = "Efectivo.findByPagoId", query = "SELECT e FROM Efectivo e WHERE e.pagoId = :pagoId")})
public class Efectivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "efectivo_id")
    private Integer efectivoId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "efectivo_dinero")
    private BigDecimal efectivoDinero;
    @Column(name = "efectivo_vueltas")
    private BigDecimal efectivoVueltas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pago_id")
    private int pagoId;
    @OneToMany(mappedBy = "efectivoId")
    private List<Pagos> pagosList;

    public Efectivo() {
    }

    public Efectivo(Integer efectivoId) {
        this.efectivoId = efectivoId;
    }

    public Efectivo(Integer efectivoId, int pagoId) {
        this.efectivoId = efectivoId;
        this.pagoId = pagoId;
    }

    public Integer getEfectivoId() {
        return efectivoId;
    }

    public void setEfectivoId(Integer efectivoId) {
        this.efectivoId = efectivoId;
    }

    public BigDecimal getEfectivoDinero() {
        return efectivoDinero;
    }

    public void setEfectivoDinero(BigDecimal efectivoDinero) {
        this.efectivoDinero = efectivoDinero;
    }

    public BigDecimal getEfectivoVueltas() {
        return efectivoVueltas;
    }

    public void setEfectivoVueltas(BigDecimal efectivoVueltas) {
        this.efectivoVueltas = efectivoVueltas;
    }

    public int getPagoId() {
        return pagoId;
    }

    public void setPagoId(int pagoId) {
        this.pagoId = pagoId;
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
        hash += (efectivoId != null ? efectivoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Efectivo)) {
            return false;
        }
        Efectivo other = (Efectivo) object;
        if ((this.efectivoId == null && other.efectivoId != null) || (this.efectivoId != null && !this.efectivoId.equals(other.efectivoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoNivelacion.entity.Efectivo[ efectivoId=" + efectivoId + " ]";
    }
    
}
