package com.uade.tpo.app.app.repository;

import com.uade.tpo.app.app.model.alumnos;
import com.uade.tpo.app.app.model.cronogramaCursos;
import com.uade.tpo.app.app.model.inscripcionesCursos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscripcionesCursosRepository extends JpaRepository<inscripcionesCursos, Long> {
    boolean existsByAlumnoAndCronograma(alumnos alumno, cronogramaCursos cronograma);

    List<inscripcionesCursos> findByAlumno(alumnos alumno);
    Optional<inscripcionesCursos> findByAlumnoAndCronograma_IdCronograma(alumnos alumno, Integer idCronograma);
}
