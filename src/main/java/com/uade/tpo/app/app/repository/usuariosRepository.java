package com.uade.tpo.app.app.repository;

import com.uade.tpo.app.app.dto.CodigoDTO;
import com.uade.tpo.app.app.model.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface usuariosRepository extends JpaRepository<usuarios, Long> {

    boolean existsByMail(String mail);

    boolean existsByNickname(String nickname);

    usuarios findByIdUsuario(int id);

    @Query("SELECT u.idUsuario FROM usuarios u WHERE u.mail = :email")
    Integer findIdUsuarioByMail(String email);



    @Query("SELECT r.codigoVerificacion, u.idUsuario " +
            "FROM RegistroUsuario r " +
            "JOIN r.usuario u " +
            "WHERE u.mail = :mail")
    Integer obtenerCodigoEcodigoPorMail(String mail);

    @Modifying
    @Transactional
    @Query("UPDATE usuarios u SET u.habilitado = 'Si' WHERE u.idUsuario = :idUsuario")
    void habilitarUsuarioPorId(int idUsuario);

    @Query("SELECT CASE WHEN r.fechaExpiracion < CURRENT_TIMESTAMP THEN true ELSE false END " +
            "FROM RegistroUsuario r WHERE r.codigoVerificacion = :codigo")
    Boolean estaExpirado (String codigo);

    @Modifying
    @Transactional
    @Query("UPDATE usuarios u SET u.nombre = :nombre WHERE u.idUsuario = :id")
    void actualizarNombrePorId(String nombre, Integer id);

}
