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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "tarjetas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarjetas.findAll", query = "SELECT t FROM Tarjetas t")
    , @NamedQuery(name = "Tarjetas.findByTarjetaId", query = "SELECT t FROM Tarjetas t WHERE t.tarjetaId = :tarjetaId")
    , @NamedQuery(name = "Tarjetas.findByTarjetaNumero", query = "SELECT t FROM Tarjetas t WHERE t.tarjetaNumero = :tarjetaNumero")
    , @NamedQuery(name = "Tarjetas.findByTarjetaEstado", query = "SELECT t FROM Tarjetas t WHERE t.tarjetaEstado = :tarjetaEstado")
    , @NamedQuery(name = "Tarjetas.findByTarjetaDinero", query = "SELECT t FROM Tarjetas t WHERE t.tarjetaDinero = :tarjetaDinero")
    , @NamedQuery(name = "Tarjetas.findByTarjetaNombre", query = "SELECT t FROM Tarjetas t WHERE t.tarjetaNombre = :tarjetaNombre")})
public class Tarjetas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tarjeta_id")
    private Integer tarjetaId;
    @Column(name = "tarjeta_numero")
    private Integer tarjetaNumero;
    @Column(name = "tarjeta_estado")
    private Boolean tarjetaEstado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tarjeta_dinero")
    private BigDecimal tarjetaDinero;
    @Size(max = 20)
    @Column(name = "tarjeta_nombre")
    private String tarjetaNombre;
    @JoinColumn(name = "persona_id", referencedColumnName = "persona_id")
    @ManyToOne
    private Datospersona personaId;
    @OneToMany(mappedBy = "tarjetaId")
    private List<Pagos> pagosList;

    public Tarjetas() {
    }

    public Tarjetas(Integer tarjetaId) {
        this.tarjetaId = tarjetaId;
    }

    public Integer getTarjetaId() {
        return tarjetaId;
    }

    public void setTarjetaId(Integer tarjetaId) {
        this.tarjetaId = tarjetaId;
    }

    public Integer getTarjetaNumero() {
        return tarjetaNumero;
    }

    public void setTarjetaNumero(Integer tarjetaNumero) {
        this.tarjetaNumero = tarjetaNumero;
    }

    public Boolean getTarjetaEstado() {
        return tarjetaEstado;
    }

    public void setTarjetaEstado(Boolean tarjetaEstado) {
        this.tarjetaEstado = tarjetaEstado;
    }

    public BigDecimal getTarjetaDinero() {
        return tarjetaDinero;
    }

    public void setTarjetaDinero(BigDecimal tarjetaDinero) {
        this.tarjetaDinero = tarjetaDinero;
    }

    public String getTarjetaNombre() {
        return tarjetaNombre;
    }

    public void setTarjetaNombre(String tarjetaNombre) {
        this.tarjetaNombre = tarjetaNombre;
    }

    public Datospersona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Datospersona personaId) {
        this.personaId = personaId;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tarjetaId != null ? tarjetaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarjetas)) {
            return false;
        }
        Tarjetas other = (Tarjetas) object;
        if ((this.tarjetaId == null && other.tarjetaId != null) || (this.tarjetaId != null && !this.tarjetaId.equals(other.tarjetaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoNivelacion.entity.Tarjetas[ tarjetaId=" + tarjetaId + " ]";
    }
    
}
