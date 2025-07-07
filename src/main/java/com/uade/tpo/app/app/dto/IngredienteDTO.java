package com.uade.tpo.app.app.dto;


public class IngredienteDTO {
    private String nombre;
    private double cantidad;
    private String unidad;
    private String observaciones;

    public IngredienteDTO(String nombre, double cantidad, String unidad, String observaciones) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.observaciones = observaciones;
    }

    public IngredienteDTO() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "IngredienteDTO{nombre='" + nombre + "', cantidad=" + cantidad +
                ", unidad='" + unidad + "', observaciones='" + observaciones + "'}";
    }
}
