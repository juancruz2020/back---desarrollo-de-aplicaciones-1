package com.uade.tpo.app.app.repository;

import com.uade.tpo.app.app.model.CuentaUsuario;
import com.uade.tpo.app.app.model.alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaUsuarioRepository extends JpaRepository<CuentaUsuario, Long>{

}
