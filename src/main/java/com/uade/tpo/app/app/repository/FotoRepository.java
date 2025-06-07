package com.uade.tpo.app.app.repository;
import com.uade.tpo.app.app.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
    List<Foto> findByRecetaIdReceta(Long idReceta);
}

