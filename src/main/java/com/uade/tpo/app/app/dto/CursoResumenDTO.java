package com.uade.tpo.app.app.dto;

public class CursoResumenDTO {
    private String contenidos;
    private String descripcion;

    public CursoResumenDTO(String contenidos, String descripcion) {
        this.contenidos = contenidos;
        this.descripcion = descripcion; // corregido
    }

    public CursoResumenDTO() {
    }

    public String getContenidos() {
        return contenidos;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
