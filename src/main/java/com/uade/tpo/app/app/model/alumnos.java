package com.uade.tpo.app.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "alumnos")
@Getter
@Setter
public class alumnos {

    @Id
    @Column(name = "idAlumno")
    private Integer idAlumno;

    @OneToOne
    @MapsId
    @JoinColumn(name = "idAlumno", referencedColumnName = "idUsuario")
    private usuarios usuario;

    @Column(name = "numeroTarjeta", length = 12)
    private String numeroTarjeta;

    @Column(name = "dniFrente", length = 300)
    private String dniFrente;

    @Column(name = "dniFondo", length = 300)
    private String dniFondo;

    @Column(name = "tramite", length = 12)
    private String tramite;

    @Column(name = "cuentaCorriente", precision = 12, scale = 2)
    private BigDecimal cuentaCorriente;

    public alumnos(Integer idAlumno, usuarios usuario, String numeroTarjeta, String dniFrente, String dniFondo, String tramite, BigDecimal cuentaCorriente) {
        this.idAlumno = idAlumno;
        this.usuario = usuario;
        this.numeroTarjeta = numeroTarjeta;
        this.dniFrente = dniFrente;
        this.dniFondo = dniFondo;
        this.tramite = tramite;
        this.cuentaCorriente = cuentaCorriente;
    }

    public alumnos() {

    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public usuarios getUsuario() {
        return usuario;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getDniFrente() {
        return dniFrente;
    }

    public String getDniFondo() {
        return dniFondo;
    }

    public String getTramite() {
        return tramite;
    }

    public BigDecimal getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(BigDecimal cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public void setDniFondo(String dniFondo) {
        this.dniFondo = dniFondo;
    }

    public void setDniFrente(String dniFrente) {
        this.dniFrente = dniFrente;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setUsuario(usuarios usuario) {
        this.usuario = usuario;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }
}

