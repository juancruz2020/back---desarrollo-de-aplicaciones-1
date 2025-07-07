package com.uade.tpo.app.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "utilizados")
public class Utilizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilizado;

    @ManyToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "idIngrediente")
    private Ingrediente ingrediente;

    private double cantidad;

    @ManyToOne
    @JoinColumn(name = "idUnidad")
    private Unidad unidad;

    private String observaciones;

    public Utilizado(Receta receta, Ingrediente ingrediente, double cantidad, Unidad unidad, String observaciones) {
        this.receta = receta;
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.observaciones = observaciones;
    }

    public Utilizado(Receta receta, Ingrediente ing, double cantidad, String unidad, String observaciones) {

    }

    public Utilizado() {

    }

    public Long getIdUtilizado() {
        return idUtilizado;
    }

    public void setIdUtilizado(Long idUtilizado) {
        this.idUtilizado = idUtilizado;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
