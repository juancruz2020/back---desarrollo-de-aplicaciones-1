package com.uade.tpo.app.app.dto;

import java.math.BigDecimal;
import java.util.List;

public class CursoConCronogramasDTO {
    private int idCurso;
    private String descripcion;
    private String contenidos;
    private String requerimientos;
    private int duracion;
    private BigDecimal precio;
    private String modalidad;
    private List<CronogramaDTO> cronogramas;

    public CursoConCronogramasDTO(int idCurso, String descripcion, String contenidos, String requerimientos, int duracion, BigDecimal precio, String modalidad, List<CronogramaDTO> cronogramas) {
        this.idCurso = idCurso;
        this.descripcion = descripcion;
        this.contenidos = contenidos;
        this.requerimientos = requerimientos;
        this.duracion = duracion;
        this.precio = precio;
        this.modalidad = modalidad;
        this.cronogramas = cronogramas;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getContenidos() {
        return contenidos;
    }

    public String getRequerimientos() {
        return requerimientos;
    }

    public int getDuracion() {
        return duracion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public String getModalidad() {
        return modalidad;
    }

    public List<CronogramaDTO> getCronogramas() {
        return cronogramas;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setContenidos(String contenidos) {
        this.contenidos = contenidos;
    }

    public void setRequerimientos(String requerimientos) {
        this.requerimientos = requerimientos;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public void setCronogramas(List<CronogramaDTO> cronogramas) {
        this.cronogramas = cronogramas;
    }
}