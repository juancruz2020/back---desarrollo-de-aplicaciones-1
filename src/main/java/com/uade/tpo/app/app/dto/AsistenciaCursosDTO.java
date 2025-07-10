package com.uade.tpo.app.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AsistenciaCursosDTO {
    private Integer idAsistencia;
    private Integer idAlumno;
    private String nombreAlumno;
    private String mailAlumno;
    private Integer idCronograma;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer vacantesDisponibles;
    private String nombreSede;
    private Integer idCurso;
    private String descripcionCurso;
    private LocalDateTime fechaAsistencia;

    public AsistenciaCursosDTO() {}

    public AsistenciaCursosDTO(Integer idAsistencia, Integer idAlumno, String nombreAlumno, String mailAlumno,
                               Integer idCronograma, LocalDate fechaInicio, LocalDate fechaFin, Integer vacantesDisponibles,
                               String nombreSede, Integer idCurso, String descripcionCurso, LocalDateTime fechaAsistencia) {
        this.idAsistencia = idAsistencia;
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.mailAlumno = mailAlumno;
        this.idCronograma = idCronograma;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vacantesDisponibles = vacantesDisponibles;
        this.nombreSede = nombreSede;
        this.idCurso = idCurso;
        this.descripcionCurso = descripcionCurso;
        this.fechaAsistencia = fechaAsistencia;
    }


    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public String getMailAlumno() {
        return mailAlumno;
    }

    public Integer getIdCronograma() {
        return idCronograma;
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

    public String getNombreSede() {
        return nombreSede;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public String getDescripcionCurso() {
        return descripcionCurso;
    }

    public LocalDateTime getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public void setMailAlumno(String mailAlumno) {
        this.mailAlumno = mailAlumno;
    }

    public void setIdCronograma(Integer idCronograma) {
        this.idCronograma = idCronograma;
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

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public void setDescripcionCurso(String descripcionCurso) {
        this.descripcionCurso = descripcionCurso;
    }

    public void setFechaAsistencia(LocalDateTime fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }
}