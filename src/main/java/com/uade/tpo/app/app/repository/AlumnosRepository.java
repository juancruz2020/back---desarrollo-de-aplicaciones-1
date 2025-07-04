package com.uade.tpo.app.app.repository;


import com.uade.tpo.app.app.model.alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnosRepository extends JpaRepository<alumnos, Long> {
    boolean existsByUsuario_IdUsuario(Long idUsuario);
}
