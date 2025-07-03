package com.uade.tpo.app.app.repository;

import com.uade.tpo.app.app.dto.CursoCompletoDTO;
import com.uade.tpo.app.app.dto.CursoResumenDTO;
import com.uade.tpo.app.app.model.cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface cursosRepository extends JpaRepository<cursos, Integer>{

    @Query("SELECT new com.uade.tpo.app.app.dto.CursoResumenDTO(c.contenidos, c.descripcion) FROM cursos c")
    List<CursoResumenDTO> findAllCursoResumen();

    @Query("SELECT new com.uade.tpo.app.app.dto.CursoCompletoDTO(c.descripcion, c.contenidos, c.requerimientos, c.duracion, c.precio, c.modalidad) FROM cursos c")
    List<CursoCompletoDTO> findAllCursosDTO();


}
