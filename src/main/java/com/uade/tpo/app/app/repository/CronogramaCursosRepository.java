package com.uade.tpo.app.app.repository;

import com.uade.tpo.app.app.model.cronogramaCursos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CronogramaCursosRepository extends JpaRepository<cronogramaCursos, Long> {

    List<cronogramaCursos> findByCurso_IdCurso(int idCurso);

}
