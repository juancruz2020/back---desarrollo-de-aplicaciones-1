package com.uade.tpo.app.app.repository;

import com.uade.tpo.app.app.model.CuentaUsuario;
import com.uade.tpo.app.app.model.alumnos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaUsuarioRepository extends JpaRepository<CuentaUsuario, Long>{

    @Query("SELECT COUNT(c) > 0 FROM CuentaUsuario c JOIN c.usuario u " +
            "WHERE (c.contrasena = :contrasena AND u.mail = :mail) " +
            "OR u.nickname = :nickname")
    boolean existePorLogin(
            String contrasena,
             String mail,
            String nickname);



    @Modifying
    @Transactional
    @Query("UPDATE CuentaUsuario c SET c.contrasena = :contrasena WHERE c.usuario.idUsuario = :idUsuario")
    void actualizarContrasena (String contrasena,int idUsuario);
}
