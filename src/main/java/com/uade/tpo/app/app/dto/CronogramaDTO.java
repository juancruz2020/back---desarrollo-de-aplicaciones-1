package com.uade.tpo.app.app.dto;

import java.time.LocalDate;

public class CronogramaDTO {
    private String sede;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer vacantesDisponibles;

    public CronogramaDTO(String sede, LocalDate fechaInicio, LocalDate fechaFin, Integer vacantesDisponibles) {
        this.sede = sede;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vacantesDisponibles = vacantesDisponibles;
    }

    public String getSede() {
        return sede;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Integer getVacantesDisponibles() {
        return vacantesDisponibles;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setVacantesDisponibles(Integer vacantesDisponibles) {
        this.vacantesDisponibles = vacantesDisponibles;
    }
}
