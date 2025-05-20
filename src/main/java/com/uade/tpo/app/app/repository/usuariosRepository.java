package com.uade.tpo.app.app.repository;

import com.uade.tpo.app.app.model.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usuariosRepository extends JpaRepository<usuarios, Long> {

    boolean existsByMail(String mail);
    boolean existsByNickname(String nickname);
}
