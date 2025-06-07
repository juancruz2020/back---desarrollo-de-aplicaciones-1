package com.uade.tpo.app.app.repository;
import com.uade.tpo.app.app.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface UtilizadoRepository extends JpaRepository<Utilizado, Long> {

    List<Utilizado> findByIngredienteIdIngredienteIn(List<Long> idIngrediente);
    // Buscar todos los utilizados de una receta
    List<Utilizado> findByRecetaIdReceta(Long idReceta);

    // Buscar recetas que utilizan un ingrediente específico
    @Query("SELECT u.receta FROM Utilizado u WHERE LOWER(u.ingrediente.nombre) = LOWER(:nombreIngrediente)")
    List<Receta> findRecetasByIngrediente(@Param("nombreIngrediente") String nombreIngrediente);

    // Eliminar ingredientes de una receta (para modificarla)
    void deleteByRecetaIdReceta(Long idReceta);
}
