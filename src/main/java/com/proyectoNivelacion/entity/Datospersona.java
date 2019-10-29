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
@Table(name = "datospersona")
@NamedQueries({
    @NamedQuery(name = "Datospersona.findAll", query = "SELECT d FROM Datospersona d")
    , @NamedQuery(name = "Datospersona.findByPersonaId", query = "SELECT d FROM Datospersona d WHERE d.personaId = :personaId")
    , @NamedQuery(name = "Datospersona.findByPersonaNombre", query = "SELECT d FROM Datospersona d WHERE d.personaNombre = :personaNombre")
    , @NamedQuery(name = "Datospersona.findByPersonaApellido", query = "SELECT d FROM Datospersona d WHERE d.personaApellido = :personaApellido")
    , @NamedQuery(name = "Datospersona.findByPersonaDocumento", query = "SELECT d FROM Datospersona d WHERE d.personaDocumento = :personaDocumento")})
public class Datospersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "persona_id")
    private Integer personaId;
    @Size(max = 20)
    @Column(name = "persona_nombre")
    private String personaNombre;
    @Size(max = 20)
    @Column(name = "persona_apellido")
    private String personaApellido;
    @Size(max = 10)
    @Column(name = "persona_documento")
    private String personaDocumento;
    @OneToMany(mappedBy = "personaId")
    private List<Tarjetas> tarjetasList;
    @OneToMany(mappedBy = "personaId")
    private List<Ventas> ventasList;

    public Datospersona() {
    }

    public Datospersona(Integer personaId) {
        this.personaId = personaId;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public String getPersonaNombre() {
        return personaNombre;
    }

    public void setPersonaNombre(String personaNombre) {
        this.personaNombre = personaNombre;
    }

    public String getPersonaApellido() {
        return personaApellido;
    }

    public void setPersonaApellido(String personaApellido) {
        this.personaApellido = personaApellido;
    }

    public String getPersonaDocumento() {
        return personaDocumento;
    }

    public void setPersonaDocumento(String personaDocumento) {
        this.personaDocumento = personaDocumento;
    }

    public List<Tarjetas> getTarjetasList() {
        return tarjetasList;
    }

    public void setTarjetasList(List<Tarjetas> tarjetasList) {
        this.tarjetasList = tarjetasList;
    }

    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaId != null ? personaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datospersona)) {
            return false;
        }
        Datospersona other = (Datospersona) object;
        if ((this.personaId == null && other.personaId != null) || (this.personaId != null && !this.personaId.equals(other.personaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoNivelacion.entity.Datospersona[ personaId=" + personaId + " ]";
    }
    
}
