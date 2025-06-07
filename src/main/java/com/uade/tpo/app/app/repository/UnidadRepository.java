package com.uade.tpo.app.app.repository;
import com.uade.tpo.app.app.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Long> {
    Optional<Unidad> findByDescripcionIgnoreCase(String descripcion);
}
