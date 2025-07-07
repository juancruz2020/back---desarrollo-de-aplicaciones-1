package com.uade.tpo.app.app.repository;


import com.uade.tpo.app.app.model.alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumnosRepository extends JpaRepository<alumnos, Long> {
    boolean existsByUsuario_IdUsuario(Long idUsuario);

    @Query("SELECT a FROM alumnos a WHERE a.usuario.mail = :mail")
    Optional<alumnos> findAlumnoByMail(String mail);
}
