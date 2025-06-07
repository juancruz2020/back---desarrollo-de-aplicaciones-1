package com.uade.tpo.app.app.repository;
import com.uade.tpo.app.app.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    Optional<Ingrediente> findByNombreIgnoreCase(String nombre);
}

