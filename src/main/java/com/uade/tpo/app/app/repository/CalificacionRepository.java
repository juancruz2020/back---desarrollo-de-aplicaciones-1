package com.uade.tpo.app.app.repository;
import com.uade.tpo.app.app.model.*;
import org.springframework.data.jpa.repository.*;


import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByRecetaIdReceta(Long idReceta);
}

