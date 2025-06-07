package com.uade.tpo.app.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "conversiones")
public class Conversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConversion;

    @ManyToOne
    @JoinColumn(name = "idUnidadOrigen", nullable = false)
    private Unidad unidadOrigen;

    @ManyToOne
    @JoinColumn(name = "idUnidadDestino", nullable = false)
    private Unidad unidadDestino;

    @Column(nullable = false)
    private float factorConversiones;

    public Conversion(Long idConversion, Unidad unidadOrigen, Unidad unidadDestino, float factorConversiones) {
        this.idConversion = idConversion;
        this.unidadOrigen = unidadOrigen;
        this.unidadDestino = unidadDestino;
        this.factorConversiones = factorConversiones;
    }

    public Conversion() {

    }

    public Long getIdConversion() {
        return idConversion;
    }

    public void setIdConversion(Long idConversion) {
        this.idConversion = idConversion;
    }

    public Unidad getUnidadOrigen() {
        return unidadOrigen;
    }

    public void setUnidadOrigen(Unidad unidadOrigen) {
        this.unidadOrigen = unidadOrigen;
    }

    public Unidad getUnidadDestino() {
        return unidadDestino;
    }

    public void setUnidadDestino(Unidad unidadDestino) {
        this.unidadDestino = unidadDestino;
    }

    public float getFactorConversiones() {
        return factorConversiones;
    }

    public void setFactorConversiones(float factorConversiones) {
        this.factorConversiones = factorConversiones;
    }
}

