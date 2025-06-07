package com.uade.tpo.app.app.repository;
import com.uade.tpo.app.app.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface TipoRecetaRepository extends JpaRepository<TipoReceta, Long> {
    Optional<TipoReceta> findByDescripcionIgnoreCase(String descripcion);
}

