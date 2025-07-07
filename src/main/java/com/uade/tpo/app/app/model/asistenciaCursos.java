package com.uade.tpo.app.app.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

    @Entity
    @Table(name = "asistenciaCursos")
    @Data
    @AllArgsConstructor
    public class asistenciaCursos {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idAsistencia;

        @ManyToOne
        @JoinColumn(name = "idAlumno", nullable = false)
        private alumnos alumno;

        @ManyToOne
        @JoinColumn(name = "idCronograma", nullable = false)
        private cronogramaCursos cronograma;

        private LocalDateTime fecha;

        public asistenciaCursos(alumnos alumno, cronogramaCursos cronograma, LocalDateTime fecha) {
            this.alumno = alumno;
            this.cronograma = cronograma;
            this.fecha = fecha;
        }
        public asistenciaCursos(){

        }

        public Integer getIdAsistencia() {
            return idAsistencia;
        }

        public alumnos getAlumno() {
            return alumno;
        }

        public cronogramaCursos getCronograma() {
            return cronograma;
        }

        public LocalDateTime getFecha() {
            return fecha;
        }

        public void setIdAsistencia(Integer idAsistencia) {
            this.idAsistencia = idAsistencia;
        }

        public void setAlumno(alumnos alumno) {
            this.alumno = alumno;
        }

        public void setCronograma(cronogramaCursos cronograma) {
            this.cronograma = cronograma;
        }

        public void setFecha(LocalDateTime fecha) {
            this.fecha = fecha;
        }
    }

