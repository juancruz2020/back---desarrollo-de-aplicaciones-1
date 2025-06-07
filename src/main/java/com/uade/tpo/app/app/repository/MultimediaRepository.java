package com.uade.tpo.app.app.repository;
import com.uade.tpo.app.app.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Long> {
    Optional<Multimedia> findByPasoIdPaso(Long idPaso);
    void deleteByPasoIdPaso(Long idPaso);
}
