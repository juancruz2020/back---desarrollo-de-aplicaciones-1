package com.uade.tpo.app.app.repository;

import com.uade.tpo.app.app.model.RegistroUsuario;
import com.uade.tpo.app.app.model.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegistroUsuarioRepository extends JpaRepository<RegistroUsuario, Integer> {

    Optional<RegistroUsuario> findByCodigoVerificacion(String codigoVerificacion);

    Optional<RegistroUsuario> findByUsuarioAndEstado(usuarios usuario, String estado);
}
