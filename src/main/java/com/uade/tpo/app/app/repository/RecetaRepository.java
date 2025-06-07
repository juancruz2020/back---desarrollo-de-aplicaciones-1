package com.uade.tpo.app.app.repository;

import com.uade.tpo.app.app.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByNombreRecetaContainingIgnoreCase(String nombre);
    List<Receta> findByUsuario(Usuario usuario);
    Optional<Receta> findByNombreRecetaIgnoreCase(String nombre);
    List<Receta> findByTipoRecetaIdTipo(Long idTipoReceta);

    // Filtrar por categoría (a través de la descripción de TipoReceta)
    @Query("SELECT r FROM Receta r WHERE LOWER(r.tipoReceta.descripcion) LIKE LOWER(CONCAT('%', :categoria, '%'))")
    List<Receta> findByCategoria(@Param("categoria") String categoria);

    // Filtrar por usuario (por nickname)
    @Query("SELECT r FROM Receta r WHERE LOWER(r.usuario.nickname) = LOWER(:nickname)")
    List<Receta> findByUsuarioNickname(@Param("nickname") String nickname);

    Optional<Receta> findByNombreReceta(String nombreReceta);
}
