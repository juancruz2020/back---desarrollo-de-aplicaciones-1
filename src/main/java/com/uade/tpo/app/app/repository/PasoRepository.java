package com.uade.tpo.app.app.repository;
import com.uade.tpo.app.app.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface PasoRepository extends JpaRepository<Paso, Long> {
    List<Paso> findByRecetaIdReceta(Long idReceta);

    void deleteByRecetaIdReceta(Long idReceta);
}

