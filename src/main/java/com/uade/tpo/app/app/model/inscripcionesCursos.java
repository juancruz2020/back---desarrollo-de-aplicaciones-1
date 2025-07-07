package com.uade.tpo.app.app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inscripcionesCursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class inscripcionesCursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscripcion;

    @ManyToOne
    @JoinColumn(name = "idAlumno", nullable = false)
    private alumnos alumno;

    @ManyToOne
    @JoinColumn(name = "idCronograma", nullable = false)
    private cronogramaCursos cronograma;

    public inscripcionesCursos(alumnos alumno, cronogramaCursos cronograma) {
        this.alumno = alumno;
        this.cronograma = cronograma;
    }
    public inscripcionesCursos(){

    }

    public Long getIdInscripcion() {
        return idInscripcion;
    }

    public alumnos getAlumno() {
        return alumno;
    }

    public cronogramaCursos getCronograma() {
        return cronograma;
    }
}
