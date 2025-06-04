package com.uade.tpo.app.app.repository;

import com.uade.tpo.app.app.model.RegistroUsuario;
import com.uade.tpo.app.app.model.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RegistroUsuarioRepository extends JpaRepository<RegistroUsuario, Integer> {

    Optional<RegistroUsuario> findByCodigoVerificacion(String codigoVerificacion);

    @Modifying
    @Transactional
    @Query("UPDATE RegistroUsuario r SET r.codigoVerificacion = :codigo WHERE r.usuario.idUsuario = :idUsuario")
    void actualizarCodigoVerificacion( String codigo,  int idUsuario);

    @Modifying
    @Transactional
    @Query("UPDATE RegistroUsuario r SET r.fechaExpiracion = :fecha WHERE r.usuario.idUsuario = :idUsuario")
    void actualizarFechaExpiracion(LocalDateTime fecha, int idUsuario);

    @Query("SELECT r.usuario.idUsuario FROM RegistroUsuario r WHERE r.codigoVerificacion = :codigoVerificacion")
    int obtenerIdPorCodigoVerificacion(String codigoVerificacion);
}
