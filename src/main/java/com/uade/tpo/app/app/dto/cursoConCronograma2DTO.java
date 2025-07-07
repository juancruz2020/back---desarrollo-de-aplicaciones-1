package com.uade.tpo.app.app.dto;

import java.math.BigDecimal;

public class cursoConCronograma2DTO {
    private Integer idCurso;
    private String descripcion;
    private String contenidos;
    private String requerimientos;
    private Integer duracion;
    private BigDecimal precio;
    private String modalidad;
    private CronogramaDTO2 cronograma;

    public cursoConCronograma2DTO() {}

    public cursoConCronograma2DTO(Integer idCurso, String descripcion, String contenidos, String requerimientos,
                                  Integer duracion, BigDecimal precio, String modalidad, CronogramaDTO2 cronograma) {
        this.idCurso = idCurso;
        this.descripcion = descripcion;
        this.contenidos = contenidos;
        this.requerimientos = requerimientos;
        this.duracion = duracion;
        this.precio = precio;
        this.modalidad = modalidad;
        this.cronograma = cronograma;
    }

    public Integer getIdCurso() {
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

    public Integer getDuracion() {
        return duracion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public String getModalidad() {
        return modalidad;
    }

    public CronogramaDTO2 getCronograma() {
        return cronograma;
    }

    public void setIdCurso(Integer idCurso) {
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

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public void setCronograma(CronogramaDTO2 cronograma) {
        this.cronograma = cronograma;
    }
}
