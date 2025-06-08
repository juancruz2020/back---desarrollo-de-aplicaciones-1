package com.uade.tpo.app.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pasos")
public class Paso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaso;

    private Integer nroPaso;
    private String texto;

    @ManyToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;

    public Paso(Long idPaso, Integer nroPaso, String texto, Receta receta) {
        this.idPaso = idPaso;
        this.nroPaso = nroPaso;
        this.texto = texto;
        this.receta = receta;
    }

    public Paso(Integer nroPaso, String texto, Receta receta) {
        this.nroPaso = nroPaso;
        this.texto = texto;
        this.receta = receta;
    }

    public Paso() {}

    public Long getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(Long idPaso) {
        this.idPaso = idPaso;
    }

    public Integer getNroPaso() {
        return nroPaso;
    }

    public void setNroPaso(Integer nroPaso) {
        this.nroPaso = nroPaso;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

}
