package com.uade.tpo.app.app.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cronogramaCursos")
@Data
@AllArgsConstructor
public class cronogramaCursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCronograma;

    @ManyToOne
    @JoinColumn(name = "idSede", nullable = false)
    private sedes sede;

    @ManyToOne
    @JoinColumn(name = "idCurso", nullable = false)
    private cursos curso;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private Integer vacantesDisponibles;

    public cronogramaCursos(sedes sede, cursos curso, LocalDate fechaInicio, LocalDate fechaFin, Integer vacantesDisponibles) {
        this.sede = sede;
        this.curso = curso;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vacantesDisponibles = vacantesDisponibles;
    }

    public cronogramaCursos(){

    }

    public Integer getIdCronograma() {
        return idCronograma;
    }

    public sedes getSede() {
        return sede;
    }

    public cursos getCurso() {
        return curso;
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

    public void setIdCronograma(Integer idCronograma) {
        this.idCronograma = idCronograma;
    }

    public void setSede(sedes sede) {
        this.sede = sede;
    }

    public void setCurso(cursos curso) {
        this.curso = curso;
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