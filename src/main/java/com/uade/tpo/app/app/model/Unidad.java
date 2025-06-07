package com.uade.tpo.app.app.model;
import jakarta.persistence.*;

@Entity
@Table(name = "unidades")
public class Unidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidad;

    private String descripcion;

    public Unidad(Long idUnidad, String descripcion) {
        this.idUnidad = idUnidad;
        this.descripcion = descripcion;
    }

    public Unidad() {}

    public Long getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Long idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
