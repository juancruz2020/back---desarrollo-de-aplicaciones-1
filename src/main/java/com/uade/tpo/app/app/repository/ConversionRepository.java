package com.uade.tpo.app.app.repository;

import com.uade.tpo.app.app.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Long> {
    Optional<Conversion> findByUnidadOrigenAndUnidadDestino(Unidad unidadOrigen, Unidad unidadDestino);
}
