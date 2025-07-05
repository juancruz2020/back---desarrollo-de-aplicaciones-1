package com.uade.tpo.app.app.repository;
import com.uade.tpo.app.app.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {

    @Query("SELECT f.urlFoto FROM Foto f WHERE f.receta.idReceta = :idReceta")
    Optional <String> findUrlFotoByRecetaIdReceta(@Param("idReceta") Long idReceta);


}

